package br.edu.farol.gadoplus.storage.database.preference

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {
    private const val FILE_NAME = "preferences"
    private const val FIRST_START = "first_start"
    private var mPreferences: SharedPreferences? = null
    fun initialize(context: Context) {
        mPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    var isFirstStartDone: Boolean
        get() = mPreferences!!.getBoolean(FIRST_START, false)
        set(done) {
            mPreferences!!.edit().putBoolean(FIRST_START, done).apply()
        }
}