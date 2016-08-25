package cn.sxfei.app.modules.sapress.services.admin;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import cn.sxfei.app.core.base.IInitialiseService;
import cn.sxfei.app.core.utils.StringUtil;
import cn.sxfei.app.modules.sapress.web.admin.dto.MenuGroup;
import cn.sxfei.app.modules.sapress.web.admin.dto.MenuItem;
import cn.sxfei.app.modules.sapress.web.admin.dto.TplMetadata;
import cn.sxfei.app.modules.sapress.web.admin.dto.TplModule;
import cn.sxfei.app.modules.sapress.web.admin.dto.TplTaxonomyType;
import cn.sxfei.app.plugins.sqlplugin.PathKit;

public class MenuInitService implements IInitialiseService {

    public static final LinkedList<MenuGroup> menuGroups = new LinkedList<MenuGroup>();
    public static List<TplModule> moduleList = new LinkedList<TplModule>();
    public static Logger log = LoggerFactory.getLogger(TermsInitService.class);

    public static TplModule getModule(String moduleName) {
        if (StringUtil.isBlank(moduleName)) {
            return null;
        }
        for (TplModule tplModule : moduleList) {
            if (moduleName.equals(tplModule.getName())) {
                return tplModule;
            }
        }
        return null;
    }

    public void init() {
        String rootPath = getRootClassPath();
        log.error("设置当前项目根路径：" + rootPath);
        PathKit.setWebRootPath(rootPath);
        menuGroups.addAll(moduleMenuGroup());
        menuGroups.add(createAttachmentMenuGroup());
        menuGroups.add(MenuGroup.createBlockGroup());
        menuGroups.add(createUserMenuGroup());
        menuGroups.add(createSettingMenuGroup());
        
        menuGroups.add(createToolsMenuGroup());
        menuGroups.add(createTemplateMenuGroup());
        menuGroups.add(createWechatMenuGroup());
        menuGroups.add(createAddonMenuGroup());
        
    }

    public List<MenuGroup> moduleMenuGroup() {
        List<MenuGroup> menuGroupList = Lists.newArrayList();
        TplModule module = new TplModule();
        module.setName("post");
        module.setTitle("文章");
        module.setListTitle("所有文章");
        module.setAddTitle("撰写文章");
        module.setCommentTitle("评论");
        moduleList.add(module);

        TplModule module1 = new TplModule();
        module1.setName("page");
        module1.setTitle("页面");
        module1.setListTitle("所有页面");
        module1.setAddTitle("新建页面");
        module1.setCommentTitle("评论");
        List<TplMetadata> metaData = Lists.newArrayList();
        module1.setMetadatas(metaData);
        moduleList.add(module1);
        List<TplTaxonomyType> taxonomys = Lists.newArrayList();
        TplTaxonomyType taxo = new TplTaxonomyType();
        taxo.setTitle("分类");
        taxo.setName("category");
        taxo.setFormType("select");
        taxo.setModule(module);
        taxonomys.add(taxo);
        // TplTaxonomyType taxo1 = new TplTaxonomyType();
        // taxo1.setTitle("专题");
        // taxo1.setName("feature");
        // taxo1.setFormType("select");
        // taxonomys.add(taxo1);
        TplTaxonomyType taxo2 = new TplTaxonomyType();
        taxo2.setTitle("标签");
        taxo2.setName("post_tag");
        taxo2.setFormType("input");
        taxonomys.add(taxo2);
        module.setTaxonomyTypes(taxonomys);
        List<TplMetadata> metadatas = Lists.newArrayList();
        TplMetadata metadata = new TplMetadata();
        metadata.setDataType("metadata");
        metadata.setName("_meta1");
        metadata.setText("元数据1");
        metadata.setPlaceholder("元数据测试1");
        metadatas.add(metadata);
        module.setMetadatas(metadatas);

        for (TplModule tplModule : moduleList) {
            MenuGroup group = new MenuGroup(tplModule.getName(), "fa fa-file-text-o", tplModule.getTitle());
            group.addMenuItem(new MenuItem("list", "/admin/content?m=" + tplModule.getName(), tplModule.getListTitle()));
            group.addMenuItem(new MenuItem("edit", "/admin/content/edit?m=" + tplModule.getName(), tplModule.getAddTitle()));
            List<TplTaxonomyType> types = tplModule.getTaxonomyTypes();
            if (types != null && !types.isEmpty()) {
                for (TplTaxonomyType type : types) {
                    group.addMenuItem(new MenuItem(type.getName(), "/admin/taxonomy?m=" + tplModule.getName() + "&t=" + type.getName(),
                            type.getTitle()));
                }
            }
            group.addMenuItem(new MenuItem("comment", "/admin/comment?t=comment&m=" + tplModule.getName(), tplModule.getCommentTitle()));
            menuGroupList.add(group);
        }
        return menuGroupList;
    }

