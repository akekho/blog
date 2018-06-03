package cn.liangjiateng.controller;

import cn.liangjiateng.common.JsonResponse;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.DO.Article;
import cn.liangjiateng.pojo.DO.Category;
import cn.liangjiateng.pojo.VO.ArticleVO;
import cn.liangjiateng.service.ArticleService;
import cn.liangjiateng.service.CategoryService;
import cn.liangjiateng.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/articles", produces = "application/json")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private Config config;
    
    @GetMapping("/{id}")
    public JsonResponse getArticleById(@PathVariable int id) throws Exception {
        Article article = articleService.getArticleById(id);
        articleService.addPvById(article.getId(), 1);
        return new JsonResponse(transferVO(article));
    }

    @GetMapping
    public JsonResponse listArticlesSortBy(@RequestParam int sortType, @RequestParam int page) throws Exception {
        Page<Article> holder = articleService.listArticlesSortBy(Article.getSortType(sortType), config.getSmallPage(), page);
        List<Article> articles = holder.getData();
        List<ArticleVO> articleVOS = batchTransferVO(articles);
        Page<ArticleVO> res = new Page<>(page, holder.getMaxPage(), holder.getMaxCount(), articleVOS);
        return new JsonResponse(res);
    }

    @GetMapping("/popular")
    public JsonResponse listHottestArticles() throws Exception {
        List<Article> articles = articleService.listHottestArticles(config.getMediumPage());
        List<ArticleVO> articleVOS = batchTransferVO(articles);
        return new JsonResponse(articleVOS);
    }

    @GetMapping("/drafts")
    public JsonResponse listDrafts(@RequestParam int sortType, @RequestParam int page) throws Exception {
        Page<Article> holder = articleService.listDrafts(Article.getSortType(sortType), config.getLargePage(), page);
        List<Article> articles = holder.getData();
        List<ArticleVO> articleVOS = batchTransferVO(articles);
        Page<ArticleVO> res = new Page<>(page, holder.getMaxPage(), holder.getMaxCount(), articleVOS);
        return new JsonResponse(res);
    }

    @GetMapping("/{name}")
    public JsonResponse listArticleByNameSortBy(@RequestParam int sortType, @RequestParam int page, @PathVariable String name) throws Exception {
        Page<Article> holder = articleService.listArticleByNameSortBy(Article.getSortType(sortType), name, config.getLargePage(), page);
        List<Article> articles = holder.getData();
        List<ArticleVO> articleVOS = batchTransferVO(articles);
        Page<ArticleVO> res = new Page<>(page, holder.getMaxPage(), holder.getMaxCount(), articleVOS);
        return new JsonResponse(res);
    }

    @GetMapping("/category_id/{categoryId}")
    public JsonResponse listArticleByCategorySortBy(@RequestParam int sortType, @RequestParam int page, @PathVariable int categoryId) throws Exception {
        Page<Article> holder = articleService.listArticleByCategorySortBy(Article.getSortType(sortType), categoryId, config.getLargePage(), page);
        List<Article> articles = holder.getData();
        List<ArticleVO> articleVOS = batchTransferVO(articles);
        Page<ArticleVO> res = new Page<>(page, holder.getMaxPage(), holder.getMaxCount(), articleVOS);
        return new JsonResponse(res);
    }

    @PutMapping("/{id}")
    public JsonResponse updateArticle(@RequestBody Article article, @PathVariable int id) throws Exception {
        article.setId(id);
        articleService.updateArticle(article);
        return new JsonResponse(null);
    }

    @PutMapping("/post/{id}")
    public JsonResponse postArticleById(@PathVariable int id) throws Exception {
        articleService.postArticleById(id);
        return new JsonResponse(null);
    }

    @PutMapping("/offline/{id}")
    public JsonResponse offlineArticleById(@PathVariable int id) throws Exception {
        articleService.offlineArticleById(id);
        return new JsonResponse(null);
    }

    @DeleteMapping("/{id}")
    public JsonResponse deleteArticleById(@PathVariable int id) throws Exception {
        articleService.deleteArticleById(id);
        return new JsonResponse(null);
    }

    @PostMapping
    public JsonResponse createNewArticle(@RequestBody Article article) throws Exception {
        articleService.createNewArticle(article);
        return new JsonResponse(null);
    }


    private ArticleVO transferVO(Article article) throws Exception {
        // 0代表无分类
        ArticleVO articleVO = new ArticleVO(article);
        if (article.getCategoryId() != 0) {
            Category category = categoryService.getCategoryById(article.getCategoryId());
            articleVO.setCategoryName(category.getName());
        }
        return articleVO;
    }


    private List<ArticleVO> batchTransferVO(List<Article> articles) throws Exception {
        List<ArticleVO> articleVOS = new ArrayList<>();
        for (Article article : articles) {
            ArticleVO articleVO = new ArticleVO(article);
            // 0代表无分类
            if (article.getCategoryId() != 0) {
                Category category = categoryService.getCategoryById(article.getCategoryId());
                articleVO.setCategoryName(category.getName());
            }
            articleVOS.add(articleVO);
        }
        return articleVOS;
    }

}
