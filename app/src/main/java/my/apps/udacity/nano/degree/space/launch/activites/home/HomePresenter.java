package my.apps.udacity.nano.degree.space.launch.activites.home;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.dialogs.DialogLoader;
import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.LaunchData;
import my.apps.udacity.nano.degree.space.launch.network.NetworkUtil;
import my.apps.udacity.nano.degree.space.launch.utilites.Constant;
import my.apps.udacity.nano.degree.space.launch.utilites.Validation;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class HomePresenter {

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final CompositeSubscription mSubscriptions;
    private final HomeInterface homeInterface;
    private FragmentManager fragmentManager;

    public HomePresenter(Context context, HomeInterface homeInterface) {
        this.context = context;
        mSubscriptions = new CompositeSubscription();
        fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        sharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        this.homeInterface = homeInterface;
    }

//    public void getAllLaunchesData() {
//        if (Validation.isConnected(context)) {
//            if (dialogLoader.isAdded()) {
//                return;
//            } else
//                dialogLoader.show(fragmentManager, "");
//
//            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
//                    .getAllLaunches()
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(this::handleResponseAllData, this::handleError));
//        } else {
//            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
//        }
//
//    }

    public void getFirstLaunchInResultData(int number) {
        if (Validation.isConnected(context)) {
            mSubscriptions.add(NetworkUtil.getRetrofitNoHeader()
                    .getLaunchesData(number)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseAllData, this::handleError));
        } else {
            Constant.showErrorDialog(context, context.getString(R.string.pls_check_connection));
        }

    }

    private void handleResponseAllData(LaunchData launchData) {


        homeInterface.getLaunchData(launchData);

    }


//    private void handleResponseAllData(LaunchesRoot launchesRoot) {
//        if (dialogLoader.isAdded()) {
//            dialogLoader.dismiss();
//        }
//
//        homeInterface.getAllData(launchesRoot);
//    }

    private void handleError(Throwable throwable) {


        Constant.handleError(context, throwable);
    }


}
