package com.example.user.iakpopularmovie.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by iswandi on 12/10/2017.
 */

public class MovieDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "film.db";

    //kalau tiap kali ganti database harus dinaikan +1
    private static final int DATABASE_VERSION = 2;

    private static final String TAG = "MovieDbHelper";
    //logt

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //bertanggung jwb untuk membuat database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_WEATHER_TABLE =

                "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " (" +
                        MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MovieContract.MovieEntry.COLUMN_ID + " INTEGER, " +
                        MovieContract.MovieEntry.COLUMN_JUDUL + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_POSTER + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_POPULAR + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_RD + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_BACKDROP + " TEXT, " +
                        //2 tambah sini
                        "UNIQUE (" + MovieContract.MovieEntry.COLUMN_JUDUL + ") ON CONFLICT REPLACE);";

        Log.d(TAG, "onCreate: "+SQL_CREATE_WEATHER_TABLE);

        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
    }
//bertanggung jwb untuk memastikan skema database selalu deperbaharui
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}
