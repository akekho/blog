package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.mapper.ImageMapper;
import cn.liangjiateng.pojo.DO.Image;
import cn.liangjiateng.util.Page;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Jiateng on 6/5/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    private File f1, f2, f3;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageMapper imageMapper;

    @Before
    public void setUp() throws Exception {
        //注意单元测试本地存在文件
        f1 = new File("/Users/liangjiateng/Desktop/1.jpg");
        f2 = new File("/Users/liangjiateng/Desktop/2.jpg");
        f3 = new File("/Users/liangjiateng/Desktop/3.jpg");
        tearDown();

        Image image = new Image();
        image.setName("sanch");
        image.setUrl("/dasda/1.png");
        imageMapper.insertImage(image);

        image.setName("sanch1");
        image.setUrl("/dasda/2.png");
        imageMapper.insertImage(image);

        image.setName("sanch3");
        image.setUrl("/dasda/3.png");
        imageMapper.insertImage(image);
    }

    @After
    public void tearDown() throws Exception {
        deleteImageById();
        imageMapper.deleteAll();
    }

    @Test
    public void getImageById() throws ServiceException {
        Image image = imageMapper.getImageByName("sanch");
        Assert.assertEquals(image.getName(), imageService.getImageById(image.getId()).getName());
    }

    @Test
    public void listImages() {
        Page<Image> images = imageService.listImages(20, 1);
        Assert.assertEquals(3, images.getData().size());
        Assert.assertEquals(3, images.getMaxCount());
        Assert.assertEquals(1, images.getMaxPage());
    }

    @Test
    public void insertImage() throws ServiceException, NoSuchAlgorithmException {
        imageService.insertImage(f1);
        imageService.insertImage(f2);
        imageService.insertImage(f3);
        Image image = imageMapper.getImageByName(f1.getName());
        Assert.assertNotNull(image);
    }

    private void deleteImageById() throws ServiceException, NoSuchAlgorithmException {

        Image image = imageMapper.getImageByName(f1.getName());
        if (image != null) {
            imageService.deleteImageById(image.getId());
            image = imageMapper.getImageByName(f1.getName());
            Assert.assertNull(image);
        }
        image = imageMapper.getImageByName(f2.getName());
        if (image != null) {
            imageService.deleteImageById(image.getId());
            image = imageMapper.getImageByName(f2.getName());
            Assert.assertNull(image);
        }
        image = imageMapper.getImageByName(f3.getName());
        if (image != null) {
            imageService.deleteImageById(image.getId());
            image = imageMapper.getImageByName(f3.getName());
            Assert.assertNull(image);
        }
    }
}