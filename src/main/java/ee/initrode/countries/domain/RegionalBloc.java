
package ee.initrode.countries.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionalBloc implements Serializable {

    private String acronym;
    private String name;
    private List<Object> otherAcronyms = null;
    private List<Object> otherNames = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1365936654708084702L;

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(List<Object> otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public List<Object> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<Object> otherNames) {
        this.otherNames = otherNames;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("acronym", acronym)
                .append("name", name)
                .append("otherAcronyms", otherAcronyms)
                .append("otherNames", otherNames)
                .append("additionalProperties", additionalProperties)
                .toString();
    }

}
