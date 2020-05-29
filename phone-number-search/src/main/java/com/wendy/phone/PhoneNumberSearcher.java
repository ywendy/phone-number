package com.wendy.phone;

import com.wendy.phone.search.BinarySearcher;
import com.wendy.phone.search.Searcher;

import java.util.Optional;


/**
 * @author wendy
 * @since 2020/5/26
 */
public class PhoneNumberSearcher {
    private Searcher searcher;


    public PhoneNumberSearcher() {
        this(new BinarySearcher());
    }

    public PhoneNumberSearcher(Searcher searcher) {
        this.searcher = searcher;
    }

    public Optional<PhoneNumberInfo> lookup(String phoneNumber) {
        return searcher.lookup(phoneNumber);
    }


}
