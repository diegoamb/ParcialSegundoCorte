package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.parcial2.io.TracksApiAdapter;
import com.example.parcial2.io.TracksApiService;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchTrack extends AppCompatActivity {
    TextView nombre,artista;
    EditText txtBuscar;
    Button btnBuscar,btnAgregar;
    Searching search;
    List<TrackSearch> lista;

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE = "extra.MESSAGE";
    public static final String EXTRA_MESSAGE1 = "extra.MESSAGE";
    private TextView nameT, artistT;
    String durationT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_track);
        nombre = (TextView) findViewById(R.id.cancion);
        artista = (TextView) findViewById(R.id.autor);
        txtBuscar = (EditText) findViewById(R.id.nameBuscador);

        nameT = findViewById(R.id.cancion);
        artistT = findViewById(R.id.autor);

        btnBuscar = (Button) findViewById(R.id.buscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPost();
            }
        });

        btnAgregar = (Button) findViewById(R.id.addButtonObject);
        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addNewTrack(v);
                //launchMainActivity2(v);
            }
        });
    }


    private void getPost(){
        String aux = txtBuscar.getText().toString();
        Call<Searching> call = TracksApiAdapter.getApiService().getSearching("track.search",aux,"b284db959637031077380e7e2c6f2775","json");
        try{
            call.enqueue(new Callback<Searching>() {
                @Override
                public void onResponse(Call<Searching> call, Response<Searching> response) {
                    if (response.isSuccessful()){
                        search = response.body();
                        Results results = search.getResults();
                        TrackMatches trackmatches = results.getTrackmatches();
                        lista = trackmatches.getTrackSearch();
                        nombre.setText(lista.get(0).getName());
                        artista.setText(lista.get(0).getArtist());
                    }
                    Log.d("onResponse search","Size of Track => ");
                }

                @Override
                public void onFailure(Call<Searching> call, Throwable t) {
                    Log.d("onFailure search","Size of Track => ");
                }
            });
        }catch (IllegalStateException | JsonSyntaxException exception){

        }
    }

    public void addNewTrack(View view) {
        Bundle extras = new Bundle();
        String nombreT = nombre.getText().toString();
        String artistaT = artista.getText().toString();
        extras.putString("name",nombreT);
        extras.putString("nameA",artistaT);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

}
