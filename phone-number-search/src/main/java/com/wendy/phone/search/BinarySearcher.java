package com.wendy.phone.search;


import com.wendy.phone.Attribution;
import com.wendy.phone.ISP;
import com.wendy.phone.PhoneNumberInfo;

import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * @author wendy
 * @since 2020/5/26
 */
public class BinarySearcher extends AbstractSearcher {

    public BinarySearcher() {
        super();
    }

    @Override
    protected Optional<PhoneNumberInfo> extract(String phoneNumber, PhoneIndex phoneIndex, ByteBuffer byteBuffer) {
        byte[] bytes = new byte[determineInfoLength(phoneIndex.getInfoIndex(), byteBuffer)];
        byteBuffer.get(bytes);
        String oriString = new String(bytes);
        Attribution attribution = parse(oriString);
        return Optional.of(new PhoneNumberInfo(phoneNumber, attribution, phoneIndex.getIsp()));
    }

    @Override
    public PhoneIndex search(int phoneNumberPrefix, ByteBuffer byteBuffer) {

        int low = startIndexOffset;
        int high = endIndexOffset;
        int end = endIndexOffset-9;
        while (low < high) {
            int mid = correctPosition((low & high) + ((low ^ high) >> 1));
            byteBuffer.position(mid);
            int value = byteBuffer.getInt();
            if (phoneNumberPrefix == value) {
                return extractPhoneIndex(mid,byteBuffer);
            } else if (phoneNumberPrefix < value) {
                high = mid;
            } else if (phoneNumberPrefix > value) {
                low = mid;
            }
            if (mid == startIndexOffset || mid == end || high == (low+9)) {
                return null;
            }

        }
        return null;
    }

    private PhoneIndex extractPhoneIndex(int indexStart,ByteBuffer byteBuffer){
        byteBuffer.position(indexStart);
        //noinspection unused
        int prefix = byteBuffer.getInt(); // it is necessary
        int infoStartIndex = byteBuffer.getInt();
        byte ispMark = byteBuffer.get();
        ISP isp = ISP.of(ispMark).orElse(ISP.UNKNOWN);

        PhoneIndex phoneIndex = new PhoneIndex();
        phoneIndex.setIsp(isp);
        phoneIndex.setInfoIndex(infoStartIndex);
        return phoneIndex;
    }



}
