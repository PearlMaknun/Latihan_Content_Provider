package com.pearlmaknun.moviecatalogue.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.pearlmaknun.moviecatalogue.R;
import com.pearlmaknun.moviecatalogue.activity.DetailActivity;
import com.pearlmaknun.moviecatalogue.model.Favorite;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewholder> {
    private Cursor listFavorite;
    private Activity activity;
    private Context context;

    public FavoriteAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setListFavorite(Cursor listFavorite) {
        this.listFavorite = listFavorite;
    }

    @Override
    public FavoriteViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie, parent, false);
        return new FavoriteViewholder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewholder holder, int position) {
        final Favorite favorite = getItem(position);
        holder.tvTitle.setText(favorite.getJudul());
        holder.tvDate.setText(favorite.getTanggalrilis());
        holder.tvDescription.setText(favorite.getSinopsis());
        Glide.with(activity)
                .load("http://image.tmdb.org/t/p/w185"+favorite.getPosterpath())
                .into(holder.Poster);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDetail(favorite.getJudul(),
                        favorite.getSinopsis(),
                        favorite.getTanggalrilis(),
                        favorite.getBackdroppath(),
                        favorite.getPosterpath(),
                        favorite.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listFavorite == null) return 0;
        return listFavorite.getCount();
    }

    private Favorite getItem(int position) {
        if (!listFavorite.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }
        return new Favorite(listFavorite);
    }

    class FavoriteViewholder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvDate;
        ImageView Poster;
        LinearLayout layout;

        FavoriteViewholder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.main_adapter);
            tvTitle = itemView.findViewById(R.id.judul);
            tvDescription = itemView.findViewById(R.id.sinopsis);
            tvDate = itemView.findViewById(R.id.tanggalrilis);
            Poster = itemView.findViewById(R.id.poster);

        }
    }

    public void getDetail(String judul, String tanggalrilis, String sinopsis, String backdrop, String poster, long id){
        Intent goToDetail = new Intent(activity, DetailActivity.class);
        goToDetail.putExtra("id", id);
        goToDetail.putExtra("judul", judul);
        goToDetail.putExtra("tanggalrilis", tanggalrilis);
        goToDetail.putExtra("sinopsis", sinopsis);
        goToDetail.putExtra("backdrop", backdrop);
        goToDetail.putExtra("poster", poster);
        activity.startActivity(goToDetail);
    }
}
