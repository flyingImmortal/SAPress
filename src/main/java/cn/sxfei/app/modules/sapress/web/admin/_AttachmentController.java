package cn.sxfei.app.modules.sapress.web.admin;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.sxfei.app.core.spring.PropertiesHelper;
import cn.sxfei.app.modules.sapress.comm.SapressController;
import cn.sxfei.app.modules.sapress.comm.util.FileUtils;
import cn.sxfei.app.modules.sapress.interfaces.base.constants.SaConstant;
import cn.sxfei.app.modules.sapress.services.admin.po.PostMetaPO;
import cn.sxfei.app.modules.sapress.web.admin.dto.Archive;
import cn.sxfei.app.modules.sapress.web.admin.dto.Attachment;
import cn.sxfei.app.plugins.jfinal.activerecord.Page;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 附件管理
 * 
 * @author songxianfei@gmail.com
 * @time 2016-08-06
 */
@Controller
@RequestMapping("/admin/attachment")
// @RouterMapping(url = "/admin/attachment", viewPath =
// "/WEB-INF/admin/attachment")
public class _AttachmentController extends SapressController {
    private static final Logger log = LoggerFactory.getLogger(_AttachmentController.class);

    @RequestMapping({ "/index", "/", "" })
    public String index() {
        List<Attachment> attachmentList = Lists.newArrayList();
        List<PostMetaPO> postMetaList = PostMetaPO.dao.findByWhereAndColumns(
                "where meta_key= ? group by meta_value order by meta_id limit 0,18", "max(meta_id) meta_id, meta_value", SaConstant.POST_META_THUMBNAIL);
        for (PostMetaPO po : postMetaList) {
            Attachment attachment = new Attachment();
            attachment.setPath(po.getMetaValue());
            attachment.setSuffix(po.getMetaValue());
            attachment.setImage(true);
            attachment.setId(po.getMetaId());
            attachmentList.add(attachment);
        }
        Page<Attachment> page = new Page<Attachment>();
        page.setList(attachmentList);
        setAttr("page", page);

        List<Archive> archives = Lists.newArrayList();
        setAttr("archives", archives);

        return "admin/attachment/index";
    }

    @RequestMapping(value = "/doUpload")
    @ResponseBody
    public Object doUpload(@RequestParam("qqfile")
    MultipartFile file, HttpServletRequest request) {
        Map<String, Object> result = Maps.newHashMap();
        // ####图片格式校验####
        if (null == file) {
            result.put("success", false);
        }
        if (!file.getOriginalFilename().toLowerCase().matches("^.*?\\.(png|bmp|jpg|gif|jpeg)$")) {
            result.put("success", false);
        }
        if (file.getSize() == 0 || file.getSize() > (5 * 1024 * 1024)) {
            result.put("success", false);
        }
        // ####图片格式校验####
        // new一个文件对象用来保存图片，默认保存当前工程根目录
        String[] fileAtr = FileUtils.saveFile(file);
        String fileName = fileAtr[0];
        boolean flag = Boolean.parseBoolean(fileAtr[2]);
        if (flag) {
            JSONObject json = new JSONObject();
            json.put("success", true);
            json.put("src", PropertiesHelper.getProperty(SaConstant.QN_URL) + fileName);
            return json;
        }
        result.put("success", false);
        return result;

    }

    @RequestMapping("/choose_layer")
    public String choose_layer() {
        index();
        return ("admin/attachment/choose_layer");
    }
    @RequestMapping("/detail_layer")
    public String detail_layer() {
        BigInteger id = getParaToBigInteger("id");
        PostMetaPO po = PostMetaPO.dao.findById(id);
        Attachment attachment = new Attachment();
        attachment.setPath(po.getMetaValue());
        attachment.setSuffix(po.getMetaValue());
        attachment.setImage(true);
        attachment.setId(po.getMetaId());
        setAttr("attachment", attachment);
        setAttr("attachmentName", "");
        setAttr("attachmentSize", "");
        return "admin/attachment/detail_layer";
    }
}