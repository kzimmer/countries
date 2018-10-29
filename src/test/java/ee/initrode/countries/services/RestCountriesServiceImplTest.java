package ee.initrode.countries.services;

import ee.initrode.countries.domain.RestCountry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestCountriesServiceImplTest {

    @Autowired
    RestCountriesService restCountriesService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getCountryByName() {
        String testCountryName = "Albania";

        Flux<RestCountry> testCountry = restCountriesService
                .getCountryByName(Mono.just(testCountryName));

        assertEquals(testCountryName, testCountry.blockFirst().getName());
    }
}