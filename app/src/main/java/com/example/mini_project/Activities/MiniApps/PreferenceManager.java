package com.example.mini_project.Activities.MiniApps;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class PreferenceManager {

    private static String CLASS_NAME = "ISAMMPreferenceManager";

    private static PreferenceManager mInstance;

    private SharedPreferences mPreferences;



    // Key
    public final static String USER_SESSION_INFORMATION = "user_session_information";
    public final static String IS_LOGGED_IN = "is_logged_in";
    public final static String IMC_LIST = "imcList";


    private PreferenceManager(Context context)
    {
        String sharedPreferencesFileName = context.getPackageName() + "_preferences";
        mPreferences = context.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE);
    }


    public static synchronized PreferenceManager getInstance(Context context)
    {
        if (mInstance == null) {
            mInstance = new PreferenceManager(context);
        }
        return mInstance;
    }

    public boolean getBoolean(String key, boolean defValue)
    {
        boolean value = mPreferences.getBoolean(key, defValue);
        return value;
    }

    public float getFloat(String key, float defValue)
    {
        Float value = mPreferences.getFloat(key, defValue);
        return value;
    }

    public int getInt(String key, int defValue)
    {
        int value = defValue;
        try {
            value = mPreferences.getInt(key, defValue);
        } catch (ClassCastException e) {
            value = Integer.parseInt(mPreferences.getString(key, String.valueOf(defValue)));
            putInt(key, value);
        }
        return value;
    }

    public long getLong(String key, long defValue)
    {
        Long value;
        try {
            value = mPreferences.getLong(key, defValue);
        } catch (ClassCastException e) {
            value = Long.parseLong(mPreferences.getString(key, String.valueOf(defValue)));
            putLong(key, value);
        }
        return value;
    }

    public String getString(String key, String defValue)
    {
        String value = mPreferences.getString(key, defValue);
        return value;
    }


    public void putBoolean(String key, boolean value)
    {
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public void putFloat(String key, float value)
    {
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putFloat(key, value);
        edit.apply();
    }

    public void putInt(String key, int value)
    {
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public void putLong(String key, long value)
    {
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public void putString(String key, String value)
    {
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putString(key, value);
        edit.apply();
    }


    public boolean contains(String key)
    {
        return mPreferences.contains(key);
    }

    public void remove(String key)
    {
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.remove(key);
        edit.apply();
    }

    public void clearAll()
    {

        Map<String, ?> preferences = mPreferences.getAll();

        SharedPreferences.Editor edit = mPreferences.edit();
        for (Map.Entry<String, ?> me : preferences.entrySet()) {
            String key = me.getKey();
            edit.remove(key);
        }
        edit.apply();

    }

    public interface MyAppPreferenceDefaultValues {

        /**
         * User default parameters
         **/
        String DEFAULT_USER_SESSION_INFORMATION = "";


    }

}


