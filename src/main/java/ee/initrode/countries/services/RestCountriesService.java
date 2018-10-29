package ee.initrode.countries.services;

import ee.initrode.countries.domain.RestCountry;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RestCountriesService {

    Flux<RestCountry> getCountryByName(Mono<String> countryName);
}
