package ee.initrode.countries.controllers;

import ee.initrode.countries.api.v1.model.Country;
import ee.initrode.countries.controllers.v1.MainController;
import ee.initrode.countries.domain.RestCountry;
import ee.initrode.countries.repositories.CountryRepository;
import ee.initrode.countries.services.CountryService;
import ee.initrode.countries.services.CountryServiceImpl;
import ee.initrode.countries.services.RestCountriesService;
import ee.initrode.countries.services.RestCountriesServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

public class MainControllerTest {

    WebTestClient webTestClient;
    MainController mainController;
    RestCountriesService restCountriesService;
    CountryService countryService;
    CountryRepository countryRepository;

    @Before
    public void setUp() throws Exception {
        countryRepository = Mockito.mock(CountryRepository.class);
        restCountriesService = new RestCountriesServiceImpl("https://restcountries.eu/rest/v2/name/");
        countryService = new CountryServiceImpl(countryRepository, restCountriesService);
        mainController = new MainController(restCountriesService, countryService);

        webTestClient = WebTestClient.bindToController(mainController).build();
    }

    @Test
    public void returnBlank() {

    }

    @Test
    public void getCountry() {
        BDDMockito.given(countryRepository.findById("Test Country"))
                .willReturn(java.util.Optional.ofNullable(
                        Country.builder().name("Test Country").build()));

        webTestClient.get()
                .uri("/country/")
                .exchange()
                .expectBodyList(RestCountry.class);
    }

    @Test
    public void addCountry() {
        BDDMockito.given(countryRepository.save(any(Country.class)))
                .willReturn(Country.builder().name("Second").build());

        webTestClient.post()
                .uri("/country/Second")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void listCountries() {
        BDDMockito.given(countryRepository.findAll())
                .willReturn(Arrays.asList(Country.builder().name("Estonia").build(),
                        Country.builder().name("Kosovo").build()));

        webTestClient.get()
                .uri("/countries/")
                .exchange()
                .expectBodyList(Country.class)
                .hasSize(2);
    }

    @Test
    public void deleteAllCountries() {
        webTestClient.delete()
                .uri("/countries/")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }
}