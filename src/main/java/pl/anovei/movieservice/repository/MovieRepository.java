package pl.anovei.movieservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.anovei.movieservice.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
