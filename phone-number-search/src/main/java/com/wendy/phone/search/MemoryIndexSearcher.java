package com.wendy.phone.search;

import com.wendy.phone.Attribution;
import com.wendy.phone.ISP;
import com.wendy.phone.PhoneNumberInfo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wendy
 * @since 2020/5/28
 */
public class MemoryIndexSearcher extends AbstractSearcher {
    protected Map<String, PhoneIndex> phoneIndexMap = new ConcurrentHashMap<>();

    public MemoryIndexSearcher() {
        super();
        init();
    }

    @Override
    protected Optional<PhoneNumberInfo> extract(String phoneNumber, PhoneIndex phoneIndex, ByteBuffer byteBuffer) {
        Attribution attribution = phoneAttrMap.get(phoneIndex.getInfoIndex());
        return Optional.of(new PhoneNumberInfo(phoneNumber, attribution, phoneIndex.getIsp()));
    }


    private void init() {
        ByteBuffer byteBuffer = originalByteBuffer.asReadOnlyBuffer().order(ByteOrder.LITTLE_ENDIAN);
        int index = startIndexOffset;
        while (index < endIndexOffset) {
            byteBuffer.position(index);
            int prefix = byteBuffer.getInt();
            int infoStartIndex = byteBuffer.getInt();
            byte ispMark = byteBuffer.get();
            PhoneIndex phoneIndex = new PhoneIndex();
            phoneIndex.setInfoIndex(infoStartIndex);
            ISP isp = ISP.of(ispMark).orElse(ISP.UNKNOWN);
            phoneIndex.setIsp(isp);
            phoneIndexMap.put(String.valueOf(prefix), phoneIndex);
            byte[] bytes = new byte[determineInfoLength(phoneIndex.getInfoIndex(), byteBuffer)];
            byteBuffer.get(bytes);
            String oriString = new String(bytes);
            Attribution attribution = parse(oriString);
            phoneAttrMap.put(infoStartIndex, attribution);
            index = index + 9;
        }
        originalByteBuffer = null;
    }

    @Override
    protected PhoneIndex search(int phoneNumberPrefix, ByteBuffer byteBuffer) {
        return phoneIndexMap.get(String.valueOf(phoneNumberPrefix));
    }
}
