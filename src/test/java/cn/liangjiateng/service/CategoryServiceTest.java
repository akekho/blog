package cn.liangjiateng.service;

import cn.liangjiateng.config.Config;
import cn.liangjiateng.mapper.ArticleMapper;
import cn.liangjiateng.mapper.CategoryMapper;
import cn.liangjiateng.pojo.DO.Article;
import cn.liangjiateng.pojo.DO.Category;
import cn.liangjiateng.service.CategoryService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private Config config;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryService categoryService;

    private Category category;

    @Before
    public void setUp() throws Exception {
        tearDown();
        Category category1 = new Category();
        category1.setName("category1");

        Category category2 = new Category();
        category2.setName("category2");

        categoryMapper.insertCategory(category1);
        categoryMapper.insertCategory(category2);

        category1 = categoryMapper.getCategoryByName("category1");
        category2 = categoryMapper.getCategoryByName("category2");

        for (int i = 1; i <= 100; i++) {
            Article article = new Article();
            article.setContent("<html><p>我是你爸爸</p></html>" + i);
            article.setContentMd("# 我是你爸爸" + i);
            article.setPreface("/cn/liangjiateng/" + (31 * i + 1031 - i * 19) + ".png");
            article.setPv(i * 1972 + i * 21);
            article.setCategoryId(category1.getId());
            article.setTitle("标题啊" + i);
            if (i % 100 == 0)
                article.setStatus(Article.Status.DELETED.getVal());
            else if (i % 10 == 0){

            }
            else {
                if (i % 3 == 0)
                    article.setCategoryId(category2.getId());
                article.setStatus(Article.Status.ONLINE.getVal());
            }

            articleMapper.insertArticle(article);
        }
        category = category1;

    }

    @After
    public void tearDown() throws Exception {
        articleMapper.deleteAllArticles();
        categoryMapper.deleteAllCategories();
    }

    @Test
    public void getCategoryById() throws Exception {
        Category category = categoryService.getCategoryById(this.category.getId());
        Assert.assertEquals(this.category.getName(), category.getName());
    }

    @Test
    public void listCategories() {
        List<Category> categoryList = categoryService.listCategories();
        Assert.assertEquals(2, categoryList.size());
    }

    @Test
    public void countCategoryArticleNum() {
        Map<Category, Long> res = categoryService.countCategoryArticleNum();
        for (Map.Entry<Category, Long> entry : res.entrySet()) {
            if (entry.getKey().getName().equals("category1")) {
                Assert.assertEquals(60, (long) entry.getValue());
            } else if (entry.getKey().getName().equals("category2")) {
                Assert.assertEquals(30, (long) entry.getValue());
            }
        }
    }

    @Test
    public void updateCategoryNameById() throws Exception {
        categoryService.updateCategoryNameById(this.category.getId(), "xxxx");
        Category category = categoryService.getCategoryById(this.category.getId());
        Assert.assertEquals("xxxx", category.getName());
    }

    @Test
    public void deleteCategoryById() throws Exception {
        categoryService.deleteCategoryById(this.category.getId());
        List<Article> articles = articleMapper.listArticlesByCategoryId(this.category.getId());
        for (Article article : articles) {
            Article check = articleMapper.getArticleById(article.getId());
            Assert.assertEquals(0, (int) check.getCategoryId());
        }
    }
}