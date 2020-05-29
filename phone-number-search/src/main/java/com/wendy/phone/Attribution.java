package com.wendy.phone;


import java.io.Serializable;

/**
 * @author wendy
 * @since 2020/5/26
 */
public class Attribution implements Serializable {
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 邮政编码
     */
    private String zipCode;
    /**
     * 区号
     */
    private String areaCode;

    public Attribution(Builder builder) {
        this.province = builder.province;
        this.areaCode = builder.areaCode;
        this.zipCode = builder.zipCode;
        this.city = builder.city;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String province;
        private String city;
        private String zipCode;
        private String areaCode;

        public Builder province(String province) {
            this.province = province;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder areaCode(String areaCode) {
            this.areaCode = areaCode;
            return this;
        }

        public Attribution build() {
            return new Attribution(this);
        }

    }


    @Override
    public String toString() {
        return "Attribution{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
