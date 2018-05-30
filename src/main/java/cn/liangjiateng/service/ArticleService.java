package cn.liangjiateng.service;

import cn.liangjiateng.pojo.Article;
import cn.liangjiateng.util.Page;

/**
 * 文章服务
 * Created by Jiateng on 5/29/18.
 */
public interface ArticleService {

    Page<Article> listArticlesSortBy(Article.SortType sortType, int pageSize, int page) throws Exception;

    Page<Article> listHottestArticles(int pageSize) throws Exception;

    Page<Article> listDrafts(Article.SortType sortType, int pageSize, int page) throws Exception;

    Page<Article> listArticleByNameSortBy(Article.SortType sortType, String name, int pageSize, int page) throws Exception;

    Article getArticleById(int id) throws Exception;

    void updateArticle(Article article) throws Exception;

    void postArticleById(int id) throws Exception;

    void offlineArticleById(int id) throws Exception;

    void deleteArticleById(int id) throws Exception;

    void createNewArticle(Article article) throws Exception;

}
