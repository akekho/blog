package cn.liangjiateng.mapper;

import cn.liangjiateng.pojo.DO.Account;
import org.apache.ibatis.annotations.*;


@Mapper
public interface AccountMapper {

    @Select("select * from account where username = #{username}")
    Account getAccountByUsername(String username);

    @Select("select * from account where username = #{username} and password = #{password}")
    Account getAccountByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    long insertAccount(Account account);

    @Update("update account set token = #{token} where id = #{id}")
    void updateAccountTokenById(@Param("id") long id, @Param("token") String token);

    @Delete("delete from account where id = #{id}")
    void deleteAccountById(long id);

    @Delete("delete from account where username = #{username}")
    void deleteAccountByUsername(String username);
}
