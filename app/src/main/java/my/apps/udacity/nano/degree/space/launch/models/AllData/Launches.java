package my.apps.udacity.nano.degree.space.launch.models.AllData;

public class Launches
{
    private int id;

    private String name;

    private String windowstart;

    private String windowend;

    private String net;

    private int status;

    private int tbdtime;

    private int tbddate;

    private int probability;

    private String changed;


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
    public void setWindowstart(String windowstart){
        this.windowstart = windowstart;
    }
    public String getWindowstart(){
        return this.windowstart;
    }
    public void setWindowend(String windowend){
        this.windowend = windowend;
    }
    public String getWindowend(){
        return this.windowend;
    }
    public void setNet(String net){
        this.net = net;
    }
    public String getNet(){
        return this.net;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setTbdtime(int tbdtime){
        this.tbdtime = tbdtime;
    }
    public int getTbdtime(){
        return this.tbdtime;
    }
    public void setTbddate(int tbddate){
        this.tbddate = tbddate;
    }
    public int getTbddate(){
        return this.tbddate;
    }
    public void setProbability(int probability){
        this.probability = probability;
    }
    public int getProbability(){
        return this.probability;
    }
    public void setChanged(String changed){
        this.changed = changed;
    }
    public String getChanged(){
        return this.changed;
    }

    @Override
    public String toString() {
        return "Launches{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", windowstart='" + windowstart + '\'' +
                ", windowend='" + windowend + '\'' +
                ", net='" + net + '\'' +
                ", status=" + status +
                ", tbdtime=" + tbdtime +
                ", tbddate=" + tbddate +
                ", probability=" + probability +
                ", changed='" + changed + '\'' +
                '}';
    }
}
