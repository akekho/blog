package cn.liangjiateng.service;

import cn.liangjiateng.pojo.DO.Article;
import cn.liangjiateng.util.Page;

import java.util.List;

/**
 * 文章服务
 * Created by Jiateng on 5/29/18.
 */
public interface ArticleService {
    /**
     * 列出上线的所有文章
     *
     * @param sortType 排序方式 1.TIME_DESC 2.TIME_ASC 3.PV
     * @param pageSize 每页数据量大小
     * @param page     页码
     * @return
     * @throws Exception
     */
    Page<Article> listArticlesSortBy(Article.SortType sortType, int pageSize, int page) throws Exception;

    /**
     * 列出高访问量文章，按时间从DESC排序
     *
     * @param pageSize 每页数据量大小
     * @return
     * @throws Exception
     */
    List<Article> listHottestArticles(int pageSize) throws Exception;

    /**
     * 列出草稿
     *
     * @param sortType 排序方式 1.TIME_DESC 2.TIME_ASC 3.PV
     * @param pageSize 每页数据量大小
     * @param page     页码
     * @return
     * @throws Exception
     */
    Page<Article> listDrafts(Article.SortType sortType, int pageSize, int page) throws Exception;

    /**
     * 根据名称模糊查询文章
     *
     * @param sortType 排序方式 1.TIME_DESC 2.TIME_ASC 3.PV
     * @param name     文章名称
     * @param pageSize 每页数据量大小
     * @param page     页码
     * @return
     * @throws Exception
     */
    Page<Article> listArticleByNameSortBy(Article.SortType sortType, String name, int pageSize, int page) throws Exception;

    /**
     * 根据文章分类列出文章, 列出线上的
     *
     * @param sortType   排序方式 1.TIME_DESC 2.TIME_ASC 3.PV
     * @param categoryId 分类Id
     * @param pageSize   每页数据量大小
     * @param page       页码
     * @return
     * @throws Exception
     */
    Page<Article> listArticleByCategorySortBy(Article.SortType sortType, int categoryId, int pageSize, int page) throws Exception;

    /**
     * 获取文章
     *
     * @param id 文章id
     * @return
     * @throws Exception
     */
    Article getArticleById(int id) throws Exception;

    /**
     * 更新文章
     *
     * @param article 文章，没设定的字段不做更新
     * @throws Exception
     */
    void updateArticle(Article article) throws Exception;

    /**
     * 文章上线
     *
     * @param id 文章id
     * @throws Exception
     */
    void postArticleById(int id) throws Exception;

    /**
     * 文章下线
     *
     * @param id 文章id
     * @throws Exception
     */
    void offlineArticleById(int id) throws Exception;

    /**
     * 给文章增加访问量 Todo: 考虑一下并发场景
     *
     * @param id  文章id
     * @param cnt 增加的访问量
     * @throws Exception
     */
    void addPvById(int id, int cnt) throws Exception;

    /**
     * 删除文章
     *
     * @param id 文章id
     * @throws Exception
     */
    void deleteArticleById(int id) throws Exception;

    /**
     * 创建新文章，默认没有上线
     *
     * @param article 文章
     * @throws Exception
     */
    void createNewArticle(Article article) throws Exception;

}
