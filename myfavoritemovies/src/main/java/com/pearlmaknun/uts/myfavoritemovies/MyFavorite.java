package com.pearlmaknun.uts.myfavoritemovies;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import static android.provider.BaseColumns._ID;
import static com.pearlmaknun.uts.myfavoritemovies.DatabaseContract.getColumnInt;
import static com.pearlmaknun.uts.myfavoritemovies.DatabaseContract.getColumnString;

public class MyFavorite implements Parcelable {
    private long id;
    private String judul;
    private String sinopsis;
    private String tanggalrilis;
    private String posterpath;
    private String backdroppath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTanggalrilis() {
        return tanggalrilis;
    }

    public void setTanggalrilis(String tanggalrilis) {
        this.tanggalrilis = tanggalrilis;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public String getBackdroppath() {
        return backdroppath;
    }

    public void setBackdroppath(String backdroppath) {
        this.backdroppath = backdroppath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.sinopsis);
        dest.writeString(this.tanggalrilis);
        dest.writeString(this.posterpath);
        dest.writeString(this.backdroppath);
    }

    public MyFavorite() {

    }

    public MyFavorite(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.judul = getColumnString(cursor, DatabaseContract.FavoriteColumns.JUDUL);
        this.sinopsis = getColumnString(cursor, DatabaseContract.FavoriteColumns.SINOPSIS);
        this.tanggalrilis = getColumnString(cursor, DatabaseContract.FavoriteColumns.TGL_RELEASE);
        this.posterpath = getColumnString(cursor, DatabaseContract.FavoriteColumns.POSTER_PATH);
        this.backdroppath = getColumnString(cursor, DatabaseContract.FavoriteColumns.BACKDROP_PATH);
    }

    protected MyFavorite(Parcel in) {
        this.id = in.readLong();
        this.judul = in.readString();
        this.sinopsis = in.readString();
        this.tanggalrilis = in.readString();
        this.posterpath = in.readString();
        this.backdroppath = in.readString();
    }

    public static final Parcelable.Creator<MyFavorite> CREATOR = new Parcelable.Creator<MyFavorite>() {
        @Override
        public MyFavorite createFromParcel(Parcel source) {
            return new MyFavorite(source);
        }

        @Override
        public MyFavorite[] newArray(int size) {
            return new MyFavorite[size];
        }
    };
}
