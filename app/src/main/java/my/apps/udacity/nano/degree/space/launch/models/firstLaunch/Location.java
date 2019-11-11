package my.apps.udacity.nano.degree.space.launch.models.firstLaunch;

import java.util.List;

public class Location
{
    private List<Pads> pads;

    private int id;

    private String name;

    private String infoURL;

    private String wikiURL;

    private String countryCode;

    public void setPads(List<Pads> pads){
        this.pads = pads;
    }
    public List<Pads> getPads(){
        return this.pads;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setInfoURL(String infoURL){
        this.infoURL = infoURL;
    }
    public String getInfoURL(){
        return this.infoURL;
    }
    public void setWikiURL(String wikiURL){
        this.wikiURL = wikiURL;
    }
    public String getWikiURL(){
        return this.wikiURL;
    }
    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }
    public String getCountryCode(){
        return this.countryCode;
    }
}



