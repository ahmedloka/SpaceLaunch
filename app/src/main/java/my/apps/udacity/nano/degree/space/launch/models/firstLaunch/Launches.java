package my.apps.udacity.nano.degree.space.launch.models.firstLaunch;

import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "launch_table")
public class Launches {
    @PrimaryKey
    private int id;

    private String name;

    private String windowstart;

    private String windowend;

    @Ignore
    private String net;
    @Ignore
    private int wsstamp;
    @Ignore
    private int westamp;
    @Ignore
    private int netstamp;
    @Ignore
    private String isostart;
    @Ignore
    private String isoend;
    @Ignore
    private String isonet;
    @Ignore
    private int status;
    @Ignore
    private int inhold;
    @Ignore
    private int tbdtime;
    @Ignore
    private List<String> vidURLs;
    @Ignore
    private String vidURL;
    @Ignore
    private List<String> infoURLs;
    @Ignore
    private String infoURL;
    @Ignore
    private String holdreason;
    @Ignore
    private String failreason;
    @Ignore
    private int tbddate;

    //  private int probability;
    @Ignore
    private String hashtag;

    @Ignore
    private Location location;
    @Ignore
    private Rocket rocket;
    @Ignore
    private List<Missions> missions;

    //   private Lsp lsp;


    public Launches(String name, String windowstart, String windowend) {
        this.name = name;
        this.windowstart = windowstart;
        this.windowend = windowend;
    }

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

    public void setWindowstart(String windowstart) {
        this.windowstart = windowstart;
    }

    public String getWindowstart() {
        return this.windowstart;
    }

    public void setWindowend(String windowend) {
        this.windowend = windowend;
    }

    public String getWindowend() {
        return this.windowend;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getNet() {
        return this.net;
    }

    public void setWsstamp(int wsstamp) {
        this.wsstamp = wsstamp;
    }

    public int getWsstamp() {
        return this.wsstamp;
    }

    public void setWestamp(int westamp) {
        this.westamp = westamp;
    }

    public int getWestamp() {
        return this.westamp;
    }

    public void setNetstamp(int netstamp) {
        this.netstamp = netstamp;
    }

    public int getNetstamp() {
        return this.netstamp;
    }

    public void setIsostart(String isostart) {
        this.isostart = isostart;
    }

    public String getIsostart() {
        return this.isostart;
    }

    public void setIsoend(String isoend) {
        this.isoend = isoend;
    }

    public String getIsoend() {
        return this.isoend;
    }

    public void setIsonet(String isonet) {
        this.isonet = isonet;
    }

    public String getIsonet() {
        return this.isonet;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }

    public void setInhold(int inhold) {
        this.inhold = inhold;
    }

    public int getInhold() {
        return this.inhold;
    }

    public void setTbdtime(int tbdtime) {
        this.tbdtime = tbdtime;
    }

    public int getTbdtime() {
        return this.tbdtime;
    }

    public void setVidURLs(List<String> vidURLs) {
        this.vidURLs = vidURLs;
    }

    public List<String> getVidURLs() {
        return this.vidURLs;
    }

    public void setVidURL(String vidURL) {
        this.vidURL = vidURL;
    }

    public String getVidURL() {
        return this.vidURL;
    }

    public void setInfoURLs(List<String> infoURLs) {
        this.infoURLs = infoURLs;
    }

    public List<String> getInfoURLs() {
        return this.infoURLs;
    }

    public void setInfoURL(String infoURL) {
        this.infoURL = infoURL;
    }

    public String getInfoURL() {
        return this.infoURL;
    }

    public void setHoldreason(String holdreason) {
        this.holdreason = holdreason;
    }

    public String getHoldreason() {
        return this.holdreason;
    }

    public void setFailreason(String failreason) {
        this.failreason = failreason;
    }

    public String getFailreason() {
        return this.failreason;
    }

    public void setTbddate(int tbddate) {
        this.tbddate = tbddate;
    }

    public int getTbddate() {
        return this.tbddate;
    }

    //    public void setProbability(int probability){
//        this.probability = probability;
//    }
//    public int getProbability(){
//        return this.probability;
//    }
    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getHashtag() {
        return this.hashtag;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public Rocket getRocket() {
        return this.rocket;
    }

    public void setMissions(List<Missions> missions) {
        this.missions = missions;
    }

    public List<Missions> getMissions() {
        return this.missions;
    }
//    public void setLsp(Lsp lsp){
//        this.lsp = lsp;
//    }
//    public Lsp getLsp(){
//        return this.lsp;
//    }
}
