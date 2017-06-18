package mosha.presentation.controller.user;

import mosha.domain.model.利用者.性別;
import mosha.domain.model.利用者.利用者;
import mosha.domain.model.利用者.識別子;
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
@RequestMapping("user/update")
@SessionAttributes("user")
class UpdateController {

    private static final String[] allowFields ;
    static {
        allowFields = new String[] {
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

    @ModelAttribute("genderTypes")
    性別[] genderTypes() {
        return 性別.values();
    }

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    String start(SessionStatus sessionStatus,@RequestParam(value="userId") String userId) {
        sessionStatus.setComplete(); // session Attribute をクリアするためにマークする
        return "forward:/user/update/" +userId + "/input"; // クリアの実行
    }

    @RequestMapping(value="/{userId}/input", method = RequestMethod.GET)
    String formWithCurrentData(@PathVariable(value="userId") String userId,Model model) {
        利用者 user = userService.findById(new 識別子(userId));
        model.addAttribute("user", user); //session attribute("user")に格納する
        return "user/update/form";
    }

    @RequestMapping(value="/input/again",method= RequestMethod.GET)
    String formAgain() {
        return "user/update/form";
    }

    @RequestMapping(value = "/confirm", method = {RequestMethod.POST })
    String validate(@Valid @ModelAttribute 利用者 user,
                           BindingResult binding, RedirectAttributes attributes) {
        if (binding.hasErrors()) return "user/update/form";
        attributes.addFlashAttribute("user", user);
        return "redirect:confirm";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    String show() {
        return "user/update/confirm";
    }

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    String updateNow(@ModelAttribute 利用者 user, SessionStatus status) {
        userService.update(user);
        status.setComplete();
        return "user/update/result";
    }
}
