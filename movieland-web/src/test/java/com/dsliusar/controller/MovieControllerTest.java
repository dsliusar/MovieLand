package com.dsliusar.controller;

import com.dsliusar.enums.SorterValidatorEnum;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Оля on 19.06.2016.
 */
public class MovieControllerTest {

    @Test
    public void movieSortControllerTest(){
        String asc = "asc";
        String desc = "desc";
        String expectedSort = SorterValidatorEnum.vaidateOrderClause(asc);

        Assert.assertEquals(asc.toLowerCase(), expectedSort.toLowerCase());

        expectedSort = SorterValidatorEnum.vaidateOrderClause(desc);
        Assert.assertEquals(desc.toLowerCase(), expectedSort.toLowerCase());

    }
}
