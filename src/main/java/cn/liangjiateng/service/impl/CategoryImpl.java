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
import java.util.LinkedHashMap;
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
        Map<Category, Long> res = new LinkedHashMap<>();
        for (Category c : categories) {
            res.put(c, articleMapper.countArticlesByCategoryIdAndStatus(c.getId(), Article.Status.ONLINE.getVal()));
        }
        return res;
    }

    @Override
    public void updateCategoryNameById(int id, String newName) throws ServiceException {
        if (newName == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "名字不能为空");
        Category category = getCategoryById(id);
        if (categoryMapper.getCategoryByName(newName) != null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "分类名已经存在");
        categoryMapper.updateCategoryNameById(category.getId(), newName);
    }

    @Override
    public void createCategory(String name) throws ServiceException {
        if (name == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "名字不能为空");
        Category category = categoryMapper.getCategoryByName(name);
        if (category != null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "重复分类名");
        Category newCategory = new Category();
        newCategory.setName(name);
        categoryMapper.insertCategory(newCategory);
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
