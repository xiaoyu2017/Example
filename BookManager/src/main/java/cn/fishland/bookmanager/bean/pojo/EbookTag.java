package cn.fishland.bookmanager.bean.pojo;

import cn.fishland.bookmanager.bean.BaseBean;
import com.alibaba.fastjson2.JSON;

/**
 * ebook和tag中间表
 *
 * @author xiaoyu
 * @version 1.0
 */
public class EbookTag extends BaseBean {
    private static final long serialVersionUID = -9094255401081140194L;

    private Long eid;
    private Long tid;
    private Integer type;
    private Tag tag;

    public EbookTag() {
    }

    public EbookTag(Long eid, Long tid, Integer type) {
        this.eid = eid;
        this.tid = tid;
        this.type = type;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    protected String toJson() {
        return JSON.toJSONString(this);
    }
}
