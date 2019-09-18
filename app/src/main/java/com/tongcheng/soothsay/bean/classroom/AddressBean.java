package com.tongcheng.soothsay.bean.classroom;

import java.io.Serializable;

/**
 * Created by Steven on 16/12/26.
 */

public class AddressBean implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * address : 广州
     * city : 广州
     * id : 1
     * isdefault : 0
     * name : 张三
     * phone : 13676267856
     * province : 广东
     */

    private String token;
    private String address;
    private String area;
    private String city;
    private String addressId;
    private String isdefault;
    private String name;
    private String phone;
    private String province;
    private String type; //保存类型  1：新增，2：修改

    private boolean isSelect;       //当前选中地址

    public AddressBean() {

    }

    public AddressBean( String token,String name,String phone, String province, String city, String address,String isdefault, String type,String area) {
        this.isdefault = isdefault;
        this.city = city;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.type = type;
        this.token = token;
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return addressId;
    }

    public void setId(String id) {
        this.addressId = id;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
