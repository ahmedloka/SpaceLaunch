package my.apps.udacity.nano.degree.space.launch.models.firstLaunch;

import java.util.List;

public class Pads
{
    private int id;

    private String name;

    private String infoURL;

    private String wikiURL;

    private String mapURL;

    private double latitude;

    private double longitude;

    private List<Agencies> agencies;

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
    public void setMapURL(String mapURL){
        this.mapURL = mapURL;
    }
    public String getMapURL(){
        return this.mapURL;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public double getLatitude(){
        return this.latitude;
    }
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
    public double getLongitude(){
        return this.longitude;
    }
    public void setAgencies(List<Agencies> agencies){
        this.agencies = agencies;
    }
    public List<Agencies> getAgencies(){
        return this.agencies;
    }
}



