package slode.elsloude.mymoview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import slode.elsloude.mymoview.data.Movie;
import slode.elsloude.mymoview.utils.JSONUtils;
import slode.elsloude.mymoview.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewPostrers;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewPostrers = findViewById(R.id.recyclerViewPosters);
        recyclerViewPostrers.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter();
        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(NetworkUtils.POPULARITY, 1);
        ArrayList<Movie> movies = JSONUtils.getMovieFromJSON(jsonObject);
        movieAdapter.setMovies(movies);
        recyclerViewPostrers.setAdapter(movieAdapter);
    }
}