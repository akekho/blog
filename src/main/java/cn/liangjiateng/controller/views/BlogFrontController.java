package cn.liangjiateng.controller.views;

import cn.liangjiateng.config.Config;
import cn.liangjiateng.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping(value = "/blog")
public class BlogFrontController {

    @Autowired
    private Config config;

    @GetMapping("/home")
    public String main(ModelMap modelMap, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "0") int sortType) throws IOException {
        String json = HttpUtil.get(config.getUrl("/api/articles?page=" + page + "&sortType=" + sortType));

        return "main";
    }

    @GetMapping("/posts/{id}")
    public String posts(ModelMap modelMap, @PathVariable int id) {
        modelMap.addAttribute("article_id", id);
        return "post";
    }
}
