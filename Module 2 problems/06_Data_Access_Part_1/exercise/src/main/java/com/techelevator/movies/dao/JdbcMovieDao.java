package com.techelevator.movies.dao;

import com.techelevator.movies.model.Genre;
import com.techelevator.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovieDao implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movie";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            movies.add(mapRowToMovie(results));
        }
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = null;
        String sql = "SELECT * FROM movie WHERE movie_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            movie = mapRowToMovie(results);
        }
        return movie;
    }

    @Override
    public List<Movie> getMoviesByTitle(String title, boolean useWildCard) {
       List<Movie> movies = new ArrayList<>();

       if (useWildCard) {
           title = "%" + title + "%";
       }
       String sql =  "SELECT * FROM movie WHERE title ILIKE ?";
       SqlRowSet results = jdbcTemplate.queryForRowSet(sql, title);
       while (results.next()) {
           movies.add(mapRowToMovie(results));
       }
       return movies;
    }

    @Override
    public List<Movie> getMoviesByDirectorNameBetweenYears(String directorName, int startYear, int endYear, boolean useWildCard) {
        List<Movie> movies = new ArrayList<>();
        if(useWildCard) {
            directorName = "%" + directorName + "%";
        }
        Date startDate = Date.valueOf(startYear + "-01-01");
        Date endDate = Date.valueOf (endYear + "-12-31");
        String sql = "SELECT * FROM movie m JOIN person p ON director_id = person_id WHERE person_name ILIKE ? AND release_date BETWEEN ? AND ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,directorName,startDate,endDate);
        while (results.next()) {
            movies.add(mapRowToMovie(results));
        }
        return movies;
    }

    private Movie mapRowToMovie(SqlRowSet rowSet) {
        Movie movie = new Movie();
        movie.setId(rowSet.getInt("movie_id"));
        movie.setTitle(rowSet.getString("title"));
        movie.setOverview(rowSet.getString("overview"));
        movie.setTagline(rowSet.getString("tagline"));
        movie.setPosterPath(rowSet.getString("poster_path"));
        movie.setHomePage(rowSet.getString("home_page"));
        movie.setReleaseDate(rowSet.getDate("release_date").toLocalDate());
        movie.setLengthMinutes(rowSet.getInt("length_minutes"));
        movie.setDirectorId(rowSet.getInt("director_id"));
        movie.setCollectionId(rowSet.getInt("collection_id"));
        return movie;
    }
}
