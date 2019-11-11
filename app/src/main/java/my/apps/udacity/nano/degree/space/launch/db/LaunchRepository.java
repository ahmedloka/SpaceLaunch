package my.apps.udacity.nano.degree.space.launch.db;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.Launches;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.Rocket;

public class LaunchRepository {
    private LaunchDao launchDao;
    private LiveData<Launches> allData;
    private LiveData<Rocket> allDataRocekt;


    public LaunchRepository(Application application) {
        LaunchDatabase database = LaunchDatabase.getInstance(application);
        launchDao = database.launchDao();
        allData = launchDao.getAllData();
        allDataRocekt = launchDao.getAllDataRocket();
    }

    public void insert(Launches launches) {
        new InsertDataAsyncTask(launchDao).execute(launches);
    }

    public void insertRocket(Rocket rocket) {
        new InsertDataRocketAsyncTask(launchDao).execute(rocket);
    }

    public LiveData<Launches> getAllNotes() {
        return allData;
    }

    public LiveData<Rocket> getAllRocketData() {
        return allDataRocekt;
    }


    public void deleteAllDataLaunch() {
        new DeleteAllDataAsyncTask(launchDao).execute();
    }


    public void deleteAllRocketData() {
        new DeleteAllDataRocektAsyncTask(launchDao).execute();
    }

    private static class InsertDataRocketAsyncTask extends AsyncTask<Rocket, Void, Void> {
        private LaunchDao launchDao;

        private InsertDataRocketAsyncTask(LaunchDao launchDao) {
            this.launchDao = launchDao;
        }

        @Override
        protected Void doInBackground(Rocket... notes) {
            launchDao.insertRocket(notes[0]);
            return null;
        }
    }

    private static class InsertDataAsyncTask extends AsyncTask<Launches, Void, Void> {
        private LaunchDao launchDao;

        private InsertDataAsyncTask(LaunchDao launchDao) {
            this.launchDao = launchDao;
        }

        @Override
        protected Void doInBackground(Launches... notes) {
            launchDao.insert(notes[0]);
            return null;
        }
    }


    private static class DeleteAllDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private LaunchDao launchDao;

        private DeleteAllDataAsyncTask(LaunchDao launchDao) {
            this.launchDao = launchDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            launchDao.deleteAllNotes();
            return null;
        }
    }

    private static class DeleteAllDataRocektAsyncTask extends AsyncTask<Void, Void, Void> {
        private LaunchDao launchDao;

        private DeleteAllDataRocektAsyncTask(LaunchDao launchDao) {
            this.launchDao = launchDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            launchDao.deleteAllRocketData();
            return null;
        }
    }


}
