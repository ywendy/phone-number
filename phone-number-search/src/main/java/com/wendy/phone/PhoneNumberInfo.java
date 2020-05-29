package com.wendy.phone;


import java.io.Serializable;

/**
*  @author wendy
*  @since 2020/5/26
*/
public class PhoneNumberInfo implements Serializable {


    private String phoneNumber;


    private Attribution attribution;

    private ISP isp;

    public PhoneNumberInfo(String phoneNumber, Attribution attribution, ISP isp) {
        this.phoneNumber = phoneNumber;
        this.attribution = attribution;
        this.isp = isp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Attribution getAttribution() {
        return attribution;
    }

    public void setAttribution(Attribution attribution) {
        this.attribution = attribution;
    }

    public ISP getIsp() {
        return isp;
    }

    public void setIsp(ISP isp) {
        this.isp = isp;
    }


    @Override
    public String toString() {
        return "PhoneNumberInfo{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", attribution=" + attribution +
                ", isp=" + isp +
                '}';
    }
}
