![数据文件](./data.png)

```

         PhoneNumberSearcher phoneNumberSearcher = new PhoneNumberSearcher();
         Optional<PhoneNumberInfo> lookup = phoneNumberSearcher.lookup("15951590000");
         if (lookup.isPresent()){
             System.out.println(lookup);
         }
         // Optional[PhoneNumberInfo{phoneNumber='15951590000', attribution=Attribution{province='江苏', city='宿迁', zipCode='223800', areaCode='0527'}, isp=ISP{cnName='中国移动', value=1}}]

       
```