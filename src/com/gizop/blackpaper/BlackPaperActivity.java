package com.gizop.blackpaper;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class BlackPaperActivity extends Activity {
	
	public static String textOnPaper;
    Context context;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final TextView textViewPaper = (TextView)findViewById(R.id.textViewPaper);        
        context=this;
        
        // get paper text via shared preferences
        Paper paper;
        paper = new Paper(context);
        textOnPaper = paper.getNote();
        
		// set paper content
        textViewPaper.setText(textOnPaper);
        textViewPaper.requestFocusFromTouch ();
        
        // listen to text change
        textViewPaper.addTextChangedListener(new TextWatcher() {
        	
            public void afterTextChanged(Editable s) {}
            
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            
            public void onTextChanged(CharSequence s, int start, int before, int count) {             
            	
            	// get the current text from the paper
            	textOnPaper = textViewPaper.getText().toString();
                        	   
                Paper paper;
                paper = new Paper(context);
            	paper.saveNote(textOnPaper);
            	
                //update widget           	
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                ComponentName thisWidget = new ComponentName(context, WidgetActivity.class);
                remoteViews.setTextViewText(R.id.widget_paper, textOnPaper);
                
                // create an intent to launch activity
                Intent intent = new Intent(context, BlackPaperActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

                // get the layout for the app widget and attach an on-click listener to the button
                remoteViews.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

                // tell the AppWidgetManager to perform an update on the current widget                
                appWidgetManager.updateAppWidget(thisWidget, remoteViews);        	
            }
            
        });
                
    }
    
    public void onBackPresed(){
    	// home intent
    	Intent startMain = new Intent(Intent.ACTION_MAIN);
    	startMain.addCategory(Intent.CATEGORY_HOME);
    	startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(startMain);
        return;
    	}
     
}