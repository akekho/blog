package cn.liangjiateng.mapper;

import cn.liangjiateng.pojo.DO.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category where id = #{id}")
    Category getCategoryById(int id);

    @Select("select * from category where name = #{name}")
    Category getCategoryByName(String name);

    @Select("select * from category where deleted = 0 order by create_time desc")
    List<Category> listCategories();

    @Update("update category set deleted = 1, update_time = now() where id = #{id}")
    void deleteCategoryById(int id);

    @Insert("insert into category set name = #{name}, deleted = 0")
    void insertCategory(Category category);

    @Update("update category set name = #{name}, update_time = now() where id = #{id}")
    void updateCategoryNameById(@Param("id") int id, @Param("name") String name);

    @Delete("delete from category")
    void deleteAllCategories();
}
