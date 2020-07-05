package slode.elsloude.mymoview.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAllMovies(); //Возвращает LiveData

    @Query("SELECT * FROM movies WHERE id == :movieId")
    Movie getMovieById(int movieId); //для получения фильма по его ID

    @Query("SELECT * FROM favourite_movies WHERE id == :movieId")
    FavouriteMovie getFavouriteMovieById(int movieId); //для получения фильма по его ID

    @Query("DELETE FROM movies")
    void deleteAllMovies(); //для удаления всех фильмов из базы данных

    @Query("SELECT * FROM favourite_movies")
    LiveData<List<FavouriteMovie>> getAllFavouriteMovies(); //Возвращает LiveData

    @Insert
    void insertMovie(Movie movie);//Добавление одного элемента

    @Delete
    void deleteMovie(Movie movie); //удаление одного элемента(фильма)

    @Insert
    void insertFavouriteMovie(FavouriteMovie movie);//Добавление одного лучшего элемента

    @Delete
    void deleteFavouriteMovie(FavouriteMovie movie); //удаление одного лучшего элемента(фильма)


}
