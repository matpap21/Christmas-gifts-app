package pl.sda.christmasgifts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.christmasgifts.entity.Person;
import pl.sda.christmasgifts.entity.Wish;
import pl.sda.christmasgifts.service.ChristmasGiftsService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.UUID;

// https://github.com/siwoncezary/xmas-gifts-app/blob/master/src/main/java/pl/sda/xmasgifts/service/JpaXmasGiftsService.java
@Controller
public class ChristmasGiftsController {

    public static final String XMAS_USER_ID = "xmas-user-id";
    // wstrzykiwanie interfejsu
    public final ChristmasGiftsService christmasGiftsService;

    public ChristmasGiftsController(ChristmasGiftsService christmasGiftsService) {
        this.christmasGiftsService = christmasGiftsService;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/user/add")
    public String addUserForm(){
        return "add-user-form";
    }
    @PostMapping("/user/add")
    public ModelAndView  addUser(@ModelAttribute Person person, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-user-confirm");
        final Person user = christmasGiftsService.addPerson(person);
        modelAndView.getModelMap().addAttribute("person", user);
        Cookie cookie = new Cookie(XMAS_USER_ID, user.getId().toString());
        cookie.setMaxAge(100_000_000);
        cookie.setPath("/");
        response.addCookie(cookie);
        return modelAndView;
    }
    @GetMapping("/user/list")
    public String listUsers(Model model){
        model.addAttribute("people",christmasGiftsService.findAllPersons());
        return "users-list";
    }
    @GetMapping("/wish/add")
    public String addWish(){
        return "add-wish-form";
    }
    @PostMapping("/wish/add")
    public String addWish(@ModelAttribute Wish wish, HttpServletRequest request) {
        String uuidString;
        final Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(XMAS_USER_ID)) {
                uuidString = cookie.getValue();
                final Optional<Wish> optionalWish = christmasGiftsService.addPersonWish(wish, UUID.fromString(uuidString));
                if (optionalWish.isPresent()) {
                    return "user/list";
                } else {
                    return "add-wish-error";

                }
            }
        }
        return "add-wish-error";
    }
}
