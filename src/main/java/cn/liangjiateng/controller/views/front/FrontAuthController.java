package cn.liangjiateng.controller.views.front;

import cn.liangjiateng.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jiateng on 7/15/18.
 */
@Controller
@RequestMapping(value = "/auth")
public class FrontAuthController {

    @Autowired
    private Config config;

    @GetMapping("/login")
    public String login() throws IOException {
        return "login";
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("token", null);
        cookie.setDomain(config.getDomain());
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/auth/login");
    }

}
