package cn.liangjiateng.mapper;

import cn.liangjiateng.pojo.DO.Job;
import cn.liangjiateng.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 定时任务
 * Created by Jiateng on 6/30/18.
 */
@Mapper
public interface JobMapper {

    @Select("select count(*) from job where status = #{status}")
    long countJobsByStatus(int status);

    @Select("select count(*) from job where status != -1")
    long countJobs();

    @Select("select * from job where status = #{status} order by create_time desc limit #{page.limit}, #{page.pageSize}")
    List<Job> listJobsByStatus(@Param("status") int status, @Param("page") Page page);

    @Select("select * from job where status != -1 order by create_time desc limit #{page.limit}, #{page.pageSize}")
    List<Job> listJobs(@Param("page") Page page);

    @Select("select * from job where job_id = #{jobId}")
    Job getJobByJobId(String jobId);

    @Select("select * from job where id = #{id}")
    Job getJobById(int id);

}
