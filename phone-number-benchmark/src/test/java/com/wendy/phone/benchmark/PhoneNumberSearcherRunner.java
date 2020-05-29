package com.wendy.phone.benchmark;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
*  @author wendy
*  @since 2020/5/28
*/
public class PhoneNumberSearcherRunner {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PhoneNumberSearcherBenchmark.class.getSimpleName())
                .warmupForks(0)
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .warmupTime(TimeValue.seconds(5))
                .measurementTime(TimeValue.seconds(5))
                .timeout(TimeValue.minutes(1))
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.NANOSECONDS)
                .threads(3)
                .build();

        new Runner(opt).run();
    }

}
