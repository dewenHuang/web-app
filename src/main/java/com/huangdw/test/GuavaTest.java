package com.huangdw.test;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 在guava库中，自带了过滤器(filter)的功能，可以用来对collection 进行过滤
 *
 * @author Devin
 * @date 2018/12/4
 */
public class GuavaTest {
    @Test
    public void testFilter() {
        // 过滤出大于某数的元素
        List<Integer> ages = Lists.newArrayList(23, 25, 27, 29, 31, 33, 35);
        Collection<Integer> result = Collections2.filter(ages, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input > 30;
            }
        });

        assertEquals(3, result.size());
        System.out.println(result);
        assertEquals(7, ages.size());
        System.out.println(ages);
    }

    @Test
    public void testTransform() {
        // 对每个元素进行改造
        List<Integer> ages = Lists.newArrayList(23, 25, 27, 29, 31, 33, 35);
        List<String> result = Lists.transform(ages, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return input + "岁";
            }
        });

        System.out.println(result);
        System.out.println(ages);
    }
}
