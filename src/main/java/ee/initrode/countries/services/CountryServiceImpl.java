package ee.initrode.countries.services;

import com.google.common.collect.Lists;
import ee.initrode.countries.api.v1.model.Country;
import ee.initrode.countries.domain.RestCountry;
import ee.initrode.countries.repositories.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private RestCountriesService restCountriesService;

    public CountryServiceImpl(CountryRepository countryRepository, RestCountriesService restCountriesService) {
        this.countryRepository = countryRepository;
        this.restCountriesService = restCountriesService;
    }

    @Override
    public Flux<Country> fluxCountries() {
        return Flux.defer(() -> Flux.fromIterable(countryRepository.findAll()))
                .subscribeOn(Schedulers.elastic());
    }

    @Override
    public List<Country> listCountries() {
        return Lists.newArrayList(countryRepository.findAll());
    }


    @Override
    public Mono<Country> addCountry(Mono<String> countryNameMono) {
        String countryName = countryNameMono.block();

        // 1. check if country present in repo, if it is, get from repo
        Optional<Country> countryFromRepo = countryRepository.findById(countryName);
        if (countryFromRepo.isPresent()) {
            log.info("RestCountry retrieved from repo: " + countryFromRepo.get().getName());
            return Mono.just(countryFromRepo.get());
        } else {
            // 2. if not in repo, look up from external api service
            log.info("RestCountry not found in repo, Starting API call for: " + countryName);

            Mono<Country> singel = restCountriesService
                    .getCountryByName(countryNameMono).single()
                    .doOnError(throwable -> {
                        log.warn("RestCountry not found or multiple found");
                        countryRepository.save(new Country(countryName, "", 0, false));
                    })
                    .map(this::convertToCountry)
                    .doOnSuccess(countryDTO -> {
                        if (null != countryDTO.getName()) {
                            log.info("Single country: " + countryDTO.getName());
                            countryRepository.save(countryDTO);
                        } else {
                            log.warn("Defaulting to entered not valid name");
                            countryRepository.save(new Country(countryName, "", 0, false));
                        }

                    });
            log.info("API call done, processing... as this is asynchronous, it will be present in random place");

//            singel
//                    .doOnError(throwable -> log.error("RestCountry not found", throwable.getMessage()))
//                    .doOnNext(countryDTO -> {
//                        log.info("Single country: " + countryDTO.getName());
//                        countryRepository.save(countryDTO);
//                    });

//            singel.subscribe(countryDTO -> {
//                try {
//                    log.info("Single country: " + countryDTO.getName());
//                    countryRepository.save(countryDTO);
//                } catch (NoSuchElementException e) {
////                    throw e;
//                    log.error("RestCountry not found");
//                } catch (IndexOutOfBoundsException e) {
////                    throw e;
//                    log.error("More than 1 country found");
//                }
//            });
            // todo 3. if not in external API then add as nonvalid
            return singel;
        }
    }

    @Override
    public void deleteCountry(String countryName) {
        log.info("deleteCountry is deleting" + countryName);

        countryRepository.deleteById(countryName);
    }

    @Override
    public void deleteAllCountries() {
        log.info("deleteCountry is deleting everything");

        countryRepository.deleteAll();
    }

    private Country convertToCountry(RestCountry restCountry) {
        return new Country(restCountry.getName(), restCountry.getCapital(), restCountry.getPopulation(), true);
    }
}
