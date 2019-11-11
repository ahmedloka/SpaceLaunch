package my.apps.udacity.nano.degree.space.launch.models.firstLaunch;

import java.util.List;

public class Missions
{
    private int id;

    private String name;

    private String description;

    private int type;

    private String wikiURL;

    private String typeName;

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
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setWikiURL(String wikiURL){
        this.wikiURL = wikiURL;
    }
    public String getWikiURL(){
        return this.wikiURL;
    }
    public void setTypeName(String typeName){
        this.typeName = typeName;
    }
    public String getTypeName(){
        return this.typeName;
    }
    public void setAgencies(List<Agencies> agencies){
        this.agencies = agencies;
    }
    public List<Agencies> getAgencies(){
        return this.agencies;
    }
}