    private MenuGroup createWechatMenuGroup() {
        MenuGroup group = new MenuGroup("wechat", "fa fa-weixin", "微信");

        {
            group.addMenuItem(new MenuItem("r", "/admin/wechat", "自动回复"));
            group.addMenuItem(new MenuItem("rd", "/admin/wechat/reply_default", "默认回复"));
            group.addMenuItem(new MenuItem("menu", "/admin/wechat/menu", "菜单设置"));
            group.addMenuItem(new MenuItem("option", "/admin/wechat/option", "微信设置"));
        }
        return group;
    }

    private MenuGroup createAttachmentMenuGroup() {
        MenuGroup group = new MenuGroup("attachment", "fa fa-file-image-o", "附件");

        {
            group.addMenuItem(new MenuItem("list", "/admin/attachment", "所有附件"));
            //group.addMenuItem(new MenuItem("upload", "/admin/attachment/upload", "上传"));
        }
        return group;
    }

    private MenuGroup createUserMenuGroup() {
        MenuGroup group = new MenuGroup("user", "fa fa-user", "用户");

        {
            group.addMenuItem(new MenuItem("list", "/admin/user", "所有用户"));
            group.addMenuItem(new MenuItem("edit", "/admin/user/edit", "添加"));
            group.addMenuItem(new MenuItem("info", "/admin/user/info", "我的资料"));
        }
        return group;
    }

    private MenuGroup createTemplateMenuGroup() {
        MenuGroup group = new MenuGroup("template", "fa fa-magic", "模板");
        {
            group.addMenuItem(new MenuItem("list", "/admin/template", "所有主题"));
            group.addMenuItem(new MenuItem("install", "/admin/template/install", "主题安装"));
            group.addMenuItem(new MenuItem("menu", "/admin/template/menu", "菜单"));
            group.addMenuItem(new MenuItem("setting", "/admin/template/setting", "设置"));
            group.addMenuItem(new MenuItem("edit", "/admin/template/edit", "编辑"));
        }
        return group;
    }

    private MenuGroup createAddonMenuGroup() {
        MenuGroup group = new MenuGroup("addon", "fa fa-plug", "插件");
        {
            group.addMenuItem(new MenuItem("list", "/admin/addon", "所有插件"));
            group.addMenuItem(new MenuItem("install", "/admin/addon/install", "安装"));
        }
        return group;
    }

    private MenuGroup createSettingMenuGroup() {
        MenuGroup group = new MenuGroup("option", "fa fa-cog", "设置");
        {
            group.addMenuItem(new MenuItem("list", "/admin/option/web", "常规"));
            group.addMenuItem(new MenuItem("edit", "/admin/option/comment", "评论"));
            group.addMenuItem(new MenuItem("n", "/admin/option/notification", "通知"));
            group.addMenuItem(new MenuItem("seo", "/admin/option/seo", "SEO"));
            group.addMenuItem(new MenuItem("watermark", "/admin/option/watermark", "水印"));
            group.addMenuItem(new MenuItem("url", "/admin/option/url", "连接形式"));
            group.addMenuItem(new MenuItem("reg", "/admin/option/register", "登录注册"));
            group.addMenuItem(new MenuItem("cdn", "/admin/option/cdn", "CDN加速"));
            group.addMenuItem(new MenuItem("api", "/admin/api", "API应用"));
        }

        return group;
    }

    private MenuGroup createToolsMenuGroup() {
        MenuGroup group = new MenuGroup("tools", "fa fa-wrench", "工具");

        {
            group.addMenuItem(new MenuItem("import", "/admin/tools/_import", "导入"));
            group.addMenuItem(new MenuItem("export", "/admin/tools/export", "导出"));
            group.addMenuItem(new MenuItem("sync", "/admin/tools/sync", "同步"));
        }
        return group;
    }

    public static String getRootClassPath() {
        String rootClassPath = null;
        if (rootClassPath == null) {
            try {
                String path = TermsInitService.class.getClassLoader().getResource("").toURI().getPath();
                rootClassPath = new File(path).getAbsolutePath();
            } catch (Exception e) {
                String path = PathKit.class.getClassLoader().getResource("").getPath();
                rootClassPath = new File(path).getAbsolutePath();
            }
        }
        return rootClassPath;
    }
}
