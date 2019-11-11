package my.apps.udacity.nano.degree.space.launch.fragemnts;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;
import java.util.Objects;

import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.activites.beforeHome.SignInActivity;
import my.apps.udacity.nano.degree.space.launch.activites.home.HomeActivity;
import my.apps.udacity.nano.degree.space.launch.utilites.CairoRegularTextView;
import my.apps.udacity.nano.degree.space.launch.utilites.Constant;


public class SettingsFragment extends Fragment {

    private CairoRegularTextView txtChangeLang;
    private CairoRegularTextView txtLogOut;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private FirebaseAuth mFireBaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        sharedPreferences = getContext().getSharedPreferences(Constant.SHARED_PREFERENCE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(Constant.FROM_WHERE, Constant.FROM_SETTINGS);
        editor.apply();

        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        mFireBaseAuth = FirebaseAuth.getInstance();

        txtChangeLang = rootView.findViewById(R.id.txt_change_lang);
        txtChangeLang.setOnClickListener(v -> {
            changeLang();

        });


        txtLogOut = rootView.findViewById(R.id.txt_log_out);
        txtLogOut.setOnClickListener(v -> {
            mFireBaseAuth.signOut();
            startActivity(new Intent(getContext(), SignInActivity.class));
            Animatoo.animateSplit(getContext());
        });


    }

    private void changeLang() {
        Dialog dialog;
        Rect displayRectangle = new Rect();
        Window window = Objects.requireNonNull(getActivity().getWindow());
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        //  builder                        = new AlertDialog.Builder(getContext());
        @SuppressLint("InflateParams")
        View mview = getLayoutInflater().inflate(R.layout.dialog_change_lang, null);
        dialog = new BottomSheetDialog(getContext());
        dialog.setContentView(mview);
        dialog.show();
        AppCompatTextView arabic = dialog.findViewById(R.id.arabic);
        AppCompatTextView english = dialog.findViewById(R.id.english);
        Button cancel = dialog.findViewById(R.id.cancel);
        arabic.setOnClickListener(v1 -> {

            Log.d("lang", "onCreate: " + "you click me");
            editor.putString(Constant.LANGUAGE, "ar");
            editor.apply();

            Constant.changeLang(getContext(), Objects.requireNonNull(sharedPreferences.getString(Constant.LANGUAGE, Locale.getDefault().getDisplayLanguage())));


            dialog.dismiss();

            Intent intent = new Intent(getContext(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Animatoo.animateZoom(getContext());


        });
        english.setOnClickListener(v12 -> {

            Log.d("lang", "onCreate: " + "you click me");

            editor.putString(Constant.LANGUAGE, "en");
            editor.apply();

            Constant.changeLang(getContext(), Objects.requireNonNull(sharedPreferences.getString(Constant.LANGUAGE, Locale.getDefault().getDisplayLanguage())));

            dialog.dismiss();

            Intent intent = new Intent(getContext(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Animatoo.animateZoom(getContext());


        });
        cancel.setOnClickListener(view -> dialog.cancel());

    }


}
