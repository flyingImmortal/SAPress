/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 *
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.sxfei.app.modules.sapress.web.admin;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.sxfei.app.core.base.constant.Constants;
import cn.sxfei.app.core.base.dto.QueryDTO;
import cn.sxfei.app.modules.sapress.comm.AjaxResult;
import cn.sxfei.app.modules.sapress.comm.PressServiceHelper;
import cn.sxfei.app.modules.sapress.comm.SapressController;
import cn.sxfei.app.modules.sapress.comm.util.FileUtils;
import cn.sxfei.app.modules.sapress.interfaces.base.dto.UserDTO;
import cn.sxfei.app.modules.sapress.services.base.po.UserMetaPO;
import cn.sxfei.app.modules.sapress.services.base.po.UserPO;
import cn.sxfei.app.modules.sapress.web.admin.dto.User;
import cn.sxfei.app.plugins.jfinal.activerecord.Page;

import com.google.common.collect.Lists;

/**
 * 用户管理
 * 
 * @author songxianfei@gmail.com
 * @time 2016-08-14
 */
@Controller
@RequestMapping("/admin/user")
// @RouterMapping(url = "/admin/user", viewPath = "/WEB-INF/admin/user")
public class _UserController extends SapressController {

    @RequestMapping({ "/index", "/", "" })
    public String index() {
        UserPO userPO = UserPO.dao.findFirstByWhereAndColumns(" ", "count(1) id");
        UserMetaPO userMetaPO = UserMetaPO.dao.findFirstByWhereAndColumns(" where meta_key = '" + Constants.TABLE_PREFIX
                + "capabilities' AND CAST(meta_value AS CHAR) LIKE '%\"administrator\"%' ", "count(1) umeta_id ");
        setAttr("userCount", userPO.getId());
        setAttr("adminCount", userMetaPO.getUmetaId());
        QueryDTO queryDto = new QueryDTO();
        Page<User> result = new Page<User>();
        List<UserPO> userList = UserPO.dao.findByWhere("order by id limit " + queryDto.getPageNo() * queryDto.getPageSize() + ","
                + queryDto.getPageSize());
        List<User> resultList = Lists.newArrayList();
        for (UserPO po : userList) {
            User user = new User();
            user.setId(po.getId());
            user.setNickname(po.getUserNicename());
            user.setEmail(po.getUserEmail());
            user.setStatus(String.valueOf(po.getUserStatus()));
            // user.setSignature(signature);
            user.setUsername(po.getDisplayName());
            resultList.add(user);
        }
        result.setTotalRow(Integer.valueOf(userPO.getId()));
        result.setPageSize(queryDto.getPageSize());
        result.setPageNumber(queryDto.getPageNo());
        result.setList(resultList);
        setAttr("page", result);
        return "admin/user/index";
    }

    @RequestMapping("/delete")
    public AjaxResult delete() {
        BigInteger id = getParaToBigInteger("id");
        if (id == null) {
            return renderAjaxResultForError();
        }
        UserDTO user = PressServiceHelper.getSessionUser(request);
        if (user.getId().compareTo(String.valueOf(id)) == 0) {
            return renderAjaxResultForError("不能删除自己...");
        }
        UserMetaPO.dao.deleteByWhere("where user_id=?", id);
        UserPO.dao.deleteById(id);
        return renderAjaxResultForSuccess();
    }

    @RequestMapping("/edit")
    public String edit() {
        BigInteger id = getParaToBigInteger("id");
        if (id != null) {
            UserPO po = UserPO.dao.findById(id);
            User user = new User();
            user.setId(po.getId());
            user.setNickname(po.getUserNicename());
            user.setEmail(po.getUserEmail());
            user.setStatus(String.valueOf(po.getUserStatus()));
            // user.setSignature(signature);
            user.setUsername(po.getDisplayName());
            setAttr("user", user);
        }
        return "admin/user/edit";
    }

    @RequestMapping("/info")
    public String info() {
        UserDTO userDto = PressServiceHelper.getSessionUser(request);
        User user = new User();
        if (userDto != null) {
            UserPO po = UserPO.dao.findById(userDto.getId());
            user.setId(po.getId());
            user.setNickname(po.getUserNicename());
            user.setEmail(po.getUserEmail());
            user.setStatus(String.valueOf(po.getUserStatus()));
            // user.setSignature(signature);
            user.setUsername(po.getDisplayName());
        }
        setAttr("user", user);
        return "admin/user/edit";
    }

    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(MultipartFile file, User user) {
        if (file != null) {
            String[] fileAtr = FileUtils.saveFile(file);
            String fileName = fileAtr[0];
            boolean flag = Boolean.parseBoolean(fileAtr[2]);
        }
        UserPO po = new UserPO();
        po.setDisplayName(user.getUsername());
        po.setUserNicename(user.getNickname());
        po.setUserEmail(user.getEmail());
        po.setUserPass(user.getPassword());
        po.setUserLogin(user.getUsername());
        po.save();
        return renderAjaxResultForSuccess("ok");
    }

}
