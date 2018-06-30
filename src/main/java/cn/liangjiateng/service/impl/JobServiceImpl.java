package cn.liangjiateng.service.impl;

import cn.liangjiateng.service.JobService;
import cn.liangjiateng.thrift_client.JobClient;
import cn.liangjiateng.thrift_client.job.JobServiceException;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobClient jobClient;

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
}
