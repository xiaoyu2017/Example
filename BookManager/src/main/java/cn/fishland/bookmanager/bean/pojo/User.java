package cn.fishland.bookmanager.bean.pojo;

import cn.fishland.bookmanager.bean.BaseBean;
import com.alibaba.fastjson2.JSON;

import java.util.Date;
import java.util.Objects;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class User extends BaseBean {
    private static final long serialVersionUID = -1847419447948018887L;

    private String nickName;
    private String name;
    private String password;
    private String icon;
    private String email;
    private String companyName;
    private Byte sex;
    private Date birthday;
    private String code;
    private Integer role;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String nickName, String name, String password, String icon, String email, String companyName, Byte sex, Date birthday, String code, Integer role) {
        this.nickName = nickName;
        this.name = name;
        this.password = password;
        this.icon = icon;
        this.email = email;
        this.companyName = companyName;
        this.sex = sex;
        this.birthday = birthday;
        this.code = code;
        this.role = role;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", sort=" + sort +
                ", nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", icon='" + icon + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", code='" + code + '\'' +
                ", role=" + role +
                "} " + super.toString();
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this);
    }
}
