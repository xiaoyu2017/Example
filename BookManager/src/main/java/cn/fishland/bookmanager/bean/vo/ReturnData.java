package cn.fishland.bookmanager.bean.vo;

import java.util.Date;
import java.util.Objects;

/**
 * ajax通用返回结果
 *
 * @author xiaoyu
 * @version 1.0
 */
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnData that = (ReturnData) o;
        return Objects.equals(code, that.code) && Objects.equals(error, that.error) && Objects.equals(message, that.message) && Objects.equals(data, that.data) && Objects.equals(to, that.to) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, error, message, data, to, time);
    }

    @Override
    public String toString() {
        return "ReturnData{" +
                "code='" + code + '\'' +
                ", error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", to='" + to + '\'' +
                ", time=" + time +
                '}';
    }

}
