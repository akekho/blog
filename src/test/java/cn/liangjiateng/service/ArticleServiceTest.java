package cn.liangjiateng.service;

import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.Article;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listArticlesSortBy() throws Exception {
        articleService.listArticlesSortBy(Article.SortType.TIME_DESC, config.getSmallPage(),1);
    }

    @Test
    public void listHottestArticles() {
    }

    @Test
    public void listDrafts() {
    }

    @Test
    public void listArticleByNameSortBy() {
    }

    @Test
    public void getArticleById() {
    }

    @Test
    public void updateArticle() {
    }

    @Test
    public void postArticleById() {
    }

    @Test
    public void offlineArticleById() {
    }

    @Test
    public void deleteArticleById() {
    }

    @Test
    public void createNewArticle() {
    }
}