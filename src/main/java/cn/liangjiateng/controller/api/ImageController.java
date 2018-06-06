package cn.liangjiateng.controller.api;

import cn.liangjiateng.common.JsonResponse;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.service.ImageService;
import cn.liangjiateng.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/api/images", produces = "application/json")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private Config config;

    @PostMapping("/upload")
    public JsonResponse uploadImage(@RequestParam MultipartFile file) throws IOException, ServiceException, NoSuchAlgorithmException {
        imageService.insertImageByStream(file.getInputStream(), file.getName());
        return new JsonResponse(null);
    }

    @GetMapping("/{id}")
    public JsonResponse getImageById(@PathVariable int id) throws ServiceException {
        Image image = imageService.getImageById(id);
        image.setUrl(config.getStorageHost() + image.getUrl());
        return new JsonResponse(image);
    }

    @GetMapping
    public JsonResponse listImages(@RequestParam int page) throws ServiceException {
        Page<Image> holder = imageService.listImages(config.getMediumPage(), page);
        for (Image image : holder.getData()) {
            image.setUrl(config.getStorageHost() + image.getUrl());
        }
        return new JsonResponse(holder);
    }

    @DeleteMapping("{id}")
    public JsonResponse deleteImageById(@PathVariable int id) throws ServiceException {
        imageService.deleteImageById(id);
        return new JsonResponse(null);
    }
}
