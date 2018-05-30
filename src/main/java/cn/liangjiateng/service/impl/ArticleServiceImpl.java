package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.ArticleMapper;
import cn.liangjiateng.pojo.Article;
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
        if (pageSize <= 0)
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
    public Page<Article> listHottestArticles(int pageSize) throws Exception {
        return null;
    }

    @Override
    public Page<Article> listDrafts(Article.SortType sortType, int pageSize, int page) throws Exception {
        return null;
    }

    @Override
    public Page<Article> listArticleByNameSortBy(Article.SortType sortType, String name, int pageSize, int page) throws Exception {
        return null;
    }


    @Override
    public Article getArticleById(int id) throws Exception {
        return null;
    }

    @Override
    public void updateArticle(Article article) throws Exception {

    }

    @Override
    public void postArticleById(int id) throws Exception {

    }

    @Override
    public void offlineArticleById(int id) throws Exception {

    }

    @Override
    public void deleteArticleById(int id) throws Exception {

    }

    @Override
    public void createNewArticle(Article article) throws Exception {

    }
}
