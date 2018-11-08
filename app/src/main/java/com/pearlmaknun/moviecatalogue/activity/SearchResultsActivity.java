package com.pearlmaknun.moviecatalogue.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.pearlmaknun.moviecatalogue.BuildConfig;
import com.pearlmaknun.moviecatalogue.R;
import com.pearlmaknun.moviecatalogue.adapter.MovieAdapter;
import com.pearlmaknun.moviecatalogue.api.ApiClient;
import com.pearlmaknun.moviecatalogue.api.ApiService;
import com.pearlmaknun.moviecatalogue.model.Movie;
import com.pearlmaknun.moviecatalogue.model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultsActivity extends AppCompatActivity {

    private String key;
    private final static String API_KEY = BuildConfig.MY_MOVIE_DB_API_KEY;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        key = getIntent().getStringExtra("kunci_pencarian");

        getSupportActionBar().setTitle(key);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        showList(key);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void showList(String key) {
        progressDialog = ProgressDialog.show(this, null, "Mengambil....", true, false);

        final RecyclerView recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        Call<MovieResponse> call = apiService.searchMovie(API_KEY, key);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response) {
                progressDialog.dismiss();
                final List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MovieAdapter(movies, R.layout.adapter_movie, getApplicationContext()));

                recyclerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }

            @Override
            public void onFailure(Call<MovieResponse>call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Tidak ada koneksi.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
