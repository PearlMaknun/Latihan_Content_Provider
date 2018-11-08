package com.pearlmaknun.moviecatalogue.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class UpcomingFragment extends Fragment {

    private final static String API_KEY = BuildConfig.MY_MOVIE_DB_API_KEY;
    ProgressDialog progressDialog;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        showList(view);
        return view;
    }

    private void showList(View view) {
        progressDialog = ProgressDialog.show(getContext(), null, "Mengambil....", true, false);
        final RecyclerView recyclerView = view.findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        Call<MovieResponse> call = apiService.getUpcoming(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response) {
                progressDialog.dismiss();
                final List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MovieAdapter(movies, R.layout.adapter_movie, getContext()));

                recyclerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });


            }

            @Override
            public void onFailure(Call<MovieResponse>call, Throwable t) {
                //progressDialog.dismiss();
                Toast.makeText(getContext(), "Tidak ada koneksi.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
