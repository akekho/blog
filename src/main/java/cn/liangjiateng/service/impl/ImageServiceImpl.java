package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.ImageMapper;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.service.ImageService;
import cn.liangjiateng.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public Image getImageById(int id) throws ServiceException {
        Image image = imageMapper.getImageById(id);
        if (image == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "图片不存在");
        return null;
    }

    @Override
    public Page<Image> listImages(int pageSize, int page) {
        long cnt = imageMapper.countImages();
        Page<Image> holder = new Page<>(pageSize);
        holder.setPage(page);
        holder.setMaxCount(cnt);
        List<Image> data = imageMapper.listImages(holder);
        holder.setData(data);
        return holder;
    }

    @Override
    public void insertImage(Image image) {

    }


    @Override
    public void deleteImageById(int id) {

    }
}
