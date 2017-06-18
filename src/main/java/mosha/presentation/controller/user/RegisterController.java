package mosha.presentation.controller.user;

import mosha.domain.model.利用者.性別;
import mosha.domain.model.利用者.利用者;
import mosha.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("user/register")
@SessionAttributes("user")
class RegisterController {

    private static final String[] allowFields ;
    static {
        allowFields = new String[] {
                "identifier",
                "name",
                "dateOfBirth",
                "gender",
                "phoneNumber",
        };
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(allowFields);
    }

    @Autowired
    UserService userService;

    @ModelAttribute("genderTypes")
    性別[] genderTypes() {
        return 性別.values();
    }

    @RequestMapping(method = RequestMethod.GET)
    String start(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // session attribute をクリアするためにマーク
        return "forward:/user/register/input"; // session attribute のクリア実行
    }

    @RequestMapping(value="/input", method = RequestMethod.GET)
    String form(Model model) {
        利用者 user = userService.prototype();
        model.addAttribute("user", user); //sessionAttributeに格納
        return "user/register/form";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    String confirm(@Valid @ModelAttribute 利用者 user, BindingResult result) {
        if (result.hasErrors()) return "user/register/form";
        if (userService.isExist(user.identifier())) {
            result.rejectValue("identifier","", "ユーザー{0}は登録済みです");
            return "user/register/form";
        }
        return "user/register/confirm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    String register(@ModelAttribute 利用者 user, RedirectAttributes attributes) {
        userService.register(user);
        attributes.addFlashAttribute("user", user);
        return "redirect:/user/register/complete";
    }

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    String complete(SessionStatus status) {
        status.setComplete();
        return "user/register/result";
    }
}
