package cn.liangjiateng.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/blog")
public class BlogFrontController {

    @GetMapping("/home")
    public String main() {
        return "main";
    }

    @GetMapping("/posts/{id}")
    public String posts(ModelMap modelMap, @PathVariable int id) {
        modelMap.addAttribute("article_id", id);
        return "post";
    }
}
