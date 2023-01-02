package cn.fishland.bookmanager.bean.vo;

import lombok.Data;

import java.util.Date;

/**
 * ajax通用返回结果
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public class ReturnData {

    public ReturnData(String message, Object data, String to) {
        this("200", 0, message, data, to);
    }

    public ReturnData(String message) {
        this("200", 0, message, null, "");
    }

    public ReturnData(String message, String to) {
        this("200", 0, message, null, to);
    }

    public ReturnData(String code, Integer error, String message, String to) {
        this(code, error, message, null, to);
    }

    public ReturnData(Object data, String to) {
        this("200", 0, "", data, to);
    }

    public ReturnData(String code, Integer error, String message, Object data, String to) {
        this.code = code;
        this.error = error;
        this.message = message;
        this.data = data;
        this.to = to;
        this.time = new Date();
    }

    private String code;
    private Integer error;
    private String message;
    private Object data;
    private String to;
    private Date time;

}
