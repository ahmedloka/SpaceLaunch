package my.apps.udacity.nano.degree.space.launch.models.AllData;

import java.util.List;

public class LaunchesRoot {

    private List<Launches> launches;

    private int total;

    private int offset;

    private int count;

    public void setLaunches(List<Launches> launches) {
        this.launches = launches;
    }

    public List<Launches> getLaunches() {
        return this.launches;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return "LaunchesRoot{" +
                "launches=" + launches +
                ", total=" + total +
                ", offset=" + offset +
                ", count=" + count +
                '}';
    }
}
