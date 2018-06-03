package cn.liangjiateng.service;

import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.util.Page;

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
    Image getImageById(int id);

    /**
     * 分页查询图片
     *
     * @param pageSize 每页数据量
     * @param page     页码
     * @return
     */
    Page<Image> listImages(int pageSize, int page);

    /**
     * 插入一张图片
     *
     * @param image 图片
     */
    void insertImage(Image image);

    /**
     * 更新图片，只更新图片，不更新各个字段
     */

    void updateImage();

    /**
     * 删除一张图片
     *
     * @param id 图片id
     */
    void deleteImageById(int id);

}
