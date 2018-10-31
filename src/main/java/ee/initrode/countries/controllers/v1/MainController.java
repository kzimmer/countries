package ee.initrode.countries.controllers.v1;


import ee.initrode.countries.api.v1.model.Country;
import ee.initrode.countries.api.v1.model.CountryDoc;
import ee.initrode.countries.domain.RestCountry;
import ee.initrode.countries.services.CountryService;
import ee.initrode.countries.services.RestCountriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("/")
public class MainController {

    private RestCountriesService restCountriesService;
    private CountryService countryService;

    public MainController(RestCountriesService restCountriesService, CountryService countryService) {
        this.restCountriesService = restCountriesService;
        this.countryService = countryService;
    }

    @GetMapping({"", "/", "/index"})
    public String returnBlank() {
        return "This page is intentionally left blank";
    }

    /**
     * Easter egg / testing tool
     * Get RestCountry list straight thru backend from external RestCountries API service
     *
     * @param countryName country name as String at this point
     * @return RestCountry flux
     */

    @GetMapping("/country/{name}")
    public @ResponseBody
    Flux<RestCountry> getCountry(@PathVariable("name") String countryName) {

        log.info("MainController.getCountry Getting country info for " + countryName);

        return restCountriesService.getCountryByName(Mono.just(countryName));
    }

    /**
     * Post RestCountry @param name to Model
     *
     * @param countryName RestCountry name as String
     * @return Country from Model
     */
    @PostMapping("/country/{name}")
    public @ResponseBody
    Mono<Country> addCountry(@PathVariable("name") String countryName) {

        log.info("MainController.addCountry: Got countryName from frontend:" + countryName);

        return countryService.addCountry(Mono.just(countryName));
    }

    /**
     * Delete RestCountry from Model by name
     *
     * @param countryName RestCountry name as String
     * @return status code of the
     */
    @DeleteMapping("/country/{name}")
    public ResponseEntity<Void> deleteCountry(@PathVariable("name") String countryName) {

        countryService.deleteCountry(countryName);

        // todo this response has to be conditional on Delete RestCountry result
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Return list of countries in model/repository
     *
     * @return List of countries. Empty if no countries found
     */
    @GetMapping("/countries/")
    public Flux<Country> fluxCountries() {
        return countryService.fluxCountries();
    }

    /**
     * Delete all RestCountry entities from model/repository
     *
     * @return Entity object mostly to get CRUD operation result status code
     */
    @DeleteMapping("/countries/")
    public ResponseEntity<Void> deleteAllCountries() {

        countryService.deleteAllCountries();

        // todo This response has to be conditional on Delete operation result
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/download/", produces = MediaType.APPLICATION_XML_VALUE)
    public Mono<CountryDoc> downloadCountries() {
        return Mono.just(new CountryDoc(countryService.fluxCountries()));
    }

    @GetMapping(value = "/xml/", produces = MediaType.APPLICATION_XML_VALUE)
    public Flux<Country> xmlCountries(){ return countryService.fluxCountries();}
}
