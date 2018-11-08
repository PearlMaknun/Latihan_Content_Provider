package com.pearlmaknun.moviecatalogue.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.pearlmaknun.moviecatalogue.R;
import com.pearlmaknun.moviecatalogue.adapter.FavoriteAdapter;
import com.pearlmaknun.moviecatalogue.database.FavoriteHelper;
import static android.provider.BaseColumns._ID;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.CONTENT_URI;

public class FavoriteActivity extends AppCompatActivity {

    private Cursor list;
    FavoriteHelper favoriteHelper;
    private FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        RecyclerView recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = this.getContentResolver().query(CONTENT_URI,null,null,null,_ID + " DESC" );
        favoriteAdapter = new FavoriteAdapter(this);
        favoriteAdapter.setListFavorite(list);
        recyclerView.setAdapter(favoriteAdapter);
    }
}
