package cn.liangjiateng.controller.api;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.JsonResponse;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.DO.Article;
import cn.liangjiateng.pojo.DO.Category;
import cn.liangjiateng.pojo.DO.DocTemplate;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.pojo.VO.ArticleVO;
import cn.liangjiateng.pojo.VO.DocTemplateVO;
import cn.liangjiateng.service.*;
import cn.liangjiateng.util.FileUtil;
import cn.liangjiateng.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/templates", produces = "application/json")
public class DocTemplateController {

    @Autowired
    private DocTemplateService docTemplateService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private FileService fileService;
    @Autowired
    private Config config;

    @GetMapping("/count/{status}")
    public JsonResponse countTemplateByStatus(@PathVariable int status) throws ServiceException {
        return new JsonResponse(docTemplateService.countDocByStatus(DocTemplate.getStatusType(status)));
    }

    @GetMapping("/{id}")
    public JsonResponse getTemplateById(@PathVariable int id) throws Exception {
        DocTemplate docTemplate = docTemplateService.getDocById(id);
        return new JsonResponse(transferVO(docTemplate));
    }

    @GetMapping
    public JsonResponse listTemplatesSortBy(@RequestParam int sortType, @RequestParam int page) throws Exception {
        Page<DocTemplate> holder = docTemplateService.listDocsSortBy(DocTemplate.getSortType(sortType), config.getMediumPage(), page);
        List<DocTemplate> articles = holder.getData();
        List<DocTemplateVO> docTemplateVOS = batchTransferVO(articles);
        Page<DocTemplateVO> res = new Page<>(page, holder.getPageSize(), holder.getMaxCount(), docTemplateVOS);
        return new JsonResponse(res);
    }

    @GetMapping("/popular")
    public JsonResponse listHottestTemplates() throws Exception {
        List<DocTemplate> docTemplates = docTemplateService.listHottestDocs(config.getMediumPage());
        List<DocTemplateVO> docTemplateVOS = batchTransferVO(docTemplates);
        return new JsonResponse(docTemplateVOS);
    }

    @GetMapping("/drafts")
    public JsonResponse listDrafts(@RequestParam int sortType, @RequestParam int page) throws Exception {
        Page<DocTemplate> holder = docTemplateService.listDrafts(DocTemplate.getSortType(sortType), config.getLargePage(), page);
        List<DocTemplate> docTemplates = holder.getData();
        List<DocTemplateVO> docTemplateVOS = batchTransferVO(docTemplates);
        Page<DocTemplateVO> res = new Page<>(page, holder.getPageSize(), holder.getMaxCount(), docTemplateVOS);
        return new JsonResponse(res);
    }


    @PutMapping("/{id}")
    public JsonResponse updateTemplate(@RequestBody DocTemplate docTemplate, @PathVariable int id) throws Exception {
        docTemplate.setId(id);
        docTemplateService.updateDoc(docTemplate);
        return new JsonResponse(null);
    }

    @PutMapping("/post/{id}")
    public JsonResponse postTemplateById(@PathVariable int id) throws Exception {
        docTemplateService.postDocById(id);
        return new JsonResponse(null);
    }

    @PutMapping("/offline/{id}")
    public JsonResponse offlineTemplateById(@PathVariable int id) throws Exception {
        docTemplateService.offlineDocById(id);
        return new JsonResponse(null);
    }

    @DeleteMapping("/{id}")
    public JsonResponse deleteTemplateById(@PathVariable int id) throws Exception {
        docTemplateService.deleteDocById(id);
        return new JsonResponse(null);
    }

    @PostMapping
    public JsonResponse createNewTemplate(@RequestParam String fileName, @RequestBody DocTemplate template) throws Exception {
        //检查文件
        String[] strs = FileUtil.getFileNameAndExtension(fileName);
        if (strs == null || (!strs[1].equals("pdf") && !strs[1].equals("PDF"))) {
            fileService.deleteFile(config.getTempPath(), fileName);
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "文件格式不正确");
        }
        //转成image
        InputStream imageStream = FileUtil.pdf2Image(config.getTempPath() + fileName);
        //删除pdf
        fileService.deleteFile(config.getTempPath(), fileName);
        //上传image
        int imageId = imageService.insertImageByStream(imageStream, strs[0] + ".PNG");
        template.setImageId(imageId);
        //新建模板
        docTemplateService.createNewDoc(template);

        return new JsonResponse(null);
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
            docTemplateVO.setSlimUrl(config.getStorageHost() + docTemplateVO.getSlimUrl());
            docTemplateVO.setOriginUrl(config.getStorageHost() + docTemplateVO.getOriginUrl());
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
