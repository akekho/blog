package cn.liangjiateng.service;

import cn.liangjiateng.pojo.DO.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    Category getCategoryById(int id);

    List<Category> listCategories();

    Map<Integer, Integer> countCategroyArticleNum();

    void updateCategoryNameById(int id, String newName);

    void deleteCategoryById(int id);
}
