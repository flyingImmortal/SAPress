package cn.sxfei.app.modules.sapress.comm.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.NestedServletException;

import cn.sxfei.app.core.spring.PropertiesHelper;
import cn.sxfei.app.core.utils.StringUtils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * 七牛服务相关的工具类
 * 
 * @author songxianfei@gmail.com
 * @since 2016/08/09
 * @version 1.0.0
 */
public class QiNiuTools {

    private static final Logger log = LoggerFactory.getLogger(QiNiuTools.class);

    private String ak;
    private String sk;
    private String uk;
    private Long expireIn;

    private UploadManager um = new UploadManager();

    private static int uploadCnt;

    public QiNiuTools() {
        this.ak = PropertiesHelper.getProperty("ak");
        this.sk = PropertiesHelper.getProperty("sk");
    }

    /**
     * 刷新七牛UPLOAD_KEY
     */
    public void refreshUK() {
        if (StringUtils.isEmpty(ak) || StringUtils.isEmpty(sk)) {
            log.error("请设置七牛的AK和SK!!!");
            return;
        }
        synchronized (this) {
            long refreshTime = System.currentTimeMillis();
            Auth auth = Auth.create(ak, sk);
            String uk = auth.uploadToken(PropertiesHelper.getProperty("bucket"));
            this.setUk(uk);
            this.setExpireIn(refreshTime + (3600 - 60) * 1000);
        }
    }

    /**
     * 上传文件到七牛云服务
     * 
     * @param file
     *            文件
     * @param key
     *            保存名字
     * @return 成功或失败
     */
    public boolean uploadFile(File file, String key) {
        boolean flag = false;
        try {
            if(this.uk==null){
                refreshUK();
            }
            Response resp = um.put(file, key, this.uk);
            flag = resp.isOK();
            if (resp.isServerError()) {
                log.error("上传文件[%s]到七牛服务时服务器错误!!!", file.getName());
            } else {
                log.info(String.format("上传文件[%s]到七牛服务时服务器成功.", file.getName()));
            }

        } catch (QiniuException e) {
            log.error(String.format("上传文件[%s]到七牛服务时异常!!!"+file.getName()+e));
            if (uploadCnt < 5) {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e1) {
                    log.error("重新上传用户图片至七牛服务器时异常!!!");
                }
                uploadCnt++;
                uploadFile(file, key);
            }
            uploadCnt = 0;
        } catch (Exception ex) {
            log.error("保存图片出错：", ex);
            refreshUK();
            if (uploadCnt < 5) {
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e1) {
                    log.error("重新上传用户图片至七牛服务器时异常!!!");
                }
                uploadCnt++;
                uploadFile(file, key);
            }
        }
        uploadCnt = 0;
        return flag;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }

    @Override
    public String toString() {
        return "QiNiuTools{" + "ak='" + ak + '\'' + ", sk='" + sk + '\'' + ", uk='" + uk + '\'' + ", expireIn=" + expireIn + '}';
    }
}
