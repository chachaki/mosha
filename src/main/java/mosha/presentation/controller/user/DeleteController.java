package mosha.presentation.controller.user;

import mosha.domain.model.利用者.利用者;
import mosha.domain.model.利用者.識別子;
import mosha.application.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Created by numamino on 2015/08/12.
 */
@Controller
@RequestMapping("/user/delete")
@SessionAttributes("user")
public class DeleteController {

    @Autowired
    UserService userService;

    //入り口 session attribute をクリアする
    @RequestMapping(method = RequestMethod.GET)
    String clearSessionAttribute(SessionStatus sessionStatus,@RequestParam(value="userId") String userId) {
        sessionStatus.setComplete();
        return "forward:/user/delete/" +userId + "/confirm";
    }

    @RequestMapping(value="/{userId}/confirm", method = RequestMethod.GET)
    String input(@PathVariable(value="userId") String userId,Model model) {
        利用者 user = userService.findById(new 識別子(userId));
        model.addAttribute("user", user); //sessionAttributeに格納
        return "user/delete/confirm";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    String execute(@ModelAttribute 利用者 user, SessionStatus status) {
        userService.delete(user);
        status.setComplete();
        return "user/delete/result";
    }

}
