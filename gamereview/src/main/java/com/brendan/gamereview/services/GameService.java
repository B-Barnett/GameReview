package com.brendan.gamereview.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.brendan.gamereview.models.Game;
import com.brendan.gamereview.models.Genre;
import com.brendan.gamereview.models.Review;
import com.brendan.gamereview.repositories.GameRepository;
import com.brendan.gamereview.repositories.GenreRepository;
import com.brendan.gamereview.repositories.ReviewRepository;

@Service
public class GameService {
	
	private static GameRepository gameRepo;
	private static GenreRepository genreRepo;
	private static ReviewRepository revRepo;
	
	public GameService(GameRepository gameRepo, GenreRepository genreRepo, ReviewRepository revRepo) {
		this.gameRepo = gameRepo;
		this.genreRepo = genreRepo;
		this.revRepo = revRepo;
	}
	
	public Game create(Game newGame) {
		return gameRepo.save(newGame);
	}
	
	public Genre create(Genre newGenre) {
		return genreRepo.save(newGenre);
	}
	
	public Review create(Review newReview) {
		List<Review> matchingReviews = revRepo.matchingReviews(
				newReview.getUser().getId(),
				newReview.getGame().getId());
		if(matchingReviews.size() > 0) {
			return null;
		}
		newReview.setId(null);
		return revRepo.save(newReview);
	}
	
	public List<Genre> getGenres() {
		return (List<Genre>) genreRepo.findAll();
	}
	
	public List<Game> getGames() {
		return (List<Game>) gameRepo.findAll();
	}
	
	public Genre getGenre(Long id) {
		Optional<Genre> genre = genreRepo.findById(id);
		return genre.isPresent() ? genre.get() : null;
	}

	public Game getGame(Long id) {
		Optional<Game> game = gameRepo.findById(id);
		return game.isPresent() ? game.get() : null;
	}
	
	public Game saveGame(Game game) {
		return gameRepo.save(game);
	}
	
	public Genre createOrRetrieve(String genreName) {
		Optional<Genre> mightExist = genreRepo.findGenreByName(genreName);
		if(mightExist.isPresent()) {
			return mightExist.get();
		} else {
			return genreRepo.save( new Genre(genreName) );
		}
	}
	
	public Game createGameWithGenres(Game newGamePlus) {
		List<Genre> genres = new ArrayList<Genre>();
		for(String genreName: newGamePlus.getGenresInput().split(",")) {
			genres.add(createOrRetrieve(genreName));
		}
		newGamePlus.setGenres(genres);
		return gameRepo.save(newGamePlus);
	}
	
	public List<Game> gamesInGenre(String genre) {
		Optional<Genre> g = genreRepo.findGenreByName(genre);
		return g.isPresent() ? g.get().getGames() : new ArrayList<Game>();
	}
}