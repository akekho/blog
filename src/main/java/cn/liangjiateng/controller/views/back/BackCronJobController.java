package cn.liangjiateng.controller.views.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jiateng on 7/1/18.
 */
@Controller
@RequestMapping(value = "/back/cronjobs")
public class BackCronJobController {
    @GetMapping
    public String cronJobs() {
        return "back_cronjob";
    }

    @GetMapping("/config")
    public String newJob() {
        return "back_edit_cronjob";
    }

    @GetMapping("/config/{jobId}")
    public String editJob(@PathVariable String jobId) {
        return "back_edit_cronjob";
    }

}
