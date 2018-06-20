package cn.liangjiateng.controller.views.front;

import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.DO.DocTemplate;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.pojo.VO.DocTemplateVO;
import cn.liangjiateng.service.DocTemplateService;
import cn.liangjiateng.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/blog/docs")
public class FrontDocController {

    @Autowired
    private DocTemplateService docTemplateService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private Config config;

    @GetMapping
    public String docs(ModelMap modelMap) throws Exception {
        //最热模板数据
        List<DocTemplate> docTemplates = docTemplateService.listHottestDocs(config.getLargePage());
        modelMap.addAttribute("templates", batchTransferVO(docTemplates));
        return "doc";
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
