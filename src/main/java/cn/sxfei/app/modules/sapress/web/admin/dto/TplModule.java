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
package cn.sxfei.app.modules.sapress.web.admin.dto;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class TplModule {

    private String title;
    private String name;
    private String listTitle;
    private String addTitle;
    private String commentTitle;
    private List<TplTaxonomyType> taxonomyTypes;
    private List<TplMetadata> metadatas;

    public List<TplTaxonomyType> getTaxonomyTypes() {
        return taxonomyTypes;
    }

    public void setTaxonomyTypes(List<TplTaxonomyType> taxonomys) {
        this.taxonomyTypes = taxonomys;
    }

    public List<TplMetadata> getMetadatas() {
        return metadatas;
    }

    public void setMetadatas(List<TplMetadata> metadatas) {
        this.metadatas = metadatas;
    }

    public List<String> getStyles() {
        List<String> moduleStyles = null;
        File f = new File("");//PathKit.getWebRootPath(), TemplateUtils.getTemplatePath());
        String[] fileNames = f.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String fileName) {
                return fileName.startsWith("content_" + name + "_");
            }
        });
        if (fileNames != null && fileNames.length > 0) {
            moduleStyles = new ArrayList<String>();
            int start = ("content_" + name + "_").length();
            for (String fileName : fileNames) {
                moduleStyles.add(fileName.substring(start, fileName.lastIndexOf(".")));
            }
        }
        return moduleStyles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getAddTitle() {
        return addTitle;
    }

    public void setAddTitle(String addTitle) {
        this.addTitle = addTitle;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TplTaxonomyType getTaxonomyTypeByType(String name) {
        List<TplTaxonomyType> tts = taxonomyTypes;
        if (null != tts && tts.size() > 0) {
            for (TplTaxonomyType type : tts) {
                if (type.getName().equals(name))
                    return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Module [title=" + title + ", name=" + name + ", listTitle=" + listTitle + ", addTitle=" + addTitle + ", commentTitle="
                + commentTitle + ", taxonomyTypes=" + taxonomyTypes + "]";
    }

}
