package cn.liangjiateng.controller.views;

import cn.liangjiateng.service.LeetcodeInfoService;
import cn.liangjiateng.service.Point3AcreService;
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
    @Autowired
    private Point3AcreService point3AcreService;

    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/blog/home");
    }

    @GetMapping("/blog/services")
    public String services() {
        return "service";
    }

    @GetMapping("/blog/services/leetcode")
    public String leetcode(ModelMap modelMap) {
        modelMap.addAttribute("count", leetcodeInfoService.countLeetcodeInfo());
        return "leetcode";
    }

    @GetMapping("/blog/services/1point3acres")
    public String point3acres(ModelMap modelMap) {
        modelMap.addAttribute("count", point3AcreService.countAcreInfo());
        return "1point3acres";
    }
}
