package my.apps.udacity.nano.degree.space.launch.fragemnts;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.activites.home.HomeInterface;
import my.apps.udacity.nano.degree.space.launch.activites.home.HomePresenter;
import my.apps.udacity.nano.degree.space.launch.appWidget.SpaceTimerWidget;
import my.apps.udacity.nano.degree.space.launch.db.LaunchViewModel;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.LaunchData;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.Launches;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.Rocket;
import my.apps.udacity.nano.degree.space.launch.utilites.CairoBoldTextView;
import my.apps.udacity.nano.degree.space.launch.utilites.Constant;
import my.apps.udacity.nano.degree.space.launch.utilites.CountLaunchDownTimer;

public class HomeFragment extends Fragment implements HomeInterface {
    private CairoBoldTextView launchName;
    private AppCompatImageView launchImg;
    private LinearLayoutCompat linearTimer;
    private CairoBoldTextView txtDays;
    private CairoBoldTextView txtHrs;
    private CairoBoldTextView txtMins;
    private CairoBoldTextView txtSecs;

    private HomePresenter presenter;
    private LaunchViewModel launchViewModel;


    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.FROM_WHERE, Constant.FROM_HOME);
        editor.apply();

        presenter = new HomePresenter(getContext(), this);
        initViews(rootView);

        handlingOfRestOfActivity();


        handlingOfImage();

        return rootView;
    }

    private void handlingOfRestOfActivity() {
        launchViewModel = ViewModelProviders.of(this).get(LaunchViewModel.class);
        launchViewModel.getAllData().observe(this, new Observer<Launches>() {
            @Override
            public void onChanged(Launches launches) {
                try {
                    launchName.setText(launches.getName());

                    String strDate = launches.getWindowstart();
                    String endDate = launches.getWindowend();


                    Calendar calendar = Calendar.getInstance();
                    long currentDate = calendar.getTime().getTime();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
                    //"December 10, 2019 00:00:00 UTC",
                    try {
                        Date dateOne = dateFormat.parse(strDate);
                        Date dateTwo = dateFormat.parse(endDate);

                        long difference = dateTwo.getTime() - currentDate;
                        CountLaunchDownTimer countDownTimer = new CountLaunchDownTimer(difference, 1000, txtDays, txtHrs, txtMins, txtSecs);
                        countDownTimer.start();

                        Intent intent = new Intent(SpaceTimerWidget.ACTION_TEXT_CHANGED);
                        intent.putExtra(Constant.WIDGET_KEY, "Up Coming Launch Name : " + launchName.getText().toString());
                        getContext().sendBroadcast(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (NullPointerException ignored) {
                    presenter.getFirstLaunchInResultData(1);
                }


            }
        });
    }

    private void handlingOfImage() {
        launchViewModel.getAllDataRocket().observe(getActivity(), new Observer<Rocket>() {
            @Override
            public void onChanged(Rocket rocket) {
                try {
                    Picasso.get()
                            .load(rocket.getImageURL())
                            .resize(600, 200)
                            .centerCrop()
                            .placeholder(getResources().getDrawable(R.drawable.ic_startup))
                            .into(launchImg);
                } catch (NullPointerException ignored) {
                    presenter.getFirstLaunchInResultData(1);

                }
            }
        });
    }

    private void initViews(View rootView) {


        launchName = rootView.findViewById(R.id.launch_name);
        launchImg = rootView.findViewById(R.id.launch_img);
        linearTimer = rootView.findViewById(R.id.linear_timer);
        txtDays = rootView.findViewById(R.id.txt_days);
        txtHrs = rootView.findViewById(R.id.txt_hrs);
        txtMins = rootView.findViewById(R.id.txt_mins);
        txtSecs = rootView.findViewById(R.id.txt_secs);

    }

    @Override
    public void getLaunchData(LaunchData launchData) {
        launchName.setText(launchData.getLaunches().get(0).getName());

        Picasso.get()
                .load(launchData.getLaunches().get(0).getRocket().getImageURL())
                .resize(600, 200)
                .centerCrop()
                .placeholder(getResources().getDrawable(R.drawable.ic_startup))
                .into(launchImg);

        String strDate = launchData.getLaunches().get(0).getWindowstart();
        String endDate = launchData.getLaunches().get(0).getWindowend();

        launchViewModel.insert(new Launches(launchData.getLaunches().get(0).getName(), launchData.getLaunches().get(0).getWindowstart()
                , launchData.getLaunches().get(0).getWindowend()));

        launchViewModel.insertRocekt(new Rocket(launchData.getLaunches().get(0).getRocket().getImageURL()));

        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTime().getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        //"December 10, 2019 00:00:00 UTC",
        try {
            Date dateOne = dateFormat.parse(strDate);
            Date dateTwo = dateFormat.parse(endDate);

            long difference = dateTwo.getTime() - currentDate;
            CountLaunchDownTimer countDownTimer = new CountLaunchDownTimer(difference, 1000, txtDays, txtHrs, txtMins, txtSecs);
            countDownTimer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }


//        try {
//            Date dateOne = dateFormat.parse(strDate);
//            Date dateTwo = dateFormat.parse(endDate);
//
//            Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
//        } catch (ParseException e) {
//            e.printStackTrace();
//            Log.d("startDate", "getAllTenderList: " + e.getMessage());
//        }

    }
}
