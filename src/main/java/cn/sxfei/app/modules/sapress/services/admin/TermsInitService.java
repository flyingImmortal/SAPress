package cn.sxfei.app.modules.sapress.services.admin;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.sxfei.app.core.base.IInitialiseService;
import cn.sxfei.app.modules.sapress.services.admin.po.TermRelationshipsPO;
import cn.sxfei.app.modules.sapress.services.admin.po.TermTaxonomyPO;
import cn.sxfei.app.modules.sapress.services.admin.po.TermsPO;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 分类信息初始化
 * 
 * @author sxf
 * 
 */
public class TermsInitService implements IInitialiseService {

    public static Map<String, TermsPO> mapTerms = Maps.newHashMap();
    public static Map<Long, TermTaxonomyPO> termTaxonomyTerms = Maps.newHashMap();
    public static Map<String, TermRelationshipsPO> termRelationshipsTerms = Maps.newHashMap();
    //taxoid为key,postid为值
    public static Map<Long, List<String>> relationshipsMap = Maps.newHashMap();
    public static Map<String, List<TermRelationshipsPO>> termRelationshipsTermsObjectId = Maps.newHashMap();
    public static Logger log = LoggerFactory.getLogger(TermsInitService.class);

    public void init() {
        List<TermsPO> termsList = TermsPO.dao.findAll();
        for (TermsPO po : termsList) {
            mapTerms.put(po.getName(), po);
        }
        List<TermTaxonomyPO> termTaxonomyList = TermTaxonomyPO.dao.findAll();
        for (TermTaxonomyPO po : termTaxonomyList) {
            termTaxonomyTerms.put(po.getTermId(), po);
        }
        List<TermRelationshipsPO> termRelationshipsList = TermRelationshipsPO.dao.findAll();
        for (TermRelationshipsPO po : termRelationshipsList) {
            termRelationshipsTerms.put(po.getTermTaxonomyId() + "_" + po.getObjectId(), po);
            List<String> postList=relationshipsMap.get(po.getTermTaxonomyId());
            if(null==postList){
                postList=Lists.newArrayList();
            }
            postList.add(po.getObjectId());
            relationshipsMap.put(po.getTermTaxonomyId(),postList);
            TermTaxonomyPO taxoPo = termTaxonomyTerms.get(po.getTermTaxonomyId());
            if (null != taxoPo) {
                //postid+category或者post_tag
                List<TermRelationshipsPO> tempList = termRelationshipsTermsObjectId.get(po.getObjectId() + taxoPo.getTaxonomy());
                if (null == tempList) {
                    tempList = Lists.newArrayList();
                }
                tempList.add(po);
                termRelationshipsTermsObjectId.put(po.getObjectId() + taxoPo.getTaxonomy(), tempList);
            }
        }
    }

    public static void reInit() {
        mapTerms.clear();
        termTaxonomyTerms.clear();
        termRelationshipsTerms.clear();
        new TermsInitService().init();
    }
}