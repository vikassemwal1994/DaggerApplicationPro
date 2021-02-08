package com.daggerapplication.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionMgmt {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    private static final String PREF_NAME = "vipGroup";
    private static final String KEY_LANGUAGE = "selected_language1";


    public SessionMgmt(Context context) {
        this._context = context;
        int PRIVATE_MODE = 0;
        if (context != null) {
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        }
    }

    public void setKeyLanguage(String language) {
        editor.putString(KEY_LANGUAGE, language);
        editor.commit();
    }

    public String getKeyLanguage() {
        return pref.getString(KEY_LANGUAGE, "");
    }

}
