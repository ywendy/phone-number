package com.wendy.phone;

import com.wendy.phone.search.BinarySearcher;
import com.wendy.phone.search.MemoryIndexSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wendy
 * @since 2020/5/29
 */
@DisplayName("查找手机归属地")
public class PhoneNumberSearcherTest extends AbstractTest {

    private PhoneNumberSearcher binarySearch;
    private PhoneNumberSearcher memorySearch;


    @BeforeEach
    public void setUp() {
        super.initData();
        binarySearch = new PhoneNumberSearcher(new BinarySearcher());
        memorySearch = new PhoneNumberSearcher(new MemoryIndexSearcher());
    }


    @Test
    @DisplayName("二分法查找基础测试")
    public void baseBinarySearch() {
        Optional<PhoneNumberInfo> lookup = binarySearch.lookup(basePhone.getPhone());
        assertTrue(lookup.isPresent());
        PhoneNumberInfo phoneNumberInfo = lookup.get();
        assertNotNull(phoneNumberInfo);
        assertEquals(phoneNumberInfo.getIsp().getCnName(), basePhone.getIsp());
        assertEquals(phoneNumberInfo.getPhoneNumber(), basePhone.getPhone());
        assertEquals(phoneNumberInfo.getAttribution().getProvince(), basePhone.getProvince());
        assertEquals(phoneNumberInfo.getAttribution().getCity(), basePhone.getCity());
    }

    @Test
    @DisplayName("二分法查找边界测试")
    public void boundaryBinarySearch() {
        Optional<PhoneNumberInfo> upLookup = binarySearch.lookup(upBoundary.getPhone());
        assertFalse(upLookup.isPresent());
        Optional<PhoneNumberInfo> downLookup = binarySearch.lookup(downBoundary.getPhone());
        assertFalse(downLookup.isPresent());
    }


    @Test
    @DisplayName("二分法查找全量测试")
    public void allBinarySearch() {
        for (int i = 0, len = phoneList.size(); i < len; i++) {
            Optional<PhoneNumberInfo> lookup = binarySearch.lookup(phoneList.get(i));
            assertNotNull(lookup);
        }
    }



    @Test
    @DisplayName("内存查找基础测试")
    public void baseMemorySearch() {
        Optional<PhoneNumberInfo> lookup = binarySearch.lookup(basePhone.getPhone());
        assertTrue(lookup.isPresent());
        PhoneNumberInfo phoneNumberInfo = lookup.get();
        assertNotNull(phoneNumberInfo);
        assertEquals(phoneNumberInfo.getIsp().getCnName(), basePhone.getIsp());
        assertEquals(phoneNumberInfo.getPhoneNumber(), basePhone.getPhone());
        assertEquals(phoneNumberInfo.getAttribution().getProvince(), basePhone.getProvince());
        assertEquals(phoneNumberInfo.getAttribution().getCity(), basePhone.getCity());
    }

    @Test
    @DisplayName("内存查找边界测试")
    public void boundaryMemorySearch() {
        Optional<PhoneNumberInfo> upLookup = binarySearch.lookup(upBoundary.getPhone());
        assertFalse(upLookup.isPresent());
        Optional<PhoneNumberInfo> downLookup = binarySearch.lookup(downBoundary.getPhone());
        assertFalse(downLookup.isPresent());
    }


    @Test
    @DisplayName("内存查找全量测试")
    public void allMemorySearch() {
        for (int i = 0, len = phoneList.size(); i < len; i++) {
            Optional<PhoneNumberInfo> lookup = binarySearch.lookup(phoneList.get(i));
            assertNotNull(lookup);
        }
    }


}
