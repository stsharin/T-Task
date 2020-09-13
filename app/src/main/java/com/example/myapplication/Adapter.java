package com.example.myapplication;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Album> albums;

    public Adapter(Context ctx, List<Album> albums){
        this.inflater = LayoutInflater.from(ctx);
        this.albums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.albumTitle.setText(albums.get(position).getTitle());
        Picasso.get().load(albums.get(position).getCoverImage()).into(holder.albumCoverImage);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView albumTitle;
        ImageView albumCoverImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            albumTitle = itemView.findViewById(R.id.album_title);
            albumCoverImage = itemView.findViewById(R.id.cover_img);
        }
    }
}
