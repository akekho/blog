package cn.liangjiateng.mapper;
import cn.liangjiateng.pojo.DO.DocTemplate;
import cn.liangjiateng.util.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * resume模板mapper
 * Created by Jiateng on 6/16/18.
 */
public interface DocTemplateMapper {

    @Select("select count(*) from doc_template where status = #{status}")
    long countDocByStatus(int status);

    @Select("select * from doc_template where id = #{id} and status = #{status}")
    DocTemplate getDocByIdAndStatus(@Param("id") int id, @Param("status") int status);

    List<DocTemplate> listDocsSortBy(@Param("sortType") int sortType, @Param("status") int status,
                                     @Param("page") Page page);

    @Update("update doc_template set status = #{status}, update_time = now() where id = #{id}")
    void updateDocStatusById(@Param("id") int id, @Param("status") int status);

    @Update("update doc_template set use_time = #{useTime}, update_time = now() where id = #{id}")
    void updateDocUseTimeById(@Param("id") int id, @Param("useTime") int useTime);

    void updateDoc(DocTemplate docTemplate);

    void insertDoc(DocTemplate DocTemplate);

    @Delete("delete from doc_template")
    void deleteAllDocs();
}
