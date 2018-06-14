package cn.liangjiateng.controller.views;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.VO.ArticleVO;
import cn.liangjiateng.pojo.VO.CategoryVO;
import cn.liangjiateng.util.HttpUtil;
import cn.liangjiateng.util.JsonUtil;
import cn.liangjiateng.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/blog")
public class BlogFrontController {

    @Autowired
    private Config config;

    @GetMapping("/home")
    public String main(ModelMap modelMap, @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "0") int sortType,
                       @RequestParam(defaultValue = "0") int categoryId) throws IOException, ServiceException {
        //主页数据
        String json;
        if (categoryId == 0)
            json = HttpUtil.get(config.getUrl("/api/articles?page=" + page + "&sortType=" + sortType));
        else
            json = HttpUtil.get(config.getUrl("/api/articles/category_id/" + categoryId + "?page=" + page + "&sortType=" + sortType));
        json = JsonUtil.getDataAndCheck(json);
        Page<ArticleVO> holder = JsonUtil.string2Bean(json, Page.class);
        modelMap.addAttribute("page", holder.getPage());
        modelMap.addAttribute("maxPage", holder.getMaxPage());
        modelMap.addAttribute("data", holder.getData());
        //最热文章数据
        json = HttpUtil.get(config.getUrl("/api/articles/popular"));
        json = JsonUtil.getDataAndCheck(json);
        List<ArticleVO> articleVOS = JsonUtil.string2Bean(json, List.class);
        modelMap.addAttribute("popular_data", articleVOS);
        //分类数据
        json = HttpUtil.get(config.getUrl("/api/categories"));
        json = JsonUtil.getDataAndCheck(json);
        List<CategoryVO> categoryVOS = JsonUtil.string2Bean(json, List.class);
        modelMap.addAttribute("category_data", categoryVOS);
        modelMap.addAttribute("category_id", categoryId);

        modelMap.addAttribute("sort_type", sortType);
        return "main";
    }

    @GetMapping("/posts/{id}")
    public String posts(ModelMap modelMap, @PathVariable int id) throws IOException, ServiceException {
        //文章数据
        String json = HttpUtil.get(config.getUrl("/api/articles/" + id));
        json = JsonUtil.getDataAndCheck(json);
        ArticleVO articleVO = JsonUtil.string2Bean(json, ArticleVO.class);
        modelMap.addAttribute("article", articleVO);
        //最热文章数据
        json = HttpUtil.get(config.getUrl("/api/articles/popular"));
        json = JsonUtil.getDataAndCheck(json);
        List<ArticleVO> articleVOS = JsonUtil.string2Bean(json, List.class);
        modelMap.addAttribute("popular_data", articleVOS);
        //分类数据
        json = HttpUtil.get(config.getUrl("/api/categories"));
        json = JsonUtil.getDataAndCheck(json);
        List<CategoryVO> categoryVOS = JsonUtil.string2Bean(json, List.class);
        modelMap.addAttribute("category_data", categoryVOS);
        return "post";
    }
}
