package cn.liangjiateng.controller.views;

import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.pojo.VO.ArticleVO;
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

/**
 * Created by Jiateng on 6/6/18.
 */
@Controller
@RequestMapping(value = "/back")
public class BlogBackController {

    @Autowired
    private Config config;

    @GetMapping("/articles")
    public String articles(ModelMap modelMap, @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "0") int sortType,
                           @RequestParam(defaultValue = "0") int categoryId) throws IOException {
        //文章列表数据
        String json;
        if (categoryId == 0)
            json = HttpUtil.get(config.getUrl("/api/articles?page=" + page + "&sortType=" + sortType));
        else
            json = HttpUtil.get(config.getUrl("/api/articles/category_id/" + categoryId + "?page=" + page + "&sortType=" + sortType));
        json = JsonUtil.get("data", json);
        Page<ArticleVO> holder = JsonUtil.string2Bean(json, Page.class);
        modelMap.addAttribute("page", holder.getPage());
        modelMap.addAttribute("maxPage", holder.getMaxPage());
        modelMap.addAttribute("articles", holder.getData());
        modelMap.addAttribute("status", 0); //标识当前页面显示状态 0-普通 1-通过名字搜索
        return "back_article_management";
    }

    @GetMapping("/articles/{name}")
    public String articles(ModelMap modelMap, @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "0") int sortType,
                           @PathVariable String name) throws IOException {
        //搜索文章列表数据
        String json = HttpUtil.get(config.getUrl("/api/articles/name/" + name + "?page=" + page + "&sortType=" + sortType));
        json = JsonUtil.get("data", json);
        Page<ArticleVO> holder = JsonUtil.string2Bean(json, Page.class);
        modelMap.addAttribute("page", holder.getPage());
        modelMap.addAttribute("maxPage", holder.getMaxPage());
        modelMap.addAttribute("articles", holder.getData());
        modelMap.addAttribute("status", 1); //标识当前页面显示状态 0-普通 1-通过名字搜索
        return "back_article_management";
    }

    @GetMapping("/new_article")
    public String newArticle(ModelMap modelMap, @RequestParam(defaultValue = "1") int page) throws IOException {
        //图片列表数据
        String json = HttpUtil.get(config.getUrl("/api/images/?page=" + page));
        json = JsonUtil.get("data", json);
        Page<Image> holder = JsonUtil.string2Bean(json, Page.class);
        modelMap.addAttribute("page", holder.getPage());
        modelMap.addAttribute("maxPage", holder.getMaxPage());
        modelMap.addAttribute("images", holder.getData());
        return "back_new_article";
    }


}
