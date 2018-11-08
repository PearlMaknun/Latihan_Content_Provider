package com.pearlmaknun.moviecatalogue.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DatabaseContract {

    public static final String AUTHORITY = "com.pearlmaknun.moviecatalogue";
    public static final String SCHEME = "content";

    private DatabaseContract(){

    }
    public static final class FavoriteColumns implements BaseColumns {
        public static String TABLE_NAME = "tbmoviefavorite";
        public static String JUDUL = "judul";
        public static String SINOPSIS = "sinopsis";
        public static String TGL_RELEASE = "tgl_release";
        public static String POSTER_PATH = "poster_path";
        public static String BACKDROP_PATH = "backdrop_path";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();

    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

}