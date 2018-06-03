package cn.liangjiateng.service;

import cn.liangjiateng.pojo.DO.Category;

import java.util.List;
import java.util.Map;

/**
 * 文章分类服务
 */
public interface CategoryService {

    /**
     * 获取分类信息
     *
     * @param id 分类id
     * @return
     */
    Category getCategoryById(int id) throws Exception;

    /**
     * 列出所有分类，只列出未删除的
     *
     * @return
     */
    List<Category> listCategories();

    /**
     * 获取每个分类下文章数量, 只统计线上文章
     *
     * @return key:category -> long: count
     */
    Map<Category, Long> countCategoryArticleNum();

    /**
     * 更新分类名称
     *
     * @param id      分类id
     * @param newName 新名称
     */
    void updateCategoryNameById(int id, String newName) throws Exception;

    /**
     * 删除一个分类
     *
     * @param id 分类id
     */
    void deleteCategoryById(int id) throws Exception;
}
