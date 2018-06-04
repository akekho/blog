package cn.liangjiateng.controller.api;

import cn.liangjiateng.common.JsonResponse;
import cn.liangjiateng.pojo.DO.Category;
import cn.liangjiateng.pojo.VO.CategoryVO;
import cn.liangjiateng.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/categories", produces = "application/json")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public JsonResponse getCategoryById(@PathVariable int id) throws Exception {
        return new JsonResponse(categoryService.getCategoryById(id));
    }

    @GetMapping
    public JsonResponse listCategories() {
        Map<Category, Long> map = categoryService.countCategoryArticleNum();
        return new JsonResponse(batchTransferVO(map));
    }

    @PutMapping("/{id}/{newName}")
    public JsonResponse updateCategoryNameById(@PathVariable int id, @PathVariable String newName) throws Exception {
        categoryService.updateCategoryNameById(id, newName);
        return new JsonResponse(null);
    }

    @DeleteMapping("/{id}")
    public JsonResponse deleteCategoryById(@PathVariable int id) throws Exception {
        categoryService.deleteCategoryById(id);
        return new JsonResponse(null);
    }

    private List<CategoryVO> batchTransferVO(Map<Category, Long> map) {
        List<CategoryVO> res = new ArrayList<>();
        for (Category category : map.keySet()) {
            CategoryVO categoryVO = new CategoryVO(category);
            categoryVO.setCount(map.getOrDefault(category, 0L));
            res.add(categoryVO);
        }
        return res;
    }
}
