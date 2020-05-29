package com.wendy.phone.benchmark;

import com.wendy.phone.PhoneNumberSearcher;
import com.wendy.phone.search.MemoryIndexSearcher;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wendy
 * @since 2020/5/28
 */
public class PhoneNumberSearcherBenchmark {




    @State(Scope.Thread)
    public static class PhoneNumbers {
        private final List<String> phoneNumbers;
        private int index = 0;

        public PhoneNumbers() {
            this.phoneNumbers = Stream.generate(() -> {
                long phoneNumber = (long) (ThreadLocalRandom.current().nextDouble(1D, 2D) * 1000_000_000_0L);
                return String.valueOf(phoneNumber);
            }).limit(2_000_000)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        public String getPhoneNumber() {
            if (index == phoneNumbers.size()) {
                index = 0;
            }
            return phoneNumbers.get(index++);
        }
    }
    private static PhoneNumberSearcher binarySearch = new PhoneNumberSearcher();
    private static PhoneNumberSearcher memorySearch = new PhoneNumberSearcher(new MemoryIndexSearcher());
    @Benchmark
    public void binarySearch(PhoneNumbers phoneNumbers) {
        binarySearch.lookup(phoneNumbers.getPhoneNumber());
    }


    @Benchmark
    public void memorySearch(PhoneNumbers phoneNumbers) {
        memorySearch.lookup(phoneNumbers.getPhoneNumber());
    }

}
