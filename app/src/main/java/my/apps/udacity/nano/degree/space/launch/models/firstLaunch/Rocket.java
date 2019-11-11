package my.apps.udacity.nano.degree.space.launch.models.firstLaunch;

import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "rocket_table")
public class Rocket
{
    @PrimaryKey
    private int id;

    @Ignore
    private String name;

    @Ignore
    private String configuration;

    @Ignore
    private String familyname;
    @Ignore
    private List<Agencies> agencies;
    @Ignore
    private String wikiURL;
    @Ignore
    private List<String> infoURLs;
    @Ignore
    private String infoURL;
    @Ignore
    private List<Integer> imageSizes;

    private String imageURL;

    public Rocket(String imageURL) {
        this.imageURL = imageURL;
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
    public void setConfiguration(String configuration){
        this.configuration = configuration;
    }
    public String getConfiguration(){
        return this.configuration;
    }
    public void setFamilyname(String familyname){
        this.familyname = familyname;
    }
    public String getFamilyname(){
        return this.familyname;
    }
    public void setAgencies(List<Agencies> agencies){
        this.agencies = agencies;
    }
    public List<Agencies> getAgencies(){
        return this.agencies;
    }
    public void setWikiURL(String wikiURL){
        this.wikiURL = wikiURL;
    }
    public String getWikiURL(){
        return this.wikiURL;
    }
    public void setInfoURLs(List<String> infoURLs){
        this.infoURLs = infoURLs;
    }
    public List<String> getInfoURLs(){
        return this.infoURLs;
    }
    public void setInfoURL(String infoURL){
        this.infoURL = infoURL;
    }
    public String getInfoURL(){
        return this.infoURL;
    }
    public void setImageSizes(List<Integer> imageSizes){
        this.imageSizes = imageSizes;
    }
    public List<Integer> getImageSizes(){
        return this.imageSizes;
    }
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }
    public String getImageURL(){
        return this.imageURL;
    }
}




