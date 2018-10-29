package ee.initrode.countries.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "country")
public class Country {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;

    private String name;
    private String capital;
    private Integer population;
    private Boolean valid;


//    public Country(String countryName) {
//        this.name = countryName;
//        this.capital = "";
//        this.population = 0;
//        this.valid = Boolean.FALSE;
//    }


    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country that = (Country) o;

        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
