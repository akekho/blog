package cn.liangjiateng.controller.views.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * Created by Jiateng on 7/15/18.
 */
@Controller
@RequestMapping(value = "/auth")
public class FrontAuthController {

    @GetMapping("/login")
    public String login() throws IOException {
        return "login";
    }

}
