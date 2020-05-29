package com.wendy.phone;

import java.io.Serializable;

/**
*  @author wendy
*  @since 2020/5/29
*/
public class Phone implements Serializable {


    private String phone;
    private String province;
    private String city;
    private String isp;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
