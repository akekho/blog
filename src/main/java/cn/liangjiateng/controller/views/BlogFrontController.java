package cn.liangjiateng.controller.views;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.DO.DocTemplate;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.pojo.VO.ArticleVO;
import cn.liangjiateng.pojo.VO.CategoryVO;
import cn.liangjiateng.pojo.VO.DocTemplateVO;
import cn.liangjiateng.service.DocTemplateService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/blog")
public class BlogFrontController {

    @Autowired
    private Config config;
    @Autowired
    private DocTemplateService docTemplateService;
    @Autowired
    private ImageService imageService;

    //Todo: 重构一下
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

    @GetMapping("/resume")
    public String resume(ModelMap modelMap) throws Exception {
        //最热模板数据
        List<DocTemplate> docTemplates = docTemplateService.listHottestDocs(config.getLargePage());
        modelMap.addAttribute("templates", batchTransferVO(docTemplates));
        return "resume";
    }

    private DocTemplateVO transferVO(DocTemplate docTemplate) throws Exception {
        DocTemplateVO docTemplateVO;
        // 0 代表无图
        if (docTemplate.getImageId() == 0) {
            docTemplateVO = new DocTemplateVO(docTemplate);
        } else {
            Image image = imageService.getImageById(docTemplate.getImageId());
            docTemplateVO = new DocTemplateVO(docTemplate, image);
            docTemplateVO.setThumbUrl(config.getStorageHost() + docTemplateVO.getThumbUrl());
            docTemplateVO.setOriginUrl(config.getStorageHost() + docTemplateVO.getOriginUrl());
            docTemplateVO.setSlimUrl(config.getStorageHost() + docTemplateVO.getSlimUrl());
        }
        return docTemplateVO;
    }


    private List<DocTemplateVO> batchTransferVO(List<DocTemplate> docTemplates) throws Exception {
        List<DocTemplateVO> docTemplateList = new ArrayList<>();
        for (DocTemplate docTemplate : docTemplates) {
            docTemplateList.add(transferVO(docTemplate));
        }
        return docTemplateList;
    }
}
