/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl;

import java.util.DoubleSummaryStatistics;

import org.eclipse.collections.api.DoubleIterable;
import org.eclipse.collections.impl.bag.mutable.primitive.DoubleHashBag;

public class MyDoubleHashBag extends DoubleHashBag
{

    @Override
    public DoubleSummaryStatistics summaryStatistics()
    {
        DoubleSummaryStatistics result = new DoubleSummaryStatistics();
        this.forEachWithOccurrences((double each, int occurrences) ->
        {
            DoubleSummaryStatistics total = new DoubleSummaryStatistics();
            DoubleSummaryStatistics temp = new DoubleSummaryStatistics();
            temp.accept(each);
            for (int i = occurrences; i > 0; i /= 2)
            {
                if (i % 2 == 1)
                {
                    total.combine(temp);
                }
                temp.combine(temp);
            }
            result.combine(total);
        });
        return result;
    }

    public MyDoubleHashBag withAll(DoubleIterable iterable)
    {
        this.addAll(iterable);
        return this;
    }
}
