package my.apps.udacity.nano.degree.space.launch.activites.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.fragemnts.AllLaunchesDataFragemnt;
import my.apps.udacity.nano.degree.space.launch.fragemnts.HomeFragment;
import my.apps.udacity.nano.degree.space.launch.fragemnts.SettingsFragment;
import my.apps.udacity.nano.degree.space.launch.utilites.Constant;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toTestCrash();

        sharedPreferences = getSharedPreferences(Constant.SHARED_PREFERENCE, MODE_PRIVATE);
        try {
            if (sharedPreferences.getString(Constant.FROM_WHERE, Constant.FROM_HOME).equals(Constant.FROM_HOME)) {
                loadFragment(new HomeFragment());
            } else if (sharedPreferences.getString(Constant.FROM_WHERE, Constant.FROM_HOME).equals(Constant.FROM_ALL_FRAGEMNT)) {
                loadFragment(new AllLaunchesDataFragemnt());
            } else {
                loadFragment(new SettingsFragment());
            }


        } catch (Exception ignored) {
            //loading the default fragment
            loadFragment(new HomeFragment());
        }


        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private void toTestCrash() {
        Button crashButton = new Button(this);
        //TODO MAKE IT GONE
        crashButton.setVisibility(View.GONE);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Crashlytics.getInstance().crash(); // Force a crash
            }
        });

        addContentView(crashButton, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.all_launches_nav:
                fragment = new AllLaunchesDataFragemnt();
                break;

            case R.id.upcomuing_launch_nav:
                fragment = new HomeFragment();
                break;

            case R.id.settings_nav:
                fragment = new SettingsFragment();
                break;
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


}
