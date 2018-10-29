package ee.initrode.countries.services;

import ee.initrode.countries.domain.RestCountry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RestCountriesServiceImpl implements RestCountriesService {

    public static final String DEFAULT_API = "https://restcountries.eu/rest/v2/name/";

    private final String apiUri;

    public RestCountriesServiceImpl(@Value(DEFAULT_API) String apiUri) {
        this.apiUri = apiUri;
        log.info("External API at:" + apiUri);
    }

    @Override
    public Flux<RestCountry> getCountryByName(Mono<String> countryName) {

        countryName.subscribe(x -> log.info("RestCountry name in API service parameter Mono is : " + x));

        return WebClient.create(apiUri)
                .get()
                .uri(uriBuilder -> uriBuilder.path("{id}").build(countryName.block()))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(RestCountry.class));
    }
}
