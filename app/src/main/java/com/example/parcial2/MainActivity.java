package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.parcial2.io.TracksApiAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TrackAdapter adapter;
    List<Artist> artistList = new ArrayList<>();
    List<Track> trackList1;
    FloatingActionButton floatingActionButton;
    String trackAux;
    String artistAux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPosts();
        Intent intent = this.getIntent();
        trackAux = intent.getStringExtra("name");
        artistAux = intent.getStringExtra("nameA");

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchTrack.class);
                startActivity(intent);
            }
        });
        floatingActionButton = findViewById(R.id.fab1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTrack();
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        insertTrack();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void insertTrack() {
        trackList1.add(new Track(trackAux,"Not Found"));
        artistList.add(new Artist(artistAux));
        adapter = new TrackAdapter(trackList1, artistList);
        recyclerView.setAdapter(adapter);
    }

    private void getPosts(){
        Call<TracksList> call = TracksApiAdapter.getApiService().getTracks();
        call.enqueue(new Callback<TracksList>() {
            @Override
            public void onResponse(Call<TracksList> call, Response<TracksList> response) {
                if (response.isSuccessful()){
                    TracksList tracksList = response.body();
                    Tracks tracks = tracksList.getTracks();
                    trackList1 = tracks.getTrack();
                    for(Track track: trackList1){
                        Artist artist = track.getArtist();
                        artistList.add(artist);
                    }
                    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);

                    adapter = new TrackAdapter(trackList1, artistList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<TracksList> call, Throwable t) {
                Log.d("onFailure Track","Error of Track => " + t );
            }
        });
        //insertTrack(0);
    }

}
