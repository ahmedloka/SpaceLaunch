package my.apps.udacity.nano.degree.space.launch.models.firstLaunch;

import java.util.List;

public class Lsp {
    private int id;

    private String name;

    private String abbrev;

    private String countryCode;

    private int type;

    private String infoURL;

    private String wikiURL;


    private List<String> infoURLs;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public String getAbbrev() {
        return this.abbrev;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public void setInfoURL(String infoURL) {
        this.infoURL = infoURL;
    }

    public String getInfoURL() {
        return this.infoURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    public String getWikiURL() {
        return this.wikiURL;
    }

    public void setInfoURLs(List<String> infoURLs) {
        this.infoURLs = infoURLs;
    }

    public List<String> getInfoURLs() {
        return this.infoURLs;
    }
}


