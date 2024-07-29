package code.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import code.dto.SignUpDto;
import code.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller @RequiredArgsConstructor
@RequestMapping("/user")
public class UserController 
{
    private final UserService userService;

    @GetMapping("/signup")
    public String signupGet()
    {
        return "/user/signup";
    }

    // Create
    @PostMapping("/signupprocess")
    @ResponseBody
    public String create(@Validated SignUpDto newUser, BindingResult error)
    {
        log.info("SignUpDto : " + newUser.toString());

        if(error.hasErrors()) return error.getAllErrors().get(0).getDefaultMessage();

        if(!newUser.mailCheck()) return "잘못된 이메일입니다. 다시 입력해주시겠어요?";
        if(!newUser.rePwCheck()) return "비밀번호 재입력이 잘못되었습니다.";

        return userService.userCreate(newUser);
    }

}
