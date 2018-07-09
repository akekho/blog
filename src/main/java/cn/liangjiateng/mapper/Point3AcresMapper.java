package cn.liangjiateng.mapper;

import cn.liangjiateng.pojo.DO.AcreInfo;
import org.apache.ibatis.annotations.*;

/**
 * Created by Jiateng on 7/7/18.
 */
@Mapper
public interface Point3AcresMapper {

    @Select("select count(*) from 1point3acres_info where status = #{status}")
    long countAcreInfoByStatus(int status);

    @Select("select count(*) from 1point3acres_info")
    long countAcreInfo();

    @Select("select * from 1point3acres_info where username = #{username}")
    AcreInfo getAcreInfoByUsername(String username);

    long insertAcreInfo(AcreInfo acreInfo);

    @Update("update 1point3acres_info set status = #{status}, update_time = now() where username = #{username}")
    void updateStatusByUsername(@Param("username") String username, @Param("status") int status);

    @Delete("delete from 1point3acres_info")
    void deleteAllAcreInfo();
}
