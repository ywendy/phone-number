package com.wendy.phone;


import java.util.Arrays;
import java.util.Optional;

/**
 * @author wendy
 * @since  2020/5/26
 */
public enum ISP {
    CHINA_MOBILE("中国移动", 1),
    CHINA_UNICOM("中国联通", 2),
    CHINA_TELECOM("中国电信", 3),
    CHINA_TELECOM_VIRTUAL("中国电信虚拟运营商", 4),
    CHINA_UNICOM_VIRTUAL("中国联通虚拟运营商", 5),
    CHINA_MOBILE_VIRTUAL("中国移动虚拟运营商", 6),
    UNKNOWN("未知", -1);

    private final String cnName;
    private final int value;



    ISP(String cnName, int value) {
        this.cnName = cnName;
        this.value = value;
    }

    public static Optional<ISP> of(int value) {
        return Arrays.stream(values())
                .filter(v -> v.value == value)
                .findAny();
    }


    public String getCnName() {
        return cnName;
    }

    @Override
    public String toString() {
        return "ISP{" +
                "cnName='" + cnName + '\'' +
                ", value=" + value +
                '}';
    }
}
