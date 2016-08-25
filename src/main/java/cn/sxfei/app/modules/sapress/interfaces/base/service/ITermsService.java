package cn.sxfei.app.modules.sapress.interfaces.base.service;

import java.util.List;

import cn.sxfei.app.core.base.IBaseService;
import cn.sxfei.app.core.base.exception.BusinessException;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Terms;

public interface ITermsService extends IBaseService<Terms, Long> {
    public List<Terms> getAllTerm() throws BusinessException;

}
