package com.brendan.gamereview.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.brendan.gamereview.models.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long>{
	Optional<Genre> findGenreByName(String name);
}
