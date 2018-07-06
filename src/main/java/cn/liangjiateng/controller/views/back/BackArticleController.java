package cn.liangjiateng.controller.views.back;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.pojo.VO.ArticleVO;
import cn.liangjiateng.pojo.VO.CategoryVO;
import cn.liangjiateng.pojo.VO.ImageVO;
import cn.liangjiateng.service.ImageService;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Jiateng on 6/6/18.
 */
@Controller
@RequestMapping(value = "/back/articles")
public class BackArticleController {

    @Autowired
    private Config config;
    @Autowired
    private ImageService imageService;

    @GetMapping
    public String articles(ModelMap modelMap, @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "0") int sortType,
                           @RequestParam(defaultValue = "0") int categoryId) throws IOException, ServiceException {
        //文章列表数据
        String json;
        if (categoryId == 0)
            json = HttpUtil.get(config.getUrl("/api/articles?page=" + page + "&sortType=" + sortType));
        else
            json = HttpUtil.get(config.getUrl("/api/articles/category_id/" + categoryId + "?page=" + page + "&sortType=" + sortType));
        json = JsonUtil.getDataAndCheck(json);
        Page<ArticleVO> holder = JsonUtil.string2Bean(json, Page.class);
        modelMap.addAttribute("page", holder.getPage());
        modelMap.addAttribute("maxPage", holder.getMaxPage());
        modelMap.addAttribute("articles", holder.getData());
        modelMap.addAttribute("status", 0); //标识当前页面显示状态 0-普通 1-通过名字搜索

        //草稿数量
        json = HttpUtil.get(config.getUrl("/api/articles/count/0"));
        json = JsonUtil.getDataAndCheck(json);
        modelMap.addAttribute("draft_cnt", json);

        //分组数据
        json = HttpUtil.get(config.getUrl("/api/categories"));
        json = JsonUtil.getDataAndCheck(json);
        List<CategoryVO> categories = JsonUtil.string2List(json, CategoryVO.class);
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("category_id", categoryId);
        return "back_article_management";
    }

    @GetMapping("/{name}")
    public String articles(ModelMap modelMap, @RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "0") int sortType,
                           @PathVariable String name) throws IOException, ServiceException {
        //搜索文章列表数据
        name = URLEncoder.encode(name, "UTF-8");
        String json = HttpUtil.get(config.getUrl("/api/articles/name/" + name + "?page=" + page + "&sortType=" + sortType));
        json = JsonUtil.getDataAndCheck(json);
        Page<ArticleVO> holder = JsonUtil.string2Bean(json, Page.class);
        modelMap.addAttribute("page", holder.getPage());
        modelMap.addAttribute("maxPage", holder.getMaxPage());
        modelMap.addAttribute("articles", holder.getData());
        modelMap.addAttribute("status", 1); //标识当前页面显示状态 0-普通 1-通过名字搜索

        //草稿数量
        json = HttpUtil.get(config.getUrl("/api/articles/count/0"));
        json = JsonUtil.getDataAndCheck(json);
        modelMap.addAttribute("draft_cnt", json);

        //分组数据
        json = HttpUtil.get(config.getUrl("/api/categories"));
        json = JsonUtil.getDataAndCheck(json);
        List<CategoryVO> categories = JsonUtil.string2List(json, CategoryVO.class);
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("category_id", 0);
        name = URLDecoder.decode(name, "UTF-8");
        modelMap.addAttribute("title", name);
        return "back_article_management";
    }

    @GetMapping("/drafts")
    public String drafts(ModelMap modelMap,
                         @RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "0") int sortType) throws IOException, ServiceException {
        //搜索文章列表数据
        String json = HttpUtil.get(config.getUrl("/api/articles/drafts?page=" + page + "&sortType=" + sortType));
        json = JsonUtil.getDataAndCheck(json);
        Page<ArticleVO> holder = JsonUtil.string2Bean(json, Page.class);
        modelMap.addAttribute("page", holder.getPage());
        modelMap.addAttribute("maxPage", holder.getMaxPage());
        modelMap.addAttribute("articles", holder.getData());

        //草稿数量
        json = HttpUtil.get(config.getUrl("/api/articles/count/0"));
        json = JsonUtil.getDataAndCheck(json);
        modelMap.addAttribute("draft_cnt", json);
        return "back_drafts";
    }

    @GetMapping("/drafts/{id}")
    public String draft(ModelMap modelMap, @PathVariable int id, @RequestParam(defaultValue = "1") int page) throws IOException, ServiceException {
        //草稿数据
        String json = HttpUtil.get(config.getUrl("/api/articles/" + id));
        json = JsonUtil.getDataAndCheck(json);
        ArticleVO articleVO = JsonUtil.string2Bean(json, ArticleVO.class);
        modelMap.addAttribute("article", articleVO);

        //图片列表数据
        json = HttpUtil.get(config.getUrl("/api/images/?page=" + page));
        json = JsonUtil.getDataAndCheck(json);
        Page<Image> holder = JsonUtil.string2Bean(json, Page.class);
        modelMap.addAttribute("page", holder.getPage());
        modelMap.addAttribute("maxPage", holder.getMaxPage());
        modelMap.addAttribute("images", holder.getData());

        //preface 数据
        if(articleVO.getPreface() != null){
            ImageVO imageVO = new ImageVO(imageService.getImageBySlimUrl(articleVO.getPreface().replaceAll(config.getStorageHost(), "")));
            imageVO.setUrl(config.getStorageHost() + imageVO.getUrl());
            imageVO.setThumbUrl(config.getStorageHost() + imageVO.getThumbUrl());
            imageVO.setSlimUrl(config.getStorageHost() + imageVO.getSlimUrl());
            modelMap.addAttribute("image_preface", imageVO);
        }

        //分组数据
        json = HttpUtil.get(config.getUrl("/api/categories"));
        json = JsonUtil.getDataAndCheck(json);
        List<CategoryVO> categories = JsonUtil.string2List(json, CategoryVO.class);
        modelMap.addAttribute("categories", categories);

        //草稿数量
        json = HttpUtil.get(config.getUrl("/api/articles/count/0"));
        json = JsonUtil.getDataAndCheck(json);
        modelMap.addAttribute("draft_cnt", json);
        return "back_new_article";
    }


    @GetMapping("/new_article")
    public String newArticle(ModelMap modelMap, @RequestParam(defaultValue = "1") int page) throws IOException, ServiceException {
        //图片列表数据
        String json = HttpUtil.get(config.getUrl("/api/images/?page=" + page));
        json = JsonUtil.getDataAndCheck(json);
        Page<Image> holder = JsonUtil.string2Bean(json, Page.class);
        modelMap.addAttribute("page", holder.getPage());
        modelMap.addAttribute("maxPage", holder.getMaxPage());
        modelMap.addAttribute("images", holder.getData());
        //分组数据
        json = HttpUtil.get(config.getUrl("/api/categories"));
        json = JsonUtil.getDataAndCheck(json);
        List<CategoryVO> categories = JsonUtil.string2List(json, CategoryVO.class);
        modelMap.addAttribute("categories", categories);

        //草稿数量
        json = HttpUtil.get(config.getUrl("/api/articles/count/0"));
        json = JsonUtil.getDataAndCheck(json);
        modelMap.addAttribute("draft_cnt", json);

        return "back_new_article";
    }

    @GetMapping("/categories")
    public String categories(ModelMap modelMap) throws IOException, ServiceException {

        //分组数据
        String json = HttpUtil.get(config.getUrl("/api/categories"));
        json = JsonUtil.getDataAndCheck(json);
        List<CategoryVO> categories = JsonUtil.string2List(json, CategoryVO.class);
        modelMap.addAttribute("categories", categories);

        //草稿数量
        json = HttpUtil.get(config.getUrl("/api/articles/count/0"));
        json = JsonUtil.getDataAndCheck(json);
        modelMap.addAttribute("draft_cnt", json);
        return "back_categories";
    }


}
