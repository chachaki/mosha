package mosha.presentation.controller;

import mosha.domain.model.利用者.利用者概要一覧;
import mosha.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by haljik on 15/06/02.
 */
@Controller
@RequestMapping("/")
class TopController {

    @Autowired
    UserService userService;


    @ModelAttribute("users")
    利用者概要一覧 users() {
        return userService.list();
    }

    @RequestMapping
    String show() {
        return "top";
    }

}
