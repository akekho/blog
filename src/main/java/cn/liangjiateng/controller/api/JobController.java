package cn.liangjiateng.controller.api;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.JsonResponse;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.DO.Job;
import cn.liangjiateng.service.JobService;
import cn.liangjiateng.util.JsonUtil;
import cn.liangjiateng.util.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 定时任务控制器
 * Created by Jiateng on 6/30/18.
 */
@RestController
@RequestMapping(value = "/api/jobs", produces = "application/json")
public class JobController {

    @Autowired
    private JobService jobService;
    @Autowired
    private Config config;

    /**
     * 操作调度器
     *
     * @param operation start stop pause resume
     * @return
     */
    @PutMapping("/scheduler/{operation}")
    public JsonResponse operateScheduler(@PathVariable String operation) throws TException, ServiceException {
        switch (operation) {
            case "start":
                jobService.start_scheduler();
                break;
            case "stop":
                jobService.stop_scheduler();
                break;
            case "pause":
                jobService.pause_scheduler();
                break;
            case "resume":
                jobService.resume_scheduler();
                break;
            default:
                throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        }
        return new JsonResponse(null);
    }

    /**
     * 操作定时任务
     *
     * @param operation start stop pause
     * @return
     */
    @PutMapping("/{jobId}/{operation}")
    public JsonResponse operateJob(@PathVariable String operation, @PathVariable String jobId) throws ServiceException, TException {
        switch (operation) {
            case "start":
                jobService.start_job(jobId);
                break;
            case "stop":
                jobService.stop_job(jobId);
                break;
            case "pause":
                jobService.pause_job(jobId);
                break;
            default:
                throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        }
        return new JsonResponse(null);
    }

    @PostMapping
    public JsonResponse submitJob(@RequestBody Job job) throws JsonProcessingException, TException {
        String jsonStr = JsonUtil.bean2String(job);
        jobService.submit_job(job.getContent(), jsonStr);
        return new JsonResponse(null);
    }

    @PutMapping("/{jobId}")
    public JsonResponse modifyJob(@RequestBody Job job, @PathVariable String jobId) throws JsonProcessingException, TException {
        String jsonStr = JsonUtil.bean2String(job);
        jobService.modify_job(jobId, jsonStr);
        return new JsonResponse(null);
    }

    @GetMapping
    public JsonResponse listJobs(@RequestParam int page, @RequestParam(defaultValue = "3") int status) throws ServiceException {
        Page<Job> pageHolder;
        if (status != 3)
            pageHolder = jobService.listJobsByStatus(Job.getStatusType(status), page, config.getLargePage());
        else
            pageHolder = jobService.listJobs(page, config.getLargePage());
        return new JsonResponse(pageHolder);
    }

    /**
     * 获取调度器状态
     *
     * @return 0 停止 1 运行 2暂停
     * @throws TException
     */
    @GetMapping("/scheduler/status")
    public JsonResponse getStatus() throws TException {
        Map<String, Integer> map = new HashMap<>();
        int status = jobService.status();
        map.put("status", status);
        return new JsonResponse(map);
    }
}
