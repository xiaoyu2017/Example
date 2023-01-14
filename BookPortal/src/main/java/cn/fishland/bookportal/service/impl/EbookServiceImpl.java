package cn.fishland.bookportal.service.impl;

import cn.fishland.bookportal.bean.pojo.Ebook;
import cn.fishland.bookportal.bean.pojo.ImageAttachment;
import cn.fishland.bookportal.bean.vo.EbookVo;
import cn.fishland.bookportal.dao.EbookDao;
import cn.fishland.bookportal.service.EbookService;
import cn.fishland.bookportal.tool.PortalTool;

import java.util.List;
import java.util.Map;

/**
 * 电子书服务类查询类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class EbookServiceImpl implements EbookService {

    @Override
    public List<EbookVo> searchAll(String search) {
        if (search == null || search.trim().length() <= 0) {
            System.out.println("search str is empty");
            return null;
        }

        EbookDao ebookDao = new EbookDao();
        Map<Long, Ebook> ebookMap = ebookDao.selectAll(search);

        return PortalTool.ebook2EbookVo(ebookMap);
    }

    @Override
    public EbookVo findById(long eid) {
        EbookDao ebookDao = new EbookDao();
        return ebookDao.selectById(eid);
    }

    @Override
    public ImageAttachment findAttachmentById(String imgId) {
        EbookDao ebookDao = new EbookDao();
        return ebookDao.selectAttachmentById(imgId);
    }
}
