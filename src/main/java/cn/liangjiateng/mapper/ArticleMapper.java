package cn.liangjiateng.mapper;

import cn.liangjiateng.pojo.Article;
import cn.liangjiateng.util.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("select * from article where id = #{id}")
    Article getArticleById(int id);

    @Select("<script>select * from article where status = #{status} " +
            "order by <if test=\"sortType == 0\">create_time desc</if>" +
            "<if test=\"sortType == 1\">create_time asc</if>" +
            "<if test=\"sortType == 2\">pv desc</if>" +
            " limit #{page.limit}, #{page.pageSize}</script>")
    List<Article> listArticlesSortBy(int sortType, int status, Page page);

    @Select("<script>select * from article where status = #{status} " +
            "and categoryId = #{categoryId} " +
            "order by <if test=\"sortType == 0\">create_time desc</if>" +
            "<if test=\"sortType == 1\">create_time asc</if>" +
            "<if test=\"sortType == 2\">pv desc</if>" +
            " limit #{page.limit}, #{page.pageSize}</script>")
    List<Article> listArticlesByCategoryIdSortBy(int categoryId, int sortType, int status, Page page);

    @Select("<script>select * from article where status = #{status} " +
            "and name like '%${text}%' " +
            "order by <if test=\"sortType == 0\">create_time desc</if>" +
            "<if test=\"sortType == 1\">create_time asc</if>" +
            "<if test=\"sortType == 2\">pv desc</if>" +
            " limit #{page.limit}, #{page.pageSize}</script>")
    List<Article> listArticlesBySearchNameSortBy(String name, int sortType, int status, Page page);

    @Update("update article set status = #{status}, update_time = now() where id = #{id}")
    void updateArticleStatusById(int id, int status);

    @Update("update article set pv = #{pv}, update_time = now() where id = #{id}")
    void updateArticlePvById(int id, int pv);

    @Update("<script>update article set <if test=\"article.title != null\">title = #{article.title}, </if>" +
            "<if test=\"article.content != null\">content = #{article.content}, </if>" +
            "<if test=\"article.contentMd != null\">content_md = #{article.contentMd}, </if>" +
            "<if test=\"article.preface != null\">preface = #{article.preface}, </if>" +
            "<if test=\"article.status != null\">status = #{article.status}, </if>" +
            "<if test=\"article.pv != null\">status = #{article.pv}, </if>" +
            "update_time = now() " +
            "where id = #{article.id}</script>")
    void updateArticle(Article article);

    @Insert("insert into article(title, content, content_md, status, pv, preface, create_time, update_time) " +
            "values (#{article.title}, #{article.content}, #{article.content_md}, " +
            "#{article.status}, #{article.pv}, #{article.preface}, now(), now())")
    void insertArticle(Article article);

}
