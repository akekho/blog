package cn.liangjiateng.controller.api;

import cn.liangjiateng.common.JsonResponse;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/files", produces = "application/json")
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private Config config;

    @PostMapping("/upload")
    public JsonResponse uploadFile(@RequestParam MultipartFile file) throws ServiceException {
        String fileName = fileService.uploadFile(file, config.getTempPath());
        Map<String, String> res = new HashMap<>();
        res.put("fileName", fileName);
        return new JsonResponse(res);
    }
}
