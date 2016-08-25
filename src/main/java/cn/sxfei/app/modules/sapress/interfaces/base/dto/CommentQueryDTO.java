package cn.sxfei.app.modules.sapress.interfaces.base.dto;

import java.util.List;

import cn.sxfei.app.core.base.dto.QueryDTO;

public class CommentQueryDTO extends QueryDTO {

    private List<String> statusList;
    private String postId;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

}
