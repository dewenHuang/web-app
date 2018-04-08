package com.huangdw.entity;

import java.util.Date;

public class TeleCardOrder {
    private Long id;

    private String orderNo;

    private String salesId;

    private String phoneNumber;

    private String custName;

    private String custId;

    private String consigneeName;

    private String consigneePhone;

    private String consigneeAddress;

    private String consigneePostalcode;

    private String provinceCode;

    private String cityCode;

    private String countyCode;

    private String provinceCodeBelong;

    private String cityCodeBelong;

    private Long packageId;

    private Integer status;

    private Integer errorCode;

    private Integer closeReason;

    private String teleOrderNo;

    private Date teleCreateTime;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId == null ? null : salesId.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone == null ? null : consigneePhone.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public String getConsigneePostalcode() {
        return consigneePostalcode;
    }

    public void setConsigneePostalcode(String consigneePostalcode) {
        this.consigneePostalcode = consigneePostalcode == null ? null : consigneePostalcode.trim();
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode == null ? null : provinceCode.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode == null ? null : countyCode.trim();
    }

    public String getProvinceCodeBelong() {
        return provinceCodeBelong;
    }

    public void setProvinceCodeBelong(String provinceCodeBelong) {
        this.provinceCodeBelong = provinceCodeBelong == null ? null : provinceCodeBelong.trim();
    }

    public String getCityCodeBelong() {
        return cityCodeBelong;
    }

    public void setCityCodeBelong(String cityCodeBelong) {
        this.cityCodeBelong = cityCodeBelong == null ? null : cityCodeBelong.trim();
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(Integer closeReason) {
        this.closeReason = closeReason;
    }

    public String getTeleOrderNo() {
        return teleOrderNo;
    }

    public void setTeleOrderNo(String teleOrderNo) {
        this.teleOrderNo = teleOrderNo == null ? null : teleOrderNo.trim();
    }

    public Date getTeleCreateTime() {
        return teleCreateTime;
    }

    public void setTeleCreateTime(Date teleCreateTime) {
        this.teleCreateTime = teleCreateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}