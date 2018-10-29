package ee.initrode.countries.domain;

import java.util.List;

public class ApiData {
    private List<RestCountry> data;

    public List<RestCountry> getData() {
        return data;
    }

    public void setData(List<RestCountry> data) {
        this.data = data;
    }
}
