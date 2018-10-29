package ee.initrode.countries.services;

import ee.initrode.countries.api.v1.model.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CountryService {

    Flux<Country> fluxCountries();

    // todo after experiments with jacson XML back to flux...
    List<Country> listCountries();

    Mono<Country> addCountry(Mono<String> countryName);

    void deleteCountry(String countryName);

    void deleteAllCountries();

}
