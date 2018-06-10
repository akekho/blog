package cn.liangjiateng.mapper;

import cn.liangjiateng.pojo.DO.Article;
import cn.liangjiateng.util.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("select count(*) from article where status = #{status}")
    long countArticlesByStatus(int status);

    @Select("select count(*) from article where status = #{status} and category_id = #{categoryId}")
    long countArticlesByCategoryIdAndStatus(@Param("categoryId") int categoryId,
                                            @Param("status") int status);

    @Select("select * from article where id = #{id}")
    Article getArticleById(int id);

    @Select("select * from article where title = #{title}")
    Article getArticleByTitle(String title);

    List<Article> listArticlesSortBy(@Param("sortType") int sortType, @Param("status") int status,
                                     @Param("page") Page page);

    List<Article> listArticlesByCategoryIdSortBy(@Param("categoryId") int categoryId, @Param("sortType") int sortType,
                                                 @Param("status") int status, @Param("page") Page page);

    @Select("select * from article where category_id = #{categoryId} order by create_time desc")
    List<Article> listArticlesByCategoryId(int categoryId);

    List<Article> listArticlesByNameSortBy(@Param("title") String title, @Param("sortType") int sortType,
                                           @Param("status") int status, @Param("page") Page page);

    @Update("update article set status = #{status}, update_time = now() where id = #{id}")
    void updateArticleStatusById(@Param("id") int id, @Param("status") int status);

    @Update("update article set pv = #{pv}, update_time = now() where id = #{id}")
    void updateArticlePvById(@Param("id") int id, @Param("pv") int pv);

    void updateArticle(Article article);

    void insertArticle(Article article);

    @Delete("delete from article")
    void deleteAllArticles();

}
