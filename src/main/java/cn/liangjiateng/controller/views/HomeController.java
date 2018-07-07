package cn.liangjiateng.controller.views;

import cn.liangjiateng.service.LeetcodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    private LeetcodeInfoService leetcodeInfoService;

    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/blog/home");
    }

    @GetMapping("/blog/services/leetcode")
    public String leetcode(ModelMap modelMap) throws IOException {
        modelMap.addAttribute("count", leetcodeInfoService.countLeetcodeInfo());
        return "leetcode";
    }
}
