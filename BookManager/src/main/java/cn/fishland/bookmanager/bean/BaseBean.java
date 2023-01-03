package cn.fishland.bookmanager.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有Bean的基类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public abstract class BaseBean implements Serializable {

    private static final long serialVersionUID = -2282893766014331881L;

    public Long id;

    public Date createTime;

    public Date updateTime;

    public Boolean status = true;

    public Integer sort = 0;
}
