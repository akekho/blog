package cn.liangjiateng.mapper;

import cn.liangjiateng.pojo.DO.Log;
import cn.liangjiateng.util.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.List;

/**
 * Created by Jiateng on 7/21/18.
 */
@Mapper
public interface LogMapper {

    @Select("select * from log where id = #{id}")
    Log getLogById(long id);

    @Select("select distinct pid from log")
    List<String> listPids();

    @Select("select count(distinct datetime) from log where pid = #{pid}")
    long countDatetimes(String pid);

    @Select("select count(*) from log where pid = #{pid} and datetime = #{datetime}")
    long countLogsByPid(@Param("pid") String pid, @Param("datetime") Date datetime);

    @Select("select count(*) from log where pid = #{pid} and datetime = #{datetime} and level = #{level}")
    long countLogsByPidAndLevel(@Param("pid") String pid, @Param("level") int level, @Param("datetime") Date datetime);

    @Select("select distinct datetime from log where pid = #{pid} order by datetime desc limit #{page.limit}, #{page.pageSize}")
    List<Date> listDatetimesByPid(@Param("pid") String pid, @Param("page") Page page);

    @Select("select * from log where pid = #{pid} and datetime = #{datetime} order by datetime desc limit #{page.limit}, #{page.pageSize}")
    List<Log> listLogsByPid(@Param("pid") String pid, @Param("page") Page page, @Param("datetime") Date datetime);

    @Select("select * from log where pid = #{pid} and datetime = #{datetime} and level = #{level} order by datetime desc limit #{page.limit}, #{page.pageSize}")
    List<Log> listLogsByPidAndLevel(@Param("pid") String pid, @Param("level") int level, @Param("page") Page page, @Param("datetime") Date datetime);

    void insertLog(Log log);

    @Select("delete from log")
    void deleteAll();

}
