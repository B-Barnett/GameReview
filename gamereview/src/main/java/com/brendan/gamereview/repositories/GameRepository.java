package com.brendan.gamereview.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.brendan.gamereview.models.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {}
