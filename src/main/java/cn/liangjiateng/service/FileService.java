package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 上传文件
     *
     * @param file 文件
     * @param path 路径（不带文件名）
     * @return 文件名
     * @throws ServiceException
     */
    String uploadFile(MultipartFile file, String path) throws ServiceException;

    /**
     * 删除文件
     * @param path 路径（不带文件名）
     * @param fileName 文件
     */
    void deleteFile(String path, String fileName) throws ServiceException;

}
