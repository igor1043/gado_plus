
package br.edu.farol.gadoplus.storage.database.preference;


import android.content.Context;
import android.content.SharedPreferences;


public class PreferenceManager {

    private static final String FILE_NAME = "preferences";
    private static final String FIRST_START = "first_start";
    private static SharedPreferences mPreferences;
    public static void initialize(Context context) {
        mPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void setIsFirstStartDone(boolean done) {
        mPreferences.edit().putBoolean(FIRST_START, done).apply();
    }

    public static boolean isFirstStartDone() {
        return mPreferences.getBoolean(FIRST_START, false);
    }
}