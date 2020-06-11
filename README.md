![数据文件](./data.png)

```

         PhoneNumberSearcher phoneNumberSearcher = new PhoneNumberSearcher();
         Optional<PhoneNumberInfo> lookup = phoneNumberSearcher.lookup("15951590000");
         if (lookup.isPresent()){
             System.out.println(lookup);
         }
         // Optional[PhoneNumberInfo{phoneNumber='15951590000', attribution=Attribution{province='江苏', city='宿迁', zipCode='223800', areaCode='0527'}, isp=ISP{cnName='中国移动', value=1}}]

       
```
![手机号数据展示](./phonedata.png)

## 你们想要的数据
[手机号归属地数据文件](./手机号归属地.xlsx)

## 使用

1. 使用现有的dat文件做搜索
2. 使用提供的数据文件自己生成dat格式
3. 数据来源于互联网
