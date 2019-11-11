package my.apps.udacity.nano.degree.space.launch.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.Launches;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.Rocket;

@Database(entities = {Launches.class, Rocket.class}, version = 6)
public abstract class LaunchDatabase extends RoomDatabase {

    private static LaunchDatabase instance;

    public abstract LaunchDao launchDao();

    public static synchronized LaunchDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LaunchDatabase.class, context.getString(R.string.db_name))
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}