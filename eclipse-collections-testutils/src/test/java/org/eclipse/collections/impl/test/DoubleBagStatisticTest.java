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

import java.util.DoubleSummaryStatistics;
import java.util.Random;
import java.util.stream.DoubleStream;

import org.eclipse.collections.api.bag.primitive.MutableDoubleBag;
import org.eclipse.collections.impl.bag.mutable.primitive.DoubleHashBag;
import org.eclipse.collections.impl.bag.mutable.primitive.MutableDoubleBagFactoryImpl;
import org.eclipse.collections.impl.MyDoubleHashBag;
import org.junit.Assert;
import org.junit.Test;

public class DoubleBagStatisticTest
{

    private static final int MAX_SIZE = 5_000_000;

    @Test
    public void testOptimize(){
        DoubleStream doubleStream = new Random()
                .doubles(1.0d, 10.0d)
                .limit(MAX_SIZE);
        MutableDoubleBag mutableDoubleBag = new MutableDoubleBagFactoryImpl().withAll(doubleStream);
        MyDoubleHashBag myDoubleHashBag = new MyDoubleHashBag().withAll(mutableDoubleBag);

        long start = System.currentTimeMillis();
        DoubleSummaryStatistics doubleSummaryStatistics = myDoubleHashBag.summaryStatistics();
        long end = System.currentTimeMillis();
        System.out.println("optimized DoubleHashBag of " + MAX_SIZE +" elements cost " + (end-start) + "ms");
        Assert.assertEquals(MAX_SIZE , doubleSummaryStatistics.getCount());
    }

    @Test
    public void testOptimize2(){
        DoubleStream doubleStream = new Random()
                .doubles(1.0d, 10.0d)
                .limit(MAX_SIZE);
        MutableDoubleBag mutableDoubleBag = new MutableDoubleBagFactoryImpl().withAll(doubleStream);
        DoubleHashBag doubleHashBag = new DoubleHashBag().withAll(mutableDoubleBag);

        long start = System.currentTimeMillis();
        DoubleSummaryStatistics doubleSummaryStatistics = doubleHashBag.summaryStatistics();
        long end = System.currentTimeMillis();
        System.out.println("previous DoubleHashBag of " + MAX_SIZE +" elements cost " + (end-start) + "ms");
        Assert.assertEquals(MAX_SIZE , doubleSummaryStatistics.getCount());

    }

}
