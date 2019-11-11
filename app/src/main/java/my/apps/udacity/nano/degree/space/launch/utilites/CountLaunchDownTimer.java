package my.apps.udacity.nano.degree.space.launch.utilites;

import android.os.CountDownTimer;
import android.util.Log;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CountLaunchDownTimer extends CountDownTimer {


    private final CairoBoldTextView mTxtDay;
    private final CairoBoldTextView mTxtHrs;
    private final CairoBoldTextView mTxtMin;
    private final CairoBoldTextView mTxtSec;

    public CountLaunchDownTimer(long millisInFuture, long countDownInterval, CairoBoldTextView
            mTxtDay, CairoBoldTextView mTxtHrs, CairoBoldTextView mTxtMin, CairoBoldTextView mTxtSec) {
        super(millisInFuture, countDownInterval);

        this.mTxtDay = mTxtDay;
        this.mTxtHrs = mTxtHrs;
        this.mTxtMin = mTxtMin;
        this.mTxtSec = mTxtSec;
    }

    @Override
    public void onTick(long millisUntilFinished) {

        long days = TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished));

        long hours = ((TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished))));

        long min = ((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))));

        long sec = (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));


        String daysString = String.format(Locale.US, "%02d", days);
        String hrsString = String.format(Locale.US, "%02d", hours);
        String minsString = String.format(Locale.US, "%02d", min);
        String secsString = String.format(Locale.US, "%02d", sec);


        if (!daysString.equals("00"))
            mTxtDay.setText(daysString);
        if (!hrsString.equals("00"))
            mTxtHrs.setText(hrsString);
        if (!minsString.equals("00"))
            mTxtMin.setText(minsString);
        if (!secsString.equals("00"))
            mTxtSec.setText(secsString);

    }


    @Override
    public void onFinish() {
        Log.d("TAG", "onFinish: DONE");


    }


}