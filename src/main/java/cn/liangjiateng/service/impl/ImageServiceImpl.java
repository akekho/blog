package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.mapper.ImageMapper;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.service.ImageService;
import cn.liangjiateng.util.EncryUtil;
import cn.liangjiateng.util.Page;
import cn.liangjiateng.util.CloudFileUtil;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private CloudFileUtil cloudUtil;
    @Autowired
    private Config config;

    @Override
    public Image getImageById(int id) throws ServiceException {
        Image image = imageMapper.getImageById(id);
        if (image == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "图片不存在");
        return image;
    }

    @Override
    public Image getImageBySlimUrl(String url) throws ServiceException {
        if (url == null || url.equals(""))
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "url不能为空");
        return imageMapper.getImageBySlimUrl(url);
    }

    @Override
    public Page<Image> listImages(int pageSize, int page) throws ServiceException {
        if (page <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long cnt = imageMapper.countImages();
        Page<Image> holder = new Page<>(pageSize);
        holder.setPage(page);
        holder.setMaxCount(cnt);
        List<Image> data = imageMapper.listImages(holder);
        holder.setData(data);
        return holder;
    }

    @Override
    public void insertImage(File file) throws NoSuchAlgorithmException, ServiceException {
        if (!checkExtension(file.getName()))
            throw new ServiceException(ErrorCode.FAIL.getCode(), "文件格式不正确");
        String[] sts = getFileNameAndExtension(file.getName());
        String url = "image/" + EncryUtil.getMd5(System.currentTimeMillis() + sts[0]) + "." + sts[1];
        try {
            cloudUtil.upload(file, config.getStorageBucket(), url);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERR.getCode(), "文件上传失败：" + e.getMessage());
        }
        Image image = new Image();
        image.setName(file.getName());
        image.setUrl(url);
        image.setThumbUrl(url + "?" + config.getThumbUrl());
        image.setSlimUrl(url + "?" + config.getSlimUrl());
        imageMapper.insertImage(image);
    }

    @Override
    public void insertImageByStream(InputStream is, String fileName) throws ServiceException, NoSuchAlgorithmException {
        if (!checkExtension(fileName))
            throw new ServiceException(ErrorCode.FAIL.getCode(), "文件格式不正确");
        String[] sts = getFileNameAndExtension(fileName);
        String url = "image/" + EncryUtil.getMd5(System.currentTimeMillis() + sts[0]) + "." + sts[1];
        try {
            cloudUtil.upload(is, config.getStorageBucket(), url);
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERR.getCode(), "文件上传失败：" + e.getMessage());
        }
        Image image = new Image();
        image.setName(fileName);
        image.setUrl(url);
        image.setThumbUrl(url + "?" + config.getThumbUrl());
        image.setSlimUrl(url + "?" + config.getSlimUrl());
        imageMapper.insertImage(image);
    }


    @Override
    public void deleteImageById(int id) throws ServiceException {
        Image image = imageMapper.getImageById(id);
        if (image == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "图片资源不存在");
        try {
            cloudUtil.delete(image.getUrl(), config.getStorageBucket());
        } catch (QiniuException e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERR.getCode(), "文件删除失败：" + e.getMessage());
        }
        imageMapper.deleteImageById(id);
    }

    private String[] getFileNameAndExtension(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index == -1)
            return null;
        String ext = fileName.substring(index + 1, fileName.length());
        String name = fileName.substring(0, index);
        return new String[]{name, ext};
    }

    private boolean checkExtension(String fileName) {
        String[] strs = getFileNameAndExtension(fileName);
        if (strs == null)
            return false;
        switch (strs[1]) {
            case "jpg":
            case "JPG":
            case "png":
            case "PNG":
            case "gif":
            case "GIF":
            case "jpeg":
            case "JPEG":
                return true;
            default:
                return false;
        }
    }

}
