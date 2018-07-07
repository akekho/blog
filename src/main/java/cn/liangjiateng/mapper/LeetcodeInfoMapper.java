package cn.liangjiateng.mapper;

import cn.liangjiateng.pojo.DO.LeetcodeInfo;
import org.apache.ibatis.annotations.*;

/**
 * Created by Jiateng on 7/7/18.
 */
@Mapper
public interface LeetcodeInfoMapper {

    @Select("select count(*) from leetcode_info where status = #{status}")
    long countLeetcodeInfoByStatus(int status);

    @Select("select count(*) from leetcode_info")
    long countLeetcodeInfo();

    @Select("select * from leetcode_info where username = #{username}")
    LeetcodeInfo getLeetcodeInfoByUsername(String username);

    long insertLeetcodeInfo(LeetcodeInfo leetcodeInfo);

    @Update("update leetcode_info set status = #{status}, update_time = now() where username = #{username}")
    void updateStatusByUsername(@Param("username") String username, @Param("status") int status);

    @Delete("delete from leetcode_info")
    void deleteAllLeetcodeInfo();
}
