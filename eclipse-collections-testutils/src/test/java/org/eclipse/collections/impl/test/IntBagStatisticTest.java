/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.test;

import java.util.IntSummaryStatistics;
import java.util.Random;
import java.util.stream.IntStream;

import org.eclipse.collections.api.bag.primitive.MutableIntBag;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.tuple.primitive.IntIntPair;
import org.eclipse.collections.impl.bag.mutable.primitive.IntHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.MutableIntBagFactoryImpl;
import org.eclipse.collections.impl.MyIntHashBag;
import org.junit.Assert;
import org.junit.Test;

public class IntBagStatisticTest
{

    private static final int MAX_SIZE = 30_000_000;

    @Test
    public void testOptimize(){
        IntStream intStream = new Random()
                .ints(1, 1000)
                .limit(MAX_SIZE);
        MutableIntBag mutableIntBag = new MutableIntBagFactoryImpl().withAll(intStream);
        MyIntHashBag myIntHashBag = new MyIntHashBag().withAll(mutableIntBag);

        long start = System.currentTimeMillis();
        IntSummaryStatistics intSummaryStatistics = myIntHashBag.summaryStatistics();
        long end = System.currentTimeMillis();
        System.out.println("optimized IntHashBag of " + MAX_SIZE +" elements cost " + (end-start) + "ms");
        Assert.assertEquals(MAX_SIZE , intSummaryStatistics.getCount());
    }


    @Test
    public void testOptimize2(){
        IntStream intStream = new Random()
                .ints(1, 1000)
                .limit(MAX_SIZE);
        MutableIntBag mutableIntBag = new MutableIntBagFactoryImpl().withAll(intStream);
        IntHashBag myIntHashBag = new IntHashBag().withAll(mutableIntBag);

        long start = System.currentTimeMillis();
        IntSummaryStatistics intSummaryStatistics = myIntHashBag.summaryStatistics();
        long end = System.currentTimeMillis();
        System.out.println("previous IntHashBag of " + MAX_SIZE +" elements cost " + (end-start) + "ms");
        Assert.assertEquals(MAX_SIZE , intSummaryStatistics.getCount());
    }

}
