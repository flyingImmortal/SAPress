package cn.sxfei.app.modules.sapress.services.base;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.sxfei.app.core.aspect.ViewLog;
import cn.sxfei.app.core.base.BaseService;
import cn.sxfei.app.core.base.constant.Constants;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.core.spring.PropertiesHelper;
import cn.sxfei.app.core.utils.BeanUtils;
import cn.sxfei.app.core.utils.DateUtils;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.PostDTO;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Posts;
import cn.sxfei.app.modules.sapress.interfaces.base.entity.Terms;
import cn.sxfei.app.modules.sapress.interfaces.base.enums.PostTypeEnum;
import cn.sxfei.app.modules.sapress.interfaces.base.service.IPostsService;
import cn.sxfei.app.modules.sapress.services.admin.TermsInitService;
import cn.sxfei.app.modules.sapress.services.admin.po.TermRelationshipsPO;
import cn.sxfei.app.modules.sapress.services.base.dao.IPostsDAO;
import cn.sxfei.app.modules.sapress.services.base.dao.ITermsDAO;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 日志信息
 * 
 * @author sxfei 2016-07-10
 */
@Service
public class PostsService extends BaseService<Posts, Long> implements IPostsService {
    @Autowired
    private IPostsDAO postsDAO;
    @Autowired
    private ITermsDAO termsDAO;

    @Override
    public Posts getByPrimaryKey(Long id) {
        QueryDTO dto = new QueryDTO();
        dto.setId(id);
        return postsDAO.selectByPrimaryKey(dto);
    }

    @Override
    @ViewLog
    public List<Long> getLastPosts(QueryDTO dto) {
        return postsDAO.selectLastPosts(dto);
    }

    @Override
    @ViewLog
    public List<Long> getHotPosts() {
        QueryDTO dto = new QueryDTO();
        return postsDAO.selectHotPosts(dto);
    }

    public Long getRandomPosts() {
        QueryDTO dto = new QueryDTO();
        return postsDAO.selectRandomPosts(dto);
    }

    @Override
    public Map<Long, List<Terms>> getTermByIds(List<Long> idList) {
        QueryDTO dto = new QueryDTO();
        dto.setIdList(idList);
        List<Map> termList = termsDAO.selectTermByPostId(dto);
        Map<Long, List<Terms>> termsMap = Maps.newHashMap();
        for (Map map : termList) {
            Terms terms = new Terms();
            BeanUtils.mapToObject(terms, map);
            Long objectId = Long.valueOf(String.valueOf(map.get("object_id")));
            List<Terms> temp = termsMap.get(objectId);
            if (null == temp) {
                temp = Lists.newArrayList();
            }
            temp.add(terms);
            termsMap.put(objectId, temp);
        }
        return termsMap;
    }

    @Override
    public List<Posts> getByIds(List<Long> idList) {
        List<Posts> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(idList)) {
            return result;
        }
        QueryDTO dto = new QueryDTO();
        dto.setIdList(idList);
        result = postsDAO.selectByIds(dto);
        return result;
    }

    @Override
    public List<PostDTO> getResultByIds(List<Long> idList) {
        List<Posts> postList = getByIds(idList);
        Map<Long, List<Terms>> termsMap = getTermByIds(idList);
        return getPostDto(postList, termsMap);
    }

    private List<PostDTO> getPostDto(List<Posts> postList, Map<Long, List<Terms>> termsMap) {
        List<PostDTO> resultList = Lists.newArrayList();
        for (Posts post : postList) {
            PostDTO postDTO = new PostDTO();
            postDTO.setMonth(DateUtils.format(post.getPostDate(), DateUtils.MM));
            postDTO.setDay(DateUtils.format(post.getPostDate(), DateUtils.DD));
            postDTO.setMonthDay(DateUtils.format(post.getPostDate(), DateUtils.MMDD));
            postDTO.setAuthor(PropertiesHelper.getProperty(Constants.USER_KEY + post.getPostAuthor()));
            Long id = post.getId();
            postDTO.setCategory(termsMap.get(id));
            postDTO.setComment(String.valueOf(post.getCommentCount()));
            postDTO.setContentArticle(post.getPostContent());
            postDTO.setTitle(post.getPostTitle());
            postDTO.setId(String.valueOf(post.getId()));
            postDTO.setOriginalUrl(post.getGuid());
            resultList.add(0, postDTO);
        }
        return resultList;
    }

    public List<PostDTO> getNextById(Long id) {
        QueryDTO dto = new QueryDTO();
        dto.setId(id);
        Long tempId = postsDAO.selectNextPost(dto);
        if (null != tempId) {
            id = tempId;
        }
        List<Long> idList = Lists.newArrayList();
        idList.add(id);
        List<Posts> postList = getByIds(idList);
        Map<Long, List<Terms>> termsMap = getTermByIds(idList);
        return getPostDto(postList, termsMap);
    }

    public List<PostDTO> getPrevById(Long id) {
        QueryDTO dto = new QueryDTO();
        dto.setId(id);
        Long tempId = postsDAO.selectPrevPost(dto);
        if (null != tempId) {
            id = tempId;
        }
        List<Long> idList = Lists.newArrayList();
        idList.add(id);
        List<Posts> postList = getByIds(idList);
        Map<Long, List<Terms>> termsMap = getTermByIds(idList);
        return getPostDto(postList, termsMap);
    }

    public List<PostDTO> getRelatePosts(Long id) {
        List<Long> idList = Lists.newArrayList();
        int i = 0;
        List<TermRelationshipsPO> relationshipsList = TermsInitService.termRelationshipsTermsObjectId.get(id
                + PostTypeEnum.CATEGORY.getName());
        List<PostDTO> resultList = Lists.newArrayList();
        if (null == relationshipsList) {
            return resultList;
        }
        for (TermRelationshipsPO po : relationshipsList) {
            List<String> postList = TermsInitService.relationshipsMap.get(po.getTermTaxonomyId());
            if (postList != null) {
                for (String postId : postList) {
                    if (!postId.equals(String.valueOf(id))) {
                        idList.add(Long.valueOf(postId));
                        i++;
                    }
                    if (i == 3) {
                        break;
                    }
                }
            }
            if (i == 3) {
                break;
            }
        }
        if (!CollectionUtils.isEmpty(idList)) {
            List<Posts> postList = getByIds(idList);
            Map<Long, List<Terms>> termsMap = getTermByIds(idList);
            return getPostDto(postList, termsMap);
        }
        return resultList;

    }
}
