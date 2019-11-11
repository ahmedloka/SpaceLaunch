package my.apps.udacity.nano.degree.space.launch.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.NavUtils;
import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.utilites.CairoBoldTextView;
import my.apps.udacity.nano.degree.space.launch.utilites.CairoRegularTextView;
import my.apps.udacity.nano.degree.space.launch.utilites.Constant;
import my.apps.udacity.nano.degree.space.launch.utilites.CountLaunchDownTimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    private AppCompatImageView img;
    private CairoBoldTextView launchName;
    private CairoRegularTextView date;
    private LinearLayoutCompat linearTimer;
    private CairoBoldTextView txtDays;
    private CairoBoldTextView txtHrs;
    private CairoBoldTextView txtMins;
    private CairoBoldTextView txtSecs;
    private CairoRegularTextView txtDetails;
    private CairoRegularTextView map;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        NavUtils.navigateUpFromSameTask(this);
        Animatoo.animateSwipeRight(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initViews();

        setDataToViews();
    }

    private void setDataToViews() {
        if (getIntent().hasExtra(Constant.DETAILS_NAME)) {
            launchName.setText(getIntent().getStringExtra(Constant.DETAILS_NAME));
        }
        if (getIntent().hasExtra(Constant.DETAILS_DESC)) {
            txtDetails.setText(getIntent().getStringExtra(Constant.DETAILS_DESC));
        }

        if (getIntent().hasExtra(Constant.DETAILS_TIME)) {
            date.setText(getIntent().getStringExtra(Constant.DETAILS_TIME));
        }

        if (getIntent().hasExtra(Constant.DETAILS_IMG)) {
            Picasso.get()
                    .load(getIntent().getStringExtra(Constant.DETAILS_IMG))
                    .fit()
                    .centerCrop()
                    .into(img);
        }

        if (getIntent().hasExtra(Constant.DETAILS_END_DATE)) {
            String endDate = getIntent().getStringExtra(Constant.DETAILS_END_DATE);

            Calendar calendar = Calendar.getInstance();
            long currentDate = calendar.getTime().getTime();

            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");

            try {
                // Date dateOne = dateFormat.parse(strDate);
                Date dateTwo = dateFormat.parse(endDate);

                long difference = dateTwo.getTime() - currentDate;
                CountLaunchDownTimer countDownTimer = new CountLaunchDownTimer(difference, 1000, txtDays, txtHrs, txtMins, txtSecs);
                countDownTimer.start();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void initViews() {
        img = findViewById(R.id.img);
        launchName = findViewById(R.id.launch_name);
        date = findViewById(R.id.date);
        linearTimer = findViewById(R.id.linear_timer);
        txtDays = findViewById(R.id.txt_days);
        txtHrs = findViewById(R.id.txt_hrs);
        txtMins = findViewById(R.id.txt_mins);
        txtSecs = findViewById(R.id.txt_secs);
        txtDetails = findViewById(R.id.txt_details);
        map = findViewById(R.id.map);

        map.setOnClickListener(v -> {
            Intent openMap = new Intent(DetailsActivity.this, MapsActivity.class);
            if (getIntent().hasExtra(Constant.MAP_URL)) {
                if (getIntent().getStringExtra(Constant.MAP_URL).equals(Constant.NO_MAP)) {
                    Constant.showInformationDialog(DetailsActivity.this, getString(R.string.no_map));
                } else {
                    openMap.putExtra(Constant.MAP_URL, getIntent().getStringExtra(Constant.MAP_URL));
                    startActivity(openMap);
                    Animatoo.animateSwipeLeft(DetailsActivity.this);
                }
            }

        });


    }
}
