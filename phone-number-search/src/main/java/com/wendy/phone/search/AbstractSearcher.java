package com.wendy.phone.search;

import com.wendy.phone.Attribution;
import com.wendy.phone.PhoneNumberInfo;
import com.wendy.phone.PhoneNumberSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wendy
 * @since 2020/5/26
 */
public abstract class AbstractSearcher implements Searcher {

    private static final Logger logger = LoggerFactory.getLogger(AbstractSearcher.class);

    protected  ByteBuffer originalByteBuffer;
    protected  int startIndexOffset;
    protected  int endIndexOffset;
    protected  int dataVersion;

    protected  Map<Integer,Attribution> phoneAttrMap = new ConcurrentHashMap<>();

    public AbstractSearcher() {
        try (InputStream inputStream = PhoneNumberSearcher.class.getClassLoader().getResourceAsStream(PHONE_NUMBER_DAT);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        ) {
            byte[] allBytes;
            byte[] buffer = new byte[1024 * 4];
            int n;
            while ((n = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, n);
            }
            allBytes = byteArrayOutputStream.toByteArray();
            int hashCode = Arrays.hashCode(allBytes);
            logger.info("bytes hashCode is {}", hashCode);


            originalByteBuffer = ByteBuffer.wrap(allBytes).asReadOnlyBuffer().order(ByteOrder.LITTLE_ENDIAN);
            dataVersion = originalByteBuffer.getInt();
            startIndexOffset = originalByteBuffer.getInt();
            endIndexOffset = originalByteBuffer.capacity();
        } catch (Exception ex) {
            logger.warn("init phone number data error!", ex);
        }

    }



    protected int correctPosition(int index) {
        int dValue = index - startIndexOffset;
        if (dValue < 9) {
            return startIndexOffset;
        } else {
            int remain = dValue % 9;
            if (remain != 0) {
                return index - remain;
            }
            return index;
        }
    }

    private boolean isInvalidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            logger.debug("phone number is null");
            return true;
        }
        int phoneNumberLength = phoneNumber.length();
        if (phoneNumberLength < 7 || phoneNumberLength > 11) {
            logger.debug("phone number {} is not acceptable, length invalid, length should be 11 or 7(for left 7 numbers), actual: {}",
                    phoneNumber, phoneNumberLength);
            return true;
        }
        return false;
    }


    private int validate(String phoneNumber) {
        if (isInvalidPhoneNumber(phoneNumber)) {
            return -1;
        }
        try {
            return Integer.parseInt(phoneNumber.substring(0, 7));
        } catch (NumberFormatException e) {
            logger.warn("phone [{}] is not allowed!", phoneNumber);
            return -1;
        }
    }

    @Override
    public final Optional<PhoneNumberInfo> lookup(String phoneNumber) {
        ByteBuffer byteBuffer = null;
        if (originalByteBuffer!=null){
            byteBuffer = originalByteBuffer.asReadOnlyBuffer().order(ByteOrder.LITTLE_ENDIAN);
        }

        int phoneNumberPrefix = validate(phoneNumber);
        if (phoneNumberPrefix == -1) {
            return Optional.empty();
        }

        PhoneIndex phoneIndex =  search(phoneNumberPrefix,byteBuffer);
        if (Objects.isNull(phoneIndex)) {
            return Optional.empty();
        }

        return extract(phoneNumber, phoneIndex, byteBuffer);
    }


    protected abstract Optional<PhoneNumberInfo> extract(String phoneNumber, PhoneIndex phoneIndex, ByteBuffer byteBuffer) ;



    protected int determineInfoLength(int infoStartIndex, ByteBuffer byteBuffer) {
        byteBuffer.position(infoStartIndex);
        //noinspection StatementWithEmptyBody
        while ((byteBuffer.get()) != 0) {
            // just to find index of next '\0'
        }
        int infoEnd = byteBuffer.position() - 1;
        byteBuffer.position(infoStartIndex); //reset to info start index
        return infoEnd - infoStartIndex;
    }


    protected Attribution parse(String ori) {
        String[] split = ori.split("\\|");
        if (split.length < 4) {
            throw new IllegalStateException("content format error");
        }
        return Attribution.builder()
                .province(split[0])
                .city(split[1])
                .zipCode(split[2])
                .areaCode(split[3])
                .build();
    }

    protected abstract PhoneIndex search(int phoneNumberPrefix,ByteBuffer byteBuffer);

}
