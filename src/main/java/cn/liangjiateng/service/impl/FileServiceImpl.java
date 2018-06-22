package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.service.FileService;
import cn.liangjiateng.util.EncryUtil;
import cn.liangjiateng.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;


@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(MultipartFile file, String path) throws ServiceException {
        String[] strs = FileUtil.getFileNameAndExtension(file.getOriginalFilename());
        if (strs == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), "文件格式不正确");
        String fileName;
        try {
            fileName = EncryUtil.getMd5(System.currentTimeMillis() + strs[0]) + "." + strs[1];
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERR.getCode(), "文件上传失败：" + e.getMessage());
        }
        File saveFile = new File(path + fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            byte[] bytes = file.getBytes();
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (Exception e) {
            throw new ServiceException(ErrorCode.INTERNAL_ERR.getCode(), "文件上传失败：" + e.getMessage());
        }
        return fileName;
    }

    @Override
    public void deleteFile(String path, String fileName) throws ServiceException {
        File file = new File(path + fileName);
        if (file.exists()) {
            if (!file.delete())
                throw new ServiceException(ErrorCode.INTERNAL_ERR.getCode(), "文件无法删除，检查一下权限");
        }
    }


}
