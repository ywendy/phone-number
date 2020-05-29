package com.wendy.phone.search;

import com.wendy.phone.PhoneNumberInfo;

import java.util.Optional;

/**
 * @author wendy
 * @since 2020/5/26
 */
public interface Searcher {
    String PHONE_NUMBER_DAT = "phone.dat";

    Optional<PhoneNumberInfo> lookup(String phoneNumber);

}
