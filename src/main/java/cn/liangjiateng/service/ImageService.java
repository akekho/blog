package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.util.Page;
import com.qiniu.common.QiniuException;

import java.io.File;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

/**
 * 图片服务
 */
public interface ImageService {

    /**
     * 获取图片
     *
     * @param id 图片id
     * @return
     */
    Image getImageById(int id) throws ServiceException;

    /**
     * 根据url获取图片
     *
     * @param url url
     * @return
     */
    Image getImageBySlimUrl(String url) throws ServiceException;

    /**
     * 分页查询图片 按create_time降序排序
     *
     * @param pageSize 每页数据量
     * @param page     页码
     * @return
     */
    Page<Image> listImages(int pageSize, int page) throws ServiceException;

    /**
     * 插入一张图片
     *
     * @param file 图片
     */
    void insertImage(File file) throws NoSuchAlgorithmException, ServiceException;

    /**
     * 流方式插入图片
     *
     * @param is       流
     * @param fileName 文件名
     */
    void insertImageByStream(InputStream is, String fileName) throws ServiceException, NoSuchAlgorithmException;

    /**
     * 删除一张图片
     *
     * @param id 图片id
     */
    void deleteImageById(int id) throws ServiceException;

}
