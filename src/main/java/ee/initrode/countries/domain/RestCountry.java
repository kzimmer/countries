
package ee.initrode.countries.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestCountry implements Serializable {

    private String name;
    private List<String> topLevelDomain = null;
    private String alpha2Code;
    private String alpha3Code;
    private List<String> callingCodes = null;
    private String capital;
    private List<String> altSpellings = null;
    private String region;
    private String subregion;
    private Integer population;
    private List<Integer> latlng = null;
    private String demonym;
    private Integer area;
    private Integer gini;
    private List<String> timezones = null;
    private List<String> borders = null;
    private String nativeName;
    private String numericCode;
    private List<Currency> currencies = null;
    private List<Language> languages = null;
    private Translations translations;
    private String flag;
    private List<RegionalBloc> regionalBlocs = null;
    private String cioc;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -975640766767539051L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public List<Integer> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Integer> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getGini() {
        return gini;
    }

    public void setGini(Integer gini) {
        this.gini = gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(List<RegionalBloc> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
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
                .append("name", name)
                .append("topLevelDomain", topLevelDomain)
                .append("alpha2Code", alpha2Code)
                .append("alpha3Code", alpha3Code)
                .append("callingCodes", callingCodes)
                .append("capital", capital)
                .append("altSpellings", altSpellings)
                .append("region", region)
                .append("subregion", subregion)
                .append("population", population)
                .append("latlng", latlng)
                .append("demonym", demonym)
                .append("area", area)
                .append("gini", gini)
                .append("timezones", timezones)
                .append("borders", borders)
                .append("nativeName", nativeName)
                .append("numericCode", numericCode)
                .append("currencies", currencies)
                .append("languages", languages)
                .append("translations", translations)
                .append("flag", flag)
                .append("regionalBlocs", regionalBlocs)
                .append("cioc", cioc)
                .append("additionalProperties", additionalProperties)
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(alpha3Code)
                .append(alpha2Code)
                .append(name)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RestCountry) == false) {
            return false;
        }
        RestCountry rhs = ((RestCountry) other);
        return new EqualsBuilder()
                .append(alpha3Code, rhs.alpha3Code)
                .append(alpha2Code, rhs.alpha2Code)
                .append(name, rhs.name)
                .isEquals();
    }

}
