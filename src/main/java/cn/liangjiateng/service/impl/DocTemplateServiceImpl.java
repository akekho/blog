package cn.liangjiateng.service.impl;

import cn.liangjiateng.common.ErrorCode;
import cn.liangjiateng.common.ServiceException;
import cn.liangjiateng.config.Config;
import cn.liangjiateng.mapper.DocTemplateMapper;
import cn.liangjiateng.pojo.DO.DocTemplate;
import cn.liangjiateng.service.DocTemplateService;
import cn.liangjiateng.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DocTemplateServiceImpl implements DocTemplateService {

    @Autowired
    private DocTemplateMapper docTemplateMapper;
    @Autowired
    private Config config;

    @Override
    public long countDocByStatus(DocTemplate.Status status) {
        return docTemplateMapper.countDocByStatus(status.getVal());
    }

    @Override
    public Page<DocTemplate> listDocsSortBy(DocTemplate.SortType sortType, int pageSize, int page) throws ServiceException {
        if (page <= 0 || pageSize <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long cnt = countDocByStatus(DocTemplate.Status.ONLINE);
        Page<DocTemplate> pageHolder = new Page<>(page, pageSize, cnt, null);
        List<DocTemplate> list = docTemplateMapper.listDocsSortBy(sortType.getVal(), DocTemplate.Status.ONLINE.getVal(), pageHolder);
        pageHolder.setData(list);
        return pageHolder;
    }

    @Override
    public List<DocTemplate> listHottestDocs(int pageSize) throws ServiceException {
        if (pageSize <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long cnt = countDocByStatus(DocTemplate.Status.ONLINE);
        Page<DocTemplate> pageHolder = new Page<>(1, pageSize, cnt, null);
        return docTemplateMapper.listDocsSortBy(DocTemplate.SortType.USE_TIME.getVal(), DocTemplate.Status.ONLINE.getVal(), pageHolder);
    }

    @Override
    public Page<DocTemplate> listDrafts(DocTemplate.SortType sortType, int pageSize, int page) throws ServiceException {
        if (page <= 0 || pageSize <= 0)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        long cnt = countDocByStatus(DocTemplate.Status.OFFLINE);
        Page<DocTemplate> pageHolder = new Page<>(page, pageSize, cnt, null);
        List<DocTemplate> list = docTemplateMapper.listDocsSortBy(sortType.getVal(), DocTemplate.Status.OFFLINE.getVal(), pageHolder);
        pageHolder.setData(list);
        return pageHolder;
    }

    @Override
    public DocTemplate getDocById(int id) throws ServiceException {
        DocTemplate docTemplate = docTemplateMapper.getDocById(id);
        if (docTemplate == null)
            throw new ServiceException(ErrorCode.FAIL.getCode(), "模板不存在");
        return docTemplate;
    }

    @Override
    public void updateDoc(DocTemplate docTemplate) throws ServiceException {
        if (docTemplate == null || docTemplate.getId() == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        getDocById(docTemplate.getId());
        docTemplateMapper.updateDoc(docTemplate);
    }

    @Override
    public void postDocById(int id) throws ServiceException {
        DocTemplate docTemplate = getDocById(id);
        docTemplate.setStatus(DocTemplate.Status.ONLINE.getVal());
        docTemplateMapper.updateDoc(docTemplate);
    }

    @Override
    public void offlineDocById(int id) throws ServiceException {
        DocTemplate docTemplate = getDocById(id);
        docTemplate.setStatus(DocTemplate.Status.OFFLINE.getVal());
        docTemplateMapper.updateDoc(docTemplate);
    }

    @Override
    public void addUseTimeById(int id, int cnt) throws ServiceException {
        DocTemplate docTemplate = getDocById(id);
        docTemplate.setUseTime(docTemplate.getUseTime() + cnt);
        docTemplateMapper.updateDoc(docTemplate);
    }

    @Override
    public void deleteDocById(int id) throws ServiceException {
        DocTemplate docTemplate = getDocById(id);
        docTemplate.setStatus(DocTemplate.Status.DELETED.getVal());
        docTemplateMapper.updateDoc(docTemplate);
    }

    @Override
    public void createNewDoc(DocTemplate docTemplate) throws ServiceException {
        if (docTemplate == null)
            throw new ServiceException(ErrorCode.PARAM_ERR.getCode(), ErrorCode.PARAM_ERR.getMsg());
        docTemplateMapper.insertDoc(docTemplate);
    }
}
