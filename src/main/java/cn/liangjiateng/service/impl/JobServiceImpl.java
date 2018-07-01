package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.JobMapper;
import cn.liangjiateng.pojo.DO.Job;
import cn.liangjiateng.service.JobService;
import cn.liangjiateng.thrift_client.JobClient;
import cn.liangjiateng.thrift_client.job.JobServiceException;
import cn.liangjiateng.util.Page;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobClient jobClient;
    @Autowired
    private JobMapper jobMapper;

    @Override
    public void start_scheduler() throws JobServiceException, TException {
        jobClient.start_scheduler();
    }

    @Override
    public void stop_scheduler() throws JobServiceException, TException {
        jobClient.stop_scheduler();
    }

    @Override
    public void pause_scheduler() throws JobServiceException, TException {
        jobClient.pause_scheduler();
    }

    @Override
    public void resume_scheduler() throws JobServiceException, TException {
        jobClient.resume_scheduler();
    }

    @Override
    public void start_job(String job_id) throws JobServiceException, TException {
        jobClient.start_job(job_id);
    }

    @Override
    public void stop_job(String job_id) throws JobServiceException, TException {
        jobClient.start_job(job_id);
    }

    @Override
    public void pause_job(String job_id) throws JobServiceException, TException {
        jobClient.pause_job(job_id);
    }

    @Override
    public void modify_job(String job_id, String config) throws JobServiceException, TException {
        jobClient.modify_job(job_id, config);
    }

    @Override
    public String submit_job(String file_bytes, String config) throws JobServiceException, TException {
        return jobClient.submit_job(file_bytes, config);
    }

    @Override
    public int status() throws JobServiceException, TException {
        return jobClient.status();
    }

    @Override
    public long countJobsByStatus(Job.Status status) {
        return jobMapper.countJobsByStatus(status.getVal());
    }

    @Override
    public long countJobs() {
        return jobMapper.countJobs();
    }

    @Override
    public Page<Job> listJobsByStatus(Job.Status status, int page, int pageSize) throws ServiceException {
        if (page <= 0 || pageSize <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long count = countJobsByStatus(status);
        Page<Job> pageHolder = new Page<>(pageSize);
        List<Job> list = jobMapper.listJobsByStatus(status.getVal(), pageHolder);
        pageHolder.setData(list);
        pageHolder.setPage(page);
        pageHolder.setMaxCount(count);
        return pageHolder;
    }

    @Override
    public Page<Job> listJobs(int page, int pageSize) throws ServiceException {
        if (page <= 0 || pageSize <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long count = countJobs();
        Page<Job> pageHolder = new Page<>(pageSize);
        List<Job> list = jobMapper.listJobs(pageHolder);
        pageHolder.setData(list);
        pageHolder.setPage(page);
        pageHolder.setMaxCount(count);
        return pageHolder;
    }


    @Override
    public Job getJobByJobId(int jobId) throws ServiceException {
        Job job = jobMapper.getJobByJobId(jobId);
        if (job == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "定时任务" + jobId + "不存在");
        return job;
    }
}
