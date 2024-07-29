package code.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;




@Controller
@RequiredArgsConstructor
public class HomeController 
{
    @RequestMapping(path="/", method=RequestMethod.GET)
    public String home() {
        return "/flee/fleeList";
    }
    
    @RequestMapping(path="/flee", method=RequestMethod.GET)
    public String flee() {
        return "/flee/fleeList";
    }
    
    @RequestMapping(path="/neighborhood", method=RequestMethod.GET)
    public String neighborhood() {
        return "/neighborhood/neighborhoodList";
    }

    @RequestMapping(path="/chat", method=RequestMethod.GET)
    public String chat() {
        return "/chat/chatList";
    }

    @RequestMapping(path="/myPage", method=RequestMethod.GET)
    public String myPage() {
        return "/user/mypage/mypage";
    }
    
    @RequestMapping(path="/manager", method=RequestMethod.GET)
    public String manager() {
        return "/manager/managerHome";
    }
    
    @RequestMapping(path="/login", method=RequestMethod.GET)
    public String login() {
        return "/user/login";
    }

}
