package my.apps.udacity.nano.degree.space.launch.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.Launches;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.Rocket;

public class LaunchViewModel extends AndroidViewModel {
    private LaunchRepository repository;
    private LiveData<Launches> allData;
    private LiveData<Rocket> allDataRocekt;


    public LaunchViewModel(@NonNull Application application) {
        super(application);
        repository = new LaunchRepository(application);
        allData = repository.getAllNotes();
        allDataRocekt = repository.getAllRocketData();
    }

    public void insert(Launches launches) {
        repository.insert(launches);
    }

    public void deleteAllLaunch() {
        repository.deleteAllDataLaunch();
    }

    public LiveData<Launches> getAllData() {
        return allData;
    }


    public void insertRocekt(Rocket rocket) {
        repository.insertRocket(rocket);
    }

    public void deleteAllRocekt() {
        repository.deleteAllRocketData();
    }

    public LiveData<Rocket> getAllDataRocket() {
        return allDataRocekt;
    }
}
