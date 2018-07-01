package cn.liangjiateng.service;


import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.pojo.DO.Job;
import cn.liangjiateng.thrift_client.job.JobRPCService;
import cn.liangjiateng.util.Page;

/**
 * 定时任务服务
 */
public interface JobService extends JobRPCService.Iface {

    long countJobsByStatus(Job.Status status);

    long countJobs();

    Page<Job> listJobsByStatus(Job.Status status, int page, int pageSize) throws ServiceException;

    Page<Job> listJobs(int page, int pageSize) throws ServiceException;

    Job getJobByJobId(int jobId) throws ServiceException;
}
