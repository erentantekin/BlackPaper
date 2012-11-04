package com.gizop.blackpaper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Paper {
     private static final String APP_SHARED_PREFS = "paper";
     private SharedPreferences sharedPreferences;
     private Editor editor;

     public Paper(Context context)
     {
         this.sharedPreferences = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
         this.editor = sharedPreferences.edit();
     }

     public String getNote() {
         return sharedPreferences.getString("note", "");
     }

     public void saveNote(String text) {
         editor.putString("note", text);
         editor.commit();
     }
     
}