package com.example.parcial2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {

    private List<Track> tracks;
    private List<Artist> artist;

    public TrackAdapter(List<Track> tracks, List<Artist> artist) {
        this.tracks = tracks;
        this.artist = artist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_track,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTrack(tracks.get(position));
        holder.bindArtist(artist.get(position));

    }

    @Override
    public int getItemCount() {
        int item = 1;
        if (item==1){
            return tracks.size();
        }
        return artist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tnombre;
        public TextView tduracion;
        public TextView tartista;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tnombre = (TextView) itemView.findViewById(R.id.name);
            tduracion = (TextView) itemView.findViewById(R.id.duration);
            tartista = (TextView) itemView.findViewById(R.id.artist);
        }

        public void bindTrack(Track track) {
            String nombre = track.getName();
            String duracion = track.getDuration();

            tnombre.setText(nombre);
            tduracion.setText(duracion);
        }

        public void bindArtist(Artist artist) {
            String artista = artist.getName();

            tartista.setText(artista);
        }
    }
}
