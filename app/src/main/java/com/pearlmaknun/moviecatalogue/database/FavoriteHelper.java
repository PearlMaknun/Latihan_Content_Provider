package com.pearlmaknun.moviecatalogue.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.pearlmaknun.moviecatalogue.model.Favorite;
import java.util.ArrayList;
import static android.provider.BaseColumns._ID;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.BACKDROP_PATH;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.JUDUL;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.POSTER_PATH;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.SINOPSIS;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.TABLE_NAME;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.TGL_RELEASE;

public class FavoriteHelper {

    private static String DATABASE_TABLE = TABLE_NAME;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public FavoriteHelper(Context context){
        this.context = context;
    }

    public FavoriteHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<Favorite> query(){
        ArrayList<Favorite> arrayList = new ArrayList<Favorite>();
        Cursor cursor = database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null,_ID +" DESC"
                ,null);
        cursor.moveToFirst();
        Favorite favorite;
        if (cursor.getCount()>0) {
            do {

                favorite = new Favorite();
                favorite.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                favorite.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(JUDUL)));
                favorite.setSinopsis(cursor.getString(cursor.getColumnIndexOrThrow(SINOPSIS)));
                favorite.setTanggalrilis(cursor.getString(cursor.getColumnIndexOrThrow(TGL_RELEASE)));
                favorite.setPosterpath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));
                favorite.setBackdroppath(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_PATH)));

                arrayList.add(favorite);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Favorite favorite){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(JUDUL, favorite.getJudul());
        initialValues.put(SINOPSIS, favorite.getSinopsis());
        initialValues.put(TGL_RELEASE, favorite.getTanggalrilis());
        initialValues.put(POSTER_PATH, favorite.getPosterpath());
        initialValues.put(BACKDROP_PATH, favorite.getBackdroppath());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int update(Favorite favorite){
        ContentValues args = new ContentValues();
        args.put(JUDUL, favorite.getJudul());
        args.put(SINOPSIS, favorite.getSinopsis());
        args.put(TGL_RELEASE, favorite.getTanggalrilis());
        args.put(POSTER_PATH, favorite.getPosterpath());
        args.put(BACKDROP_PATH, favorite.getBackdroppath());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + favorite.getId() + "'", null);
    }

    public int delete(int id){
        return database.delete(TABLE_NAME, _ID + " = '"+id+"'", null);
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null
                ,_ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }
    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID + " DESC");
    }
    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }
    public int updateProvider(String id,ContentValues values){
        return database.update(DATABASE_TABLE,values,_ID +" = ?",new String[]{id} );
    }
    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }
}
