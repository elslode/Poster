package slode.elsloude.mymoview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import slode.elsloude.mymoview.data.Movie;
import slode.elsloude.mymoview.utils.JSONUtils;
import slode.elsloude.mymoview.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private Switch switchSort;
    private RecyclerView recyclerViewPostrers;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchSort = findViewById(R.id.switchSort);
        recyclerViewPostrers = findViewById(R.id.recyclerViewPosters);
        recyclerViewPostrers.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter();
        switchSort.setChecked(true);
        recyclerViewPostrers.setAdapter(movieAdapter);
        switchSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setMethedOfSort(isChecked);
            }
        });
        switchSort.setChecked(false);
    }

    public void onClickSetPopularity(View view) {
        setMethedOfSort(false);
    }

    public void onClickSetTopRated(View view) {
        setMethedOfSort(true);
    }

    private void setMethedOfSort(boolean isTopRated) {
        int methodOfSort;
        if (isTopRated) {
            methodOfSort = NetworkUtils.TOP_RATED;
        } else {
            methodOfSort = NetworkUtils.POPULARITY;
        }
        JSONObject jsonObject = NetworkUtils.getJSONFromNetwork(methodOfSort, 1);
        ArrayList<Movie> movies = JSONUtils.getMovieFromJSON(jsonObject);
        movieAdapter.setMovies(movies);
    }
}