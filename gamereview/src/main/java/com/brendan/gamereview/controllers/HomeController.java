package com.brendan.gamereview.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.brendan.gamereview.models.Game;
import com.brendan.gamereview.models.Review;
import com.brendan.gamereview.models.User;
import com.brendan.gamereview.services.GameService;

@Controller
public class HomeController {
	
	private static GameService gameServ;
	
	public HomeController(GameService gameServ) {
		this.gameServ = gameServ;
	}
	
	@GetMapping("/home")
	public String newGamePlus(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if(loggedInUser == null) {
			return "redirect:/";
		}
		model.addAttribute("title", "All Games!");
		model.addAttribute("newGamePlus", new Game());
		model.addAttribute("allGames", gameServ.getGames());
		return "game.jsp";
	}
	
	@PostMapping("/games/new")
	public String createGameWithGenres(@Valid @ModelAttribute("newGamePlus") Game newGamePlus, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("allGames", gameServ.getGames());
			return "game.jsp";
		} else {
			User loggedInUser = (User) session.getAttribute("user");
			newGamePlus.setUser(loggedInUser);
			gameServ.createGameWithGenres(newGamePlus);
			return "redirect:/home";
		}
	}
	
	@GetMapping("/games/{id}")
	public String showGame(@PathVariable("id") Long id, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if(loggedInUser == null) {
			return "redirect:/";
		}
		model.addAttribute("someGame", gameServ.getGame(id));
		model.addAttribute("newReview", new Review());
		return "review.jsp";
	}
	
	@PostMapping("/games/{id}/review")
	public String reviewGame(@Valid @ModelAttribute("newReview") Review newReview, BindingResult result, @PathVariable("id") Long id, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if(result.hasErrors()) {
			model.addAttribute("someGame", gameServ.getGame(id));
			return "review.jsp";
		}
		newReview.setGame(gameServ.getGame(id));
		newReview.setUser(loggedInUser);
		Review r = gameServ.create(newReview);
		if(r == null) {
			result.rejectValue("content", "unique", "You have already reviewed this game!");
			model.addAttribute("someGame", gameServ.getGame(id));
			return "review.jsp";
		}
		return "redirect:/games/" + id;
	}
	
	@GetMapping("/genre/{genre}")
	public String gamesInGenre(@PathVariable("genre") String genre, Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("user");
		if(loggedInUser == null) {
			return "redirect:/";
		}
		model.addAttribute("title", "Genre: " + genre);
		model.addAttribute("newGamePlus", new Game());
		model.addAttribute("allGames", gameServ.gamesInGenre(genre));
		return "game.jsp";
	}
}