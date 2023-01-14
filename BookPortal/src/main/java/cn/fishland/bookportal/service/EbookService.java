package cn.fishland.bookportal.service;

import cn.fishland.bookportal.bean.vo.EbookVo;

import java.util.List;

/**
 * 电子书服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface EbookService {

    List<EbookVo> searchAll(String search);

}
