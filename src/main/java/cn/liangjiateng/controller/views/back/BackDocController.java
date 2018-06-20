package cn.liangjiateng.controller.views.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/back/docs")
public class BackDocController {

    @GetMapping("/new_doc")
    public String createDoc(){
        return "back_new_doc";
    }
}
