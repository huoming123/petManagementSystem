package com.design.petStoreManagement.pojo;

import java.util.Date;
import java.io.Serializable;

/**
 * (Manager)实体类
 *
 * @author makejava
 *
 */
public class Manager implements Serializable {
    private static final long serialVersionUID = -37207777189856384L;
     /**
     * 主键id
     */
    private Integer id;
     /**
     * 手机号码11位
     */
    private String telephone;
     /**
     * 姓名
     */
    private String userName;
     /**
     * 管理员编号
     */
    private String userNo;
     /**
     * 密码
     */
    private String password;
     /**
     * 住址
     */
    private String address;
     /**
     * 录入时间
     */
    private Date createdAt;
     /**
     * 录入人
     */
    private String createdBy;
     /**
     * 照片
     */
    private String images;
    private String imagesUrl;

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

}

