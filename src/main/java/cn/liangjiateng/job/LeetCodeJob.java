package cn.liangjiateng.job;

import cn.liangjiateng.util.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode定时签到任务
 */
@Component
public class LeetCodeJob {

    private Logger logger = Logger.getLogger(getClass());

    /**
     * 凌晨1点执行
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void job() {
        System.out.println("--------------->定时任务");
    }


    private void doJob(String username, String password) {
        Map<String, String> params = new HashMap<>();
        try {
            String res = HttpUtil.postForm("https://leetcode.com/accounts/login/", params);
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
