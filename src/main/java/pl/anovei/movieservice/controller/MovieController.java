package pl.anovei.movieservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.anovei.movieservice.model.Movie;
import pl.anovei.movieservice.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies()
    {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);

        if (movie != null) {
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {

        if (movie.getName() == null || movie.getCategory() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        movieService.saveMovie(movie);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {

        if (movieService.getMovieById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        movie.setId(id);

        movieService.saveMovie(movie);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {

        if (movieService.getMovieById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        movieService.deleteMovie(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
