package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by MacLap on 3/8/16.
 */

@Controller
public class GameTrackerController {
    @Autowired
    GameRepository games;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String genre, Integer releaseYear, String platform) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByName(username);
        if (user !=null) {
            model.addAttribute("user", users.findFirstByName(username));
        }

        if (platform !=null){
            model.addAttribute("games", games.findByPlatformStartsWith(platform));
        }
        else if (genre != null && releaseYear != null) {
            model.addAttribute("games", games.findByUserAndGenreAndReleaseYear(user, genre, releaseYear));
        }
        else if (genre != null){
            model.addAttribute("games", games.findByUserAndGenre(user, genre));
        }
        else{
            model.addAttribute("games", games.findByUser(user));
        }
        return "home";
    }

    @RequestMapping(path = "/add-game", method = RequestMethod.POST)
    public String addGame(HttpSession session, String gameName, String gamePlatform, String gameGenre, int gameYear) {
        String username = (String) session.getAttribute("username");
        User user = users.findFirstByName(username);
        Game game = new Game(gameName, gamePlatform, gameGenre, gameYear);
        game.user = user;
        games.save(game);
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username) {
        User user = users.findFirstByName(username);
        if (user == null) {
            user = new User(username);
            users.save(user);
        }
        session.setAttribute("username", username);
    return "redirect:/";
    }

    @RequestMapping(path="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
