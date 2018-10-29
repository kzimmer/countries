package ee.initrode.countries.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDoc {

    private Flux<Country> countryDoc;
}
