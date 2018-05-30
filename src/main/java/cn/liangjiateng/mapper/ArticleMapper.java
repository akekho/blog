package cn.liangjiateng.mapper;

import cn.liangjiateng.pojo.Article;
import cn.liangjiateng.util.Page;
import org.apache.ibatis.annotations.*;

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

    @Select("<script>select * from article where status = #{status} " +
            "order by <if test=\"sortType == 0\">create_time desc</if>" +
            "<if test=\"sortType == 1\">create_time asc</if>" +
            "<if test=\"sortType == 2\">pv desc</if>" +
            " limit #{page.limit}, #{page.pageSize}</script>")
    List<Article> listArticlesSortBy(@Param("sortType") int sortType, @Param("status") int status,
                                     @Param("page") Page page);

    @Select("<script>select * from article where status = #{status} " +
            "and categoryId = #{categoryId} " +
            "order by <if test=\"sortType == 0\">create_time desc</if>" +
            "<if test=\"sortType == 1\">create_time asc</if>" +
            "<if test=\"sortType == 2\">pv desc</if>" +
            " limit #{page.limit}, #{page.pageSize}</script>")
    List<Article> listArticlesByCategoryIdSortBy(@Param("categoryId") int categoryId, @Param("sortType") int sortType,
                                                 @Param("status") int status, @Param("page") Page page);

    @Select("<script>select * from article where status = #{status} " +
            "and title like concat('%',#{title},'%') " +
            "order by <if test=\"sortType == 0\">create_time desc</if>" +
            "<if test=\"sortType == 1\">create_time asc</if>" +
            "<if test=\"sortType == 2\">pv desc</if>" +
            " limit #{page.limit}, #{page.pageSize}</script>")
    List<Article> listArticlesByNameSortBy(@Param("title") String title, @Param("sortType") int sortType,
                                           @Param("status") int status, @Param("page") Page page);

    @Update("update article set status = #{status}, update_time = now() where id = #{id}")
    void updateArticleStatusById(@Param("id") int id, @Param("status") int status);

    @Update("update article set pv = #{pv}, update_time = now() where id = #{id}")
    void updateArticlePvById(@Param("id") int id, @Param("pv") int pv);

    @Update("<script>update article set <if test=\"title != null\">title = #{title}, </if>" +
            "<if test=\"content != null\">content = #{content}, </if>" +
            "<if test=\"contentMd != null\">content_md = #{contentMd}, </if>" +
            "<if test=\"preface != null\">preface = #{preface}, </if>" +
            "<if test=\"status != null\">status = #{status}, </if>" +
            "<if test=\"pv != null\">status = #{pv}, </if>" +
            "update_time = now() " +
            "where id = #{article.id}</script>")
    void updateArticle(Article article);

    @Insert("insert into article(title, content, content_md, status, pv, preface, create_time, update_time) " +
            "values (#{title}, #{content}, #{contentMd}, " +
            "#{status}, #{pv}, #{preface}, now(), now())")
    void insertArticle(Article article);

    @Delete("delete from article")
    void deleteAllArticles();

}
