package cn.fishland.bookmanager.bean.pojo;

import cn.fishland.bookmanager.bean.BaseBean;

/**
 * 文件或图片附件对象
 *
 * @author xiaoyu
 * @version 1.0
 */
public abstract class Attachment extends BaseBean {
    private static final long serialVersionUID = 4648141158101899732L;

    /** 附件类别重新存储名称 */
    protected String aid;
    /** 附件类别：0：文件，1：图片 */
    protected Integer type;
    /** 附件原名称 */
    protected String name;
    /** 大小 */
    protected Double size;
    /** 大小单位 */
    protected String sizeUnit;
    /** 格式 */
    protected String extension;
    /** 附件动态id，防止盗链 */
    protected String dynamicId;
    /** 文件物理地址 */
    protected String filePath;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
