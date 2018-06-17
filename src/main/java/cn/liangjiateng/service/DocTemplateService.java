package cn.liangjiateng.service;

import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.pojo.DO.DocTemplate;
import cn.liangjiateng.util.Page;

import java.util.List;

/**
 * 文档模板服务
 * Created by Jiateng on 6/17/18.
 */
public interface DocTemplateService {

    /**
     * 查询模板数量
     *
     * @param status 模板状态
     * @return
     */
    long countDocByStatus(DocTemplate.Status status);

    /**
     * 列出上线的所有模板
     *
     * @param sortType 排序方式 1.TIME_DESC 2.TIME_ASC 3.USE_TIME
     * @param pageSize 每页数据量大小
     * @param page     页码
     * @return
     * @throws Exception
     */
    Page<DocTemplate> listDocsSortBy(DocTemplate.SortType sortType, int pageSize, int page) throws ServiceException;

    /**
     * 列出高访问量模板，按时间从DESC排序
     *
     * @param pageSize 每页数据量大小
     * @return
     * @throws Exception
     */
    List<DocTemplate> listHottestDocs(int pageSize) throws ServiceException;

    /**
     * 列出未上线模板
     *
     * @param sortType 排序方式 1.TIME_DESC 2.TIME_ASC 3.USE_TIME
     * @param pageSize 每页数据量大小
     * @param page     页码
     * @return
     * @throws Exception
     */
    Page<DocTemplate> listDrafts(DocTemplate.SortType sortType, int pageSize, int page) throws ServiceException;



    /**
     * 获取模板
     *
     * @param id 模板id
     * @return
     * @throws Exception
     */
    DocTemplate getDocById(int id) throws ServiceException;

    /**
     * 更新模板
     *
     * @param article 模板，没设定的字段不做更新
     * @throws Exception
     */
    void updateDoc(DocTemplate article) throws ServiceException;

    /**
     * 模板上线
     *
     * @param id 模板id
     * @throws Exception
     */
    void postDocById(int id) throws ServiceException;

    /**
     * 模板下线
     *
     * @param id 模板id
     * @throws Exception
     */
    void offlineDocById(int id) throws ServiceException;

    /**
     * 给文章增加使用量 Todo: 考虑一下并发场景
     *
     * @param id  模板id
     * @param cnt 增加的访问量
     * @throws Exception
     */
    void addUseTimeById(int id, int cnt) throws ServiceException;

    /**
     * 删除模板
     *
     * @param id 模板id
     * @throws Exception
     */
    void deleteDocById(int id) throws ServiceException;

    /**
     * 创建新模板，默认没有上线
     *
     * @param docTemplate 模板
     * @throws Exception
     */
    void createNewDoc(DocTemplate docTemplate) throws ServiceException;
}
