package com.wendy.phone;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
*  @author wendy
*  @since 2020/5/29
*/
public abstract class AbstractTest {
    protected List<String> phoneList;
    protected Phone basePhone;
    protected Phone upBoundary;
    protected Phone downBoundary;



    public  void initData(){
        basePhone = new Phone();
        basePhone.setCity("宿迁");
        basePhone.setProvince("江苏");
        basePhone.setIsp("中国移动");
        basePhone.setPhone("15951590000");

        upBoundary = new Phone();
        upBoundary.setCity("");
        upBoundary.setProvince("");
        upBoundary.setIsp("");
        upBoundary.setPhone("20000000000");

        downBoundary = new Phone();
        downBoundary.setCity("");
        downBoundary.setProvince("");
        downBoundary.setIsp("");
        downBoundary.setPhone("10000000000");


        phoneList =  Stream.generate(() -> {
            long phoneNumber = (long) (ThreadLocalRandom.current().nextDouble(1D, 2D) * 1000_000_000_0L);
            return String.valueOf(phoneNumber);
        }).limit(2_000_000).collect(Collectors.toCollection(ArrayList::new));


    }

}
