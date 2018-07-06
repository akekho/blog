package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.mapper.DocTemplateMapper;
import cn.liangjiateng.pojo.DO.DocTemplate;
import cn.liangjiateng.util.Page;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocTemplateServiceTest {

    @Autowired
    private DocTemplateService docTemplateService;
    @Autowired
    private Config config;
    @Autowired
    private DocTemplateMapper docTemplateMapper;

    private int id;

    @Before
    public void setUp() throws Exception {
        tearDown();
        for (int i = 1; i <= 100; i++) {
            DocTemplate doc = new DocTemplate();
            doc.setId(i);
            doc.setContent("<html><p>我是你爸爸</p></html>" + i);
            doc.setImageId(i);
            doc.setUseTime(i * 1972 + i * 21);
            doc.setTitle("标题啊" + i);
            if (i % 100 == 0)
                doc.setStatus(DocTemplate.Status.DELETED.getVal());
            else if (i % 10 == 0)
                doc.setStatus(DocTemplate.Status.OFFLINE.getVal());
            else {
                doc.setStatus(DocTemplate.Status.ONLINE.getVal());
            }
            // 调整create_time
            Thread.sleep(100);
            docTemplateMapper.insertDoc(doc);
        }

        id = docTemplateMapper.getDocByName("标题啊" + 1).getId();

    }

    @After
    public void tearDown() throws Exception {
        docTemplateMapper.deleteAllDocs();
    }

    @Test
    public void countDocByStatus() {
        Assert.assertEquals(9, docTemplateService.countDocByStatus(DocTemplate.Status.OFFLINE));
    }

    @Test
    public void listDocsSortBy() throws ServiceException {
        Page<DocTemplate> page = docTemplateService.listDocsSortBy(DocTemplate.SortType.USE_TIME, config.getSmallPage(), 1);
        Assert.assertTrue(page.getData().get(0).getUseTime() >= page.getData().get(3).getUseTime());
        Assert.assertEquals(5, page.getData().size());
    }

    @Test
    public void listHottestDocs() throws ServiceException {
        List<DocTemplate> list = docTemplateService.listHottestDocs(config.getSmallPage());
        Assert.assertTrue(list.get(0).getUseTime() >= list.get(3).getUseTime());
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void listDrafts() throws ServiceException {
        Page<DocTemplate> page = docTemplateService.listDrafts(DocTemplate.SortType.TIME_ASC, config.getSmallPage(), 1);
        Assert.assertTrue(page.getData().get(0).getUseTime() <= page.getData().get(3).getUseTime());
        Assert.assertEquals((int) page.getData().get(0).getStatus(), DocTemplate.Status.OFFLINE.getVal());
        Assert.assertEquals(5, page.getData().size());
    }

    @Test
    public void getDocById() throws ServiceException {
        DocTemplate docTemplate = docTemplateService.getDocById(id);
        Assert.assertNotNull(docTemplate);
    }

    @Test
    public void updateDoc() throws ServiceException {
        DocTemplate docTemplate = docTemplateService.getDocById(id);
        docTemplate.setContent("sjsh");
        docTemplateService.updateDoc(docTemplate);
        Assert.assertEquals("sjsh", docTemplateService.getDocById(id).getContent());

    }

    @Test
    public void postDocById() throws ServiceException {
        DocTemplate docTemplate = docTemplateMapper.getDocByName("标题啊" + 10);
        Assert.assertEquals(DocTemplate.Status.OFFLINE.getVal(), (int) docTemplate.getStatus());
        docTemplateService.postDocById(docTemplate.getId());
        docTemplate = docTemplateMapper.getDocByName("标题啊" + 10);
        Assert.assertEquals(DocTemplate.Status.ONLINE.getVal(), (int) docTemplate.getStatus());
    }

    @Test
    public void offlineDocById() throws ServiceException {
        DocTemplate docTemplate = docTemplateMapper.getDocByName("标题啊" + 1);
        Assert.assertEquals(DocTemplate.Status.ONLINE.getVal(), (int) docTemplate.getStatus());
        docTemplateService.offlineDocById(docTemplate.getId());
        docTemplate = docTemplateMapper.getDocByName("标题啊" + 1);
        Assert.assertEquals(DocTemplate.Status.OFFLINE.getVal(), (int) docTemplate.getStatus());
    }

    @Test
    public void addUseTimeById() throws ServiceException {
        DocTemplate docTemplate = docTemplateMapper.getDocByName("标题啊" + 1);
        int cnt = docTemplate.getUseTime();
        docTemplateService.addUseTimeById(docTemplate.getId(), 10);
        DocTemplate docTemplate1 = docTemplateMapper.getDocByName("标题啊" + 1);
        Assert.assertEquals(cnt + 10, (int) docTemplate1.getUseTime());
    }

    @Test
    public void deleteDocById() throws ServiceException {
        DocTemplate docTemplate = docTemplateMapper.getDocByName("标题啊" + 1);
        docTemplateService.deleteDocById(docTemplate.getId());
        docTemplate = docTemplateMapper.getDocByName("标题啊" + 1);
        Assert.assertEquals((int) docTemplate.getStatus(), DocTemplate.Status.DELETED.getVal());
    }

}