package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.ArticleMapper;
import cn.liangjiateng.mapper.CategoryMapper;
import cn.liangjiateng.pojo.DO.Article;
import cn.liangjiateng.pojo.DO.Category;
import cn.liangjiateng.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Category getCategoryById(int id) throws ServiceException {
        Category category = categoryMapper.getCategoryById(id);
        if (category == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "分类不存在");
        return category;
    }

    @Override
    public List<Category> listCategories() {
        return categoryMapper.listCategories();
    }

    @Override
    public Map<Category, Long> countCategoryArticleNum() {
        List<Category> categories = listCategories();
        Map<Category, Long> res = new HashMap<>();
        for (Category c : categories) {
            res.put(c, articleMapper.countArticlesByCategoryIdAndStatus(c.getId(), Article.Status.ONLINE.getVal()));
        }
        return res;
    }

    @Override
    public void updateCategoryNameById(int id, String newName) throws ServiceException {
        Category category = getCategoryById(id);
        categoryMapper.updateCategoryNameById(category.getId(), newName);
    }

    @Override
    public void deleteCategoryById(int id) throws ServiceException {
        Category category = getCategoryById(id);
        List<Article> articles = articleMapper.listArticlesByCategoryId(category.getId());
        for (Article article : articles) {
            //0 代表空category
            article.setCategoryId(0);
            articleMapper.updateArticle(article);
        }
        categoryMapper.deleteCategoryById(category.getId());
    }
}
