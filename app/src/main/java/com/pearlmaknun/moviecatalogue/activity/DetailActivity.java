package com.pearlmaknun.moviecatalogue.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pearlmaknun.moviecatalogue.R;
import com.pearlmaknun.moviecatalogue.model.Favorite;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.BACKDROP_PATH;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.CONTENT_URI;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.JUDUL;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.POSTER_PATH;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.SINOPSIS;
import static com.pearlmaknun.moviecatalogue.database.DatabaseContract.FavoriteColumns.TGL_RELEASE;

public class DetailActivity extends AppCompatActivity {

    String judul, tanggalrilis, sinopsis, backdrop, poster;
    TextView Judul, Rilis, Sinopsis;
    ImageView Poster;
    ImageButton btnfavorit;
    public static int RESULT_DELETE = 301;
    private long id, ada;
    private Favorite favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id = getIntent().getLongExtra("id", 1);
        judul = getIntent().getStringExtra("judul");
        tanggalrilis = getIntent().getStringExtra("tanggalrilis");
        sinopsis = getIntent().getStringExtra("sinopsis");
        backdrop = getIntent().getStringExtra("backdrop");
        poster = getIntent().getStringExtra("poster");
        ada = getIntent().getLongExtra("id", 1);

        //Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_LONG).show();

        Judul = findViewById(R.id.judul);
        Rilis = findViewById(R.id.tanggalrilis);
        Sinopsis = findViewById(R.id.sinopsis);
        Poster = findViewById(R.id.poster);
        btnfavorit = findViewById(R.id.fab);

        cekFavorit();

        Judul.setText(judul);
        Rilis.setText(tanggalrilis);
        Sinopsis.setText(sinopsis);
        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w500"+backdrop)
                .into(Poster);

        btnfavorit.setOnClickListener(Favoritkan);
    }

    public View.OnClickListener Favoritkan = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(cekFavorit() == true){
                Uri uri = Uri.parse(CONTENT_URI+"/"+ada);
                getContentResolver().delete(uri, null, null);
                String pesan = String.format(getResources().getString(R.string.remove_from_favorite));
                Toast.makeText(getApplicationContext(), judul+" "+pesan+uri, Toast.LENGTH_SHORT).show();
                btnfavorit.setImageDrawable(getResources().getDrawable(
                        R.drawable.ic_favorite_border_24dp));
                Intent f = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(f);
            }
            else{
                ContentValues values = new ContentValues();
                values.put(JUDUL,judul);
                values.put(BACKDROP_PATH,backdrop);
                values.put(TGL_RELEASE,tanggalrilis);
                values.put(SINOPSIS,sinopsis);
                values.put(POSTER_PATH,poster);

                getContentResolver().insert(CONTENT_URI,values);
                String pesan = String.format(getResources().getString(R.string.add_to_favorite));

                Toast.makeText(getApplicationContext(), judul +" "+ pesan, Toast.LENGTH_SHORT).show();
                setResult(101);
                Intent g = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(g);
            }
        }
    };

    public boolean cekFavorit(){
        Uri uri = Uri.parse(CONTENT_URI+"");
        boolean favorite = false;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        String getTitle;
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getLong(0);
                getTitle = cursor.getString(1);
                if (getTitle.equals(getIntent().getStringExtra("judul"))){
                    btnfavorit.setImageDrawable(getResources().getDrawable(
                            R.drawable.ic_favorite_24dp));
                    favorite = true;
                }
            } while (cursor.moveToNext());
        }
        //Toast.makeText(getApplicationContext(), ""+id+" "+ada, Toast.LENGTH_LONG).show();
        return favorite;
    }
}
