package com.pearlmaknun.uts.myfavoritemovies;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import static com.pearlmaknun.uts.myfavoritemovies.DatabaseContract.FavoriteColumns.JUDUL;
import static com.pearlmaknun.uts.myfavoritemovies.DatabaseContract.FavoriteColumns.POSTER_PATH;
import static com.pearlmaknun.uts.myfavoritemovies.DatabaseContract.FavoriteColumns.SINOPSIS;
import static com.pearlmaknun.uts.myfavoritemovies.DatabaseContract.FavoriteColumns.TGL_RELEASE;
import static com.pearlmaknun.uts.myfavoritemovies.DatabaseContract.getColumnString;

public class MyFavoriteAdapter extends CursorAdapter {

    public MyFavoriteAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.adapter_my_fav_movie, viewGroup, false);
    }


    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null){
            TextView tvTitle = view.findViewById(R.id.judul);
            TextView tvDate = view.findViewById(R.id.tanggalrilis);
            TextView tvDescription = view.findViewById(R.id.sinopsis);
            ImageView Poster = view.findViewById(R.id.poster);

            tvTitle.setText(getColumnString(cursor,JUDUL));
            tvDescription.setText(getColumnString(cursor,SINOPSIS));
            tvDate.setText(getColumnString(cursor,TGL_RELEASE));
            Glide.with(context)
                    .load("http://image.tmdb.org/t/p/w185"+getColumnString(cursor,POSTER_PATH))
                    .into(Poster);
        }
    }
}
