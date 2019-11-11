package my.apps.udacity.nano.degree.space.launch.appWidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import my.apps.udacity.nano.degree.space.launch.R;
import my.apps.udacity.nano.degree.space.launch.activites.home.HomeActivity;
import my.apps.udacity.nano.degree.space.launch.utilites.Constant;

/**
 * Implementation of App Widget functionality.
 */
public class SpaceTimerWidget extends AppWidgetProvider {
    public static final String ACTION_TEXT_CHANGED = "android.appwidget.action.APPWIDGET_UPDATE";

    private static String text;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.space_timer_widget);

        Intent openTimer = new Intent(context, HomeActivity.class);
        PendingIntent timerPendingIntent = PendingIntent.getActivity(context, 0, openTimer, 0);
        views.setOnClickPendingIntent(R.id.txt_widget, timerPendingIntent);
        // Instruct the widget manager to update the widget
        views.setTextViewText(R.id.txt_widget, text);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(ACTION_TEXT_CHANGED)) {
            // handle intent here
            String s = intent.getStringExtra(Constant.WIDGET_KEY);
            this.text = s;
        }
    }
}

