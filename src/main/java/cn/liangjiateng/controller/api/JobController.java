package cn.liangjiateng.controller.api;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.JsonResponse;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.DO.Job;
import cn.liangjiateng.pojo.VO.JobVO;
import cn.liangjiateng.service.JobService;
import cn.liangjiateng.util.JsonUtil;
import cn.liangjiateng.util.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public JsonResponse submitJob(@RequestBody JobVO job) throws JsonProcessingException, TException {
        String config = packConfig(job);
        jobService.submit_job(job.getContent(), config);
        return new JsonResponse(null);
    }

    @PutMapping("/{jobId}")
    public JsonResponse modifyJob(@RequestBody JobVO job, @PathVariable String jobId) throws JsonProcessingException, TException {
        String config = packConfig(job);
        jobService.modify_job(jobId, config);
        return new JsonResponse(null);
    }

    @GetMapping
    public JsonResponse listJobs(@RequestParam int page, @RequestParam(defaultValue = "3") int status) throws ServiceException {
        Page<Job> pageHolder;
        if (status != 3)
            pageHolder = jobService.listJobsByStatus(Job.getStatusType(status), page, config.getLargePage());
        else
            pageHolder = jobService.listJobs(page, config.getLargePage());
        List<JobVO> jobVOS = batchTransfer2VO(pageHolder.getData());
        Page<JobVO> res = new Page<>(page, pageHolder.getPageSize(), pageHolder.getMaxCount(), jobVOS);
        return new JsonResponse(res);
    }

    private List<JobVO> batchTransfer2VO(List<Job> list) {
        List<JobVO> res = new ArrayList<>();
        for (Job job : list) {
            JobVO jobVO = new JobVO(job);
            res.add(jobVO);
        }
        return res;
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

    private String packConfig(JobVO job) throws JsonProcessingException {
        Map<String, Object> configMap = new HashMap<>();
        Map<String, Object> cronMap = new HashMap<>();
        configMap.put("name", job.getName());
        configMap.put("job_id", job.getJobId());
        configMap.put("type", job.getType());
        configMap.put("instance_cnt", job.getInstanceCount());

        if (job.getType() == Job.Type.INTERVAL.getVal()) {
            if (job.getYear() != null)
                cronMap.put("years", job.getYear());
            if (job.getMonth() != null)
                cronMap.put("months", job.getMonth());
            if (job.getDay() != null)
                cronMap.put("days", job.getDay());
            if (job.getHour() != null)
                cronMap.put("hours", job.getHour());
            if (job.getMinute() != null)
                cronMap.put("minutes", job.getMinute());
            if (job.getSecond() != null)
                cronMap.put("seconds", job.getSecond());
        } else if (job.getType() == Job.Type.CRON.getVal()) {
            if (job.getYear() != null)
                cronMap.put("year", job.getYear());
            if (job.getMonth() != null)
                cronMap.put("month", job.getMonth());
            if (job.getDay() != null)
                cronMap.put("day", job.getDay());
            if (job.getHour() != null)
                cronMap.put("hour", job.getHour());
            if (job.getMinute() != null)
                cronMap.put("minute", job.getMinute());
            if (job.getSecond() != null)
                cronMap.put("second", job.getSecond());
        }
        if (job.getStartDate() != null && !"".equals(job.getStartDate()))
            cronMap.put("start_date", job.getStartDate());
        if (job.getEndDate() != null && !"".equals(job.getEndDate()))
            cronMap.put("end_date", job.getEndDate());
        configMap.put("cron", JsonUtil.bean2String(cronMap));
        return JsonUtil.bean2String(configMap);
    }
}
