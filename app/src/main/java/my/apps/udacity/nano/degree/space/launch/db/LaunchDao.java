package my.apps.udacity.nano.degree.space.launch.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.Launches;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.Rocket;

@Dao
public interface LaunchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Launches launches);

    @Query("SELECT * FROM launch_table")
    LiveData<Launches> getAllData();


    @Query("DELETE FROM launch_table")
    void deleteAllNotes();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRocket(Rocket rocket);

    @Query("SELECT * FROM rocket_table")
    LiveData<Rocket> getAllDataRocket();


    @Query("DELETE FROM rocket_table")
    void deleteAllRocketData();
}
