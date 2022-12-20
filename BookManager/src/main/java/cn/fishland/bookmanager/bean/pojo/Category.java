package cn.fishland.bookmanager.bean.pojo;

import cn.fishland.bookmanager.bean.BaseBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 书籍类别
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseBean {

    private static final long serialVersionUID = -1276274461848466050L;

    public String name;

}
