package ee.initrode.countries.repositories;

import ee.initrode.countries.api.v1.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {

}
