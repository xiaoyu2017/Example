package cn.fishland.bookportal.bean.pojo;

import cn.fishland.bookportal.bean.BaseBean;
import com.alibaba.fastjson2.JSON;

/**
 * ebook和category中间表
 *
 * @author xiaoyu
 * @version 1.0
 */
public class EbookCategory extends BaseBean {
    private static final long serialVersionUID = 4269017472368921927L;

    private Long eid;
    private Long cid;

    public EbookCategory() {
    }

    public EbookCategory(Long eid, Long cid) {
        this.eid = eid;
        this.cid = cid;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Override
    protected String toJson() {
        return JSON.toJSONString(this);
    }
}
