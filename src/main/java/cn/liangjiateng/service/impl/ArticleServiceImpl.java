package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.ArticleMapper;
import cn.liangjiateng.pojo.DO.Article;
import cn.liangjiateng.service.ArticleService;
import cn.liangjiateng.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jiateng on 5/29/18.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public Page<Article> listArticlesSortBy(Article.SortType sortType, int pageSize, int page) throws Exception {
        if (pageSize <= 0 || page <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long count = articleMapper.countArticlesByStatus(Article.Status.ONLINE.getVal());
        Page<Article> pageHolder = new Page<>(pageSize);
        pageHolder.setMaxCount(count);
        pageHolder.setPage(page);
        List<Article> articles = articleMapper.listArticlesSortBy(sortType.getVal(), Article.Status.ONLINE.getVal(), pageHolder);
        pageHolder.setData(articles);
        return pageHolder;
    }

    @Override
    public List<Article> listHottestArticles(int pageSize) throws Exception {
        if (pageSize <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        Page<Article> page = new Page<>(pageSize);
        page.setPage(1);
        return articleMapper.listArticlesSortBy(Article.SortType.PV.getVal(), Article.Status.ONLINE.getVal(), page);
    }

    @Override
    public Page<Article> listDrafts(Article.SortType sortType, int pageSize, int page) throws Exception {
        if (pageSize <= 0 || page <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long count = articleMapper.countArticlesByStatus(Article.Status.OFFLINE.getVal());
        Page<Article> pageHolder = new Page<>(pageSize);
        pageHolder.setMaxCount(count);
        pageHolder.setPage(page);
        List<Article> articles = articleMapper.listArticlesSortBy(sortType.getVal(), Article.Status.OFFLINE.getVal(), pageHolder);
        pageHolder.setData(articles);
        return pageHolder;
    }

    @Override
    public Page<Article> listArticleByNameSortBy(Article.SortType sortType, String name, int pageSize, int page) throws Exception {
        if (pageSize <= 0 || page <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long count = articleMapper.countArticlesByStatus(Article.Status.ONLINE.getVal());
        Page<Article> pageHolder = new Page<>(pageSize);
        pageHolder.setMaxCount(count);
        pageHolder.setPage(page);
        List<Article> articles = articleMapper.listArticlesByNameSortBy(name, sortType.getVal(), Article.Status.ONLINE.getVal(), pageHolder);
        pageHolder.setData(articles);
        return pageHolder;
    }

    @Override
    public Page<Article> listArticleByCategorySortBy(Article.SortType sortType, int categoryId, int pageSize, int page) throws Exception {
        if (pageSize <= 0 || page <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long count = articleMapper.countArticlesByCategoryIdAndStatus(categoryId, Article.Status.ONLINE.getVal());
        Page<Article> pageHolder = new Page<>(pageSize);
        pageHolder.setMaxCount(count);
        pageHolder.setPage(page);
        List<Article> articles = articleMapper.listArticlesByCategoryIdSortBy(categoryId, sortType.getVal(), Article.Status.ONLINE.getVal(), pageHolder);
        pageHolder.setData(articles);
        return pageHolder;
    }


    @Override
    public Article getArticleById(int id) throws Exception {
        Article article = articleMapper.getArticleById(id);
        if (article == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "文章不存在");
        return article;
    }

    @Override
    public void updateArticle(Article article) throws Exception {
        if (article == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        if (article.getTitle().length() > 30)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "文章标题不能超过30个字符");
        Article check = articleMapper.getArticleById(article.getId());
        if (check == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "文章不存在");
        check = articleMapper.getArticleByTitle(article.getTitle());
        if (check != null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "文章标题已存在");
        articleMapper.updateArticle(article);
    }

    @Override
    public void postArticleById(int id) throws Exception {
        Article article = articleMapper.getArticleById(id);
        if (article == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "文章不存在");
        articleMapper.updateArticleStatusById(id, Article.Status.ONLINE.getVal());
    }

    @Override
    public void offlineArticleById(int id) throws Exception {
        Article article = articleMapper.getArticleById(id);
        if (article == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "文章不存在");
        articleMapper.updateArticleStatusById(id, Article.Status.OFFLINE.getVal());
    }

    @Override
    public void addPvById(int id, int cnt) throws Exception {
        Article article = articleMapper.getArticleById(id);
        if (article == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "文章不存在");
        articleMapper.updateArticlePvById(id, article.getPv() + cnt);
    }

    @Override
    public void deleteArticleById(int id) throws Exception {
        Article article = articleMapper.getArticleById(id);
        if (article == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "文章不存在");
        articleMapper.updateArticleStatusById(id, Article.Status.DELETED.getVal());
    }

    @Override
    public void createNewArticle(Article article) throws Exception {
        if (article == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        if (article.getTitle().length() > 30)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "文章标题不能超过30个字符");
        Article check = articleMapper.getArticleByTitle(article.getTitle());
        if (check != null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "文章标题已存在");
        article.setStatus(Article.Status.OFFLINE.getVal());
        articleMapper.insertArticle(article);
    }


}
