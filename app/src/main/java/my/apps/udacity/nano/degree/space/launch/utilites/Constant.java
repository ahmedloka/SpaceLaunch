package my.apps.udacity.nano.degree.space.launch.utilites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONObject;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import libs.mjn.prettydialog.PrettyDialog;
import my.apps.udacity.nano.degree.space.launch.R;
import retrofit2.HttpException;

import static android.content.Context.MODE_PRIVATE;

public final class Constant {
    public static final String SHARED_PREFERENCE = "MySharedPreference";
    public static final String LANGUAGE = "LANG";
    public static final String BASE_URL_HTTP = "https://launchlibrary.net/".trim();
    public static final String DETAILS_IMG = "DEATAILS_IMG";
    public static final String DETAILS_NAME = "DETAILS_NAME";
    public static final String DETAILS_TIME = "DETAILS_DATE";
    public static final String DETAILS_DESC = "DEAILS_DESC";
    public static final String FROM_WHERE = "FROM_WHERE";
    public static final String FROM_ALL_FRAGEMNT = "ALL_FRAGEMNT";
    public static final String FROM_HOME = "FROM_HOME";
    public static final String FROM_SETTINGS = "FROM_SETTINGS";
    public static final String DETAILS_END_DATE = "DETRAILS_END_DATE";
    public static final String MAP_URL = "MAP_URL";
    public static final String NO_MAP = "NO_MAP";
    public static final String WIDGET_KEY = "WIDGET_KEY";


    public static String getLng(Context context) {
        SharedPreferences mSharedPreferences;

        mSharedPreferences = context.getSharedPreferences(Constant.SHARED_PREFERENCE, MODE_PRIVATE);
        return mSharedPreferences.getString(Constant.LANGUAGE, Locale.getDefault().getLanguage());
    }

    public static void changeLang(Context mContext, String countryCode) {
        Resources res = mContext.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(countryCode.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        view = ((AppCompatActivity) context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


    }

    public static void hideStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = activity.getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }


    public static void clearStackIntent(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);

        context.startActivity(intent);
        ((AppCompatActivity) context).finish();

    }

    public static void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_right); //TODO (Other Option)R.anim.layout_animation

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public static void handleError(Context context, Throwable throwable) {
        String message = "";
        if (throwable instanceof HttpException) {
            try {
                HttpException error = (HttpException) throwable;
                JSONObject jsonObject = new JSONObject(((HttpException) throwable).response().errorBody().string());
                message = jsonObject.getString("msg");
            } catch (Exception e) {
                message = throwable.getMessage();
            }
            Constant.getErrorDependingOnResponse(context, message);

        }
    }

    private static void getErrorDependingOnResponse(Context context, String response) {
        String message;
        switch (response) {
            case "1":
                message = "error one";
                break;
            default:
                message = context.getString(R.string.default_error);
                break;
        }

        Constant.showErrorDialog(context, message);

        Log.d("ERROR_TAG", "handleError: " + response);
    }

    public static void showErrorDialog(Context context, String message) {
        PrettyDialog prettyDialog = new PrettyDialog(context);

        prettyDialog.setCancelable(true);
        prettyDialog
                .setIcon(R.drawable.ic_error)
                .setTitle(message)
                .addButton(context.getString(R.string.ok), android.R.color.white, R.color.pdlg_color_red, prettyDialog::dismiss)
                .show();
    }

    public static void showInformationDialog(Context context, String message) {
        PrettyDialog prettyDialog = new PrettyDialog(context);

        prettyDialog.setCancelable(true);
        prettyDialog
                .setIcon(R.drawable.ic_information)
                .setTitle(message)
                .addButton(context.getString(R.string.ok), android.R.color.white, R.color.color_blue, prettyDialog::dismiss)
                .show();
    }

}
