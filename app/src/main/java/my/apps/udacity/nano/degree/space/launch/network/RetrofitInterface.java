package my.apps.udacity.nano.degree.space.launch.network;

import my.apps.udacity.nano.degree.space.launch.models.firstLaunch.LaunchData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface RetrofitInterface {
//    @GET("1.4/launch/{id}")
//    Observable<LaunchesRoot> getAllLaunches(@Path("id") int id);

    @GET("1.4/launch/next/{id}")
    Observable<LaunchData> getLaunchesData(@Path("id") int id);
}
