package com.gizop.blackpaper;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetActivity extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
      
        // perform this loop procedure for each app widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];
            
            // create an intent to launch activity
            Intent intent = new Intent(context, BlackPaperActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // get the layout for the app widget and attach an on-click listener to the button
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            
            ///////////////////         
            Paper paper;
            paper = new Paper(context);
            String textOnPaper = paper.getNote();
            remoteViews.setTextViewText(R.id.widget_paper, textOnPaper);
            /////////////////
            
            remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

            // tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
        
    }
    	
}