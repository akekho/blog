package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.mapper.ArticleMapper;
import cn.liangjiateng.pojo.DO.Article;
import cn.liangjiateng.util.Page;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * Created by Jiateng on 5/29/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private Config config;
    @Autowired
    private ArticleMapper articleMapper;

    private Article article;

    @Before
    public void setUp() throws Exception {
        tearDown();
        for (int i = 1; i <= 100; i++) {
            Article article = new Article();
            article.setContent("<html><p>我是你爸爸</p></html>" + i);
            article.setContentMd("# 我是你爸爸" + i);
            article.setPreface("/cn/liangjiateng/" + (31 * i + 1031 - i * 19) + ".png");
            article.setPv(i * 1972 + i * 21);
            article.setTitle("标题啊" + i);
            if (i % 100 == 0)
                article.setStatus(Article.Status.DELETED.getVal());
            else if (i % 10 == 0)
                article.setStatus(Article.Status.OFFLINE.getVal());
            else
                article.setStatus(Article.Status.ONLINE.getVal());
            articleMapper.insertArticle(article);
        }

    }

    @After
    public void tearDown() throws Exception {
        articleMapper.deleteAllArticles();
    }

    @Test
    public void listArticlesSortBy() throws Exception {
        Page<Article> page = articleService.listArticlesSortBy(Article.SortType.TIME_DESC, config.getSmallPage(), 1);
        Assert.assertEquals(90, page.getMaxCount());
        Assert.assertEquals(config.getSmallPage(), page.getData().size());
        Assert.assertTrue(page.getData().get(0).getCreateTime().getTime() >= page.getData().get(3).getCreateTime().getTime());
    }

    @Test
    public void listHottestArticles() throws Exception {
        List<Article> articles = articleService.listHottestArticles(config.getMediumPage());
        Assert.assertEquals(config.getMediumPage(), articles.size());
        Assert.assertTrue(articles.get(0).getPv() >= articles.get(3).getPv());
        Assert.assertTrue(articles.get(0).getCreateTime().getTime() >= articles.get(3).getCreateTime().getTime());
    }

    @Test
    public void listDrafts() throws Exception {
        Page<Article> page = articleService.listDrafts(Article.SortType.PV, config.getSmallPage(), 1);
        Assert.assertEquals(9, page.getMaxCount());
        Assert.assertEquals(config.getSmallPage(), page.getData().size());
        Assert.assertTrue(page.getData().get(0).getPv() >= page.getData().get(3).getPv());
        Assert.assertEquals(2, page.getMaxPage());
        Assert.assertEquals(Article.Status.OFFLINE.getVal(), (int) page.getData().get(0).getStatus());
    }

    @Test
    public void listArticleByNameSortBy() throws Exception {
        Page<Article> page = articleService.listArticleByNameSortBy(Article.SortType.PV, "啊", config.getSmallPage(), 1);
        Assert.assertEquals(90, page.getMaxCount());
        Assert.assertEquals(config.getSmallPage(), page.getData().size());
        Assert.assertTrue(page.getData().get(0).getPv() >= page.getData().get(3).getPv());
        Assert.assertEquals(18, page.getMaxPage());
        Assert.assertEquals(Article.Status.ONLINE.getVal(), (int) page.getData().get(0).getStatus());
    }

    @Test
    public void getArticleById() throws Exception {
        article = articleMapper.getArticleByTitle("标题啊1");
        Article target = articleService.getArticleById(article.getId());
        Assert.assertEquals(article.getTitle(), target.getTitle());
    }

    @Test(expected = ServiceException.class)
    public void updateArticle() throws Exception {
        article = articleMapper.getArticleByTitle("标题啊1");
        article.setContent("fuck");
        articleService.updateArticle(article);
        Article target = articleService.getArticleById(article.getId());
        Assert.assertEquals("fuck", target.getContent());
        article.setTitle("dahdkjahbjkqwbdkqwbekqwbekjqbwkjqwbfkrjqbekjqbwkjebq");
        articleService.updateArticle(article);
    }

    @Test
    public void postArticleById() throws Exception {
        article = articleMapper.getArticleByTitle("标题啊10");
        Assert.assertEquals(Article.Status.OFFLINE.getVal(), (int) article.getStatus());
        articleService.postArticleById(article.getId());
        article = articleMapper.getArticleByTitle("标题啊10");
        Assert.assertEquals(Article.Status.ONLINE.getVal(), (int) article.getStatus());
    }

    @Test
    public void offlineArticleById() throws Exception {
        article = articleMapper.getArticleByTitle("标题啊1");
        Assert.assertEquals(Article.Status.ONLINE.getVal(), (int) article.getStatus());
        articleService.offlineArticleById(article.getId());
        article = articleMapper.getArticleByTitle("标题啊1");
        Assert.assertEquals(Article.Status.OFFLINE.getVal(), (int) article.getStatus());
    }

    @Test
    public void deleteArticleById() throws Exception {
        article = articleMapper.getArticleByTitle("标题啊1");
        Assert.assertEquals(Article.Status.ONLINE.getVal(), (int) article.getStatus());
        articleService.deleteArticleById(article.getId());
        article = articleMapper.getArticleByTitle("标题啊1");
        Assert.assertEquals(Article.Status.DELETED.getVal(), (int) article.getStatus());
    }

    @Test
    public void createNewArticle() throws Exception {
        Article article = new Article();
        article.setTitle("duck");
        article.setContent("ssss");
        article.setContentMd("# duck");
        article.setPreface("/d/d/w");
        article.setCategoryId(1);
        articleService.createNewArticle(article);
        Article target = articleMapper.getArticleByTitle("duck");
        Assert.assertEquals("ssss", target.getContent());
        Assert.assertEquals(Article.Status.OFFLINE.getVal(), (int) target.getStatus());
        Assert.assertEquals(0, (int) target.getPv());
    }
}