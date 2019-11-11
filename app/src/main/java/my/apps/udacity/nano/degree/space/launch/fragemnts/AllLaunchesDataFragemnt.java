package my.apps.udacity.nano.degree.space.launch.fragemnts;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.activites.DetailsActivity;
import my.apps.udacity.nano.degree.space.launch.activites.home.HomeInterface;
import my.apps.udacity.nano.degree.space.launch.activites.home.HomePresenter;
import my.apps.udacity.nano.degree.space.launch.adapters.AllDataAdapter;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.LaunchData;
import my.apps.udacity.nano.degree.space.launch.utilites.Constant;


public class AllLaunchesDataFragemnt extends Fragment implements HomeInterface, AllDataAdapter.OnClickHandler {


    private RecyclerView mRV;
    private AllDataAdapter allDataAdapter;
    private List<String> listNames, listCities, listImg, listDates;
    private List<Integer> listId;

    private HomePresenter presenter;

    private LaunchData launchData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootViews = inflater.inflate(R.layout.fragment_all_launches_data, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.FROM_WHERE, Constant.FROM_ALL_FRAGEMNT);
        editor.apply();

        initViews(rootViews);
        return rootViews;
    }

    private void initViews(View rootViews) {
        presenter = new HomePresenter(getContext(), this);

        mRV = rootViews.findViewById(R.id.rv);
        mRV.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        initRVData();
        presenter.getFirstLaunchInResultData(20);

    }

    private void initRVData() {
        listCities = new ArrayList<>();
        listDates = new ArrayList<>();
        listImg = new ArrayList<>();
        listNames = new ArrayList<>();

        listId = new ArrayList<>();
    }


    @Override
    public void getLaunchData(LaunchData launchData) {
        this.launchData = launchData;
        for (int i = 0; i < launchData.getLaunches().size(); i++) {
            listId.add(launchData.getLaunches().get(i).getId());

            try {
                listNames.add(String.valueOf(launchData.getLaunches().get(i).getName()));
            } catch (Exception ignored) {
                listNames.add("");
            }
            try {
                listImg.add(launchData.getLaunches().get(i).getRocket().getImageURL());
            } catch (Exception ignored) {
                listImg.add("");
            }
            try {
                listCities.add(String.valueOf(launchData.getLaunches().get(i).getRocket().getAgencies().get(i).getCountryCode()));
            } catch (Exception ignored) {
                listCities.add("");
            }
            try {
                listDates.add(String.valueOf(launchData.getLaunches().get(i).getWindowstart()));
            } catch (Exception ignored) {
                listDates.add("");
            }

        }

        allDataAdapter = new AllDataAdapter(getContext(), listNames, listCities, listImg, listDates, this);
        mRV.setAdapter(allDataAdapter);
        Constant.runLayoutAnimation(mRV);
    }

    @Override
    public void onClick(int position) {
        // Toast.makeText(getContext(), , Toast.LENGTH_SHORT).show();
        //int i = (listId.get(position));

        /*
         Log.d("DATA_IMG", "onClick: " + launchData.getLaunches().get(position).getRocket().getImageURL());
        Log.d("DATA_NAME", "onClick: " + launchData.getLaunches().get(position).getName());
        Log.d("DATA_TIME", "onClick: " + launchData.getLaunches().get(position).getWindowstart() + " - " + launchData.getLaunches().get(position).getWindowend());
        Log.d("DATA_DETAILS", "onClick: " + launchData.getLaunches().get(position).getMissions().get(position).getDescription());
         */
        Intent openDetails = new Intent(getContext(), DetailsActivity.class);
        try {
            openDetails.putExtra(Constant.DETAILS_IMG, launchData.getLaunches().get(position).getRocket().getImageURL());
        } catch (Exception ignored) {

        }

        try {
            openDetails.putExtra(Constant.DETAILS_NAME, launchData.getLaunches().get(position).getName());
        } catch (Exception ignored) {

        }

        try {
            openDetails.putExtra(Constant.DETAILS_END_DATE ,launchData.getLaunches().get(position).getWindowend());
            openDetails.putExtra(Constant.DETAILS_TIME, launchData.getLaunches().get(position).getWindowstart() + " - " + launchData.getLaunches().get(position).getWindowend());
        } catch (Exception ignored) {

        }
        try {
            openDetails.putExtra(Constant.DETAILS_DESC, launchData.getLaunches().get(position).getMissions().get(0).getDescription());
        } catch (Exception ignored) {
            openDetails.putExtra(Constant.DETAILS_DESC, getContext().getString(R.string.there_is_no));
        }

        try {
            openDetails.putExtra(Constant.MAP_URL, launchData.getLaunches().get(position).getLocation().getPads().get(0).getMapURL());
        } catch (Exception ignored) {
            openDetails.putExtra(Constant.MAP_URL, Constant.NO_MAP);
        }
        startActivity(openDetails);
        Animatoo.animateSwipeLeft(getContext());
    }
}
