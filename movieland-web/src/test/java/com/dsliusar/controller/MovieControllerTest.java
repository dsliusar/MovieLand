package com.dsliusar.web.controller;

import com.dsliusar.enums.SortTypeEnum;
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
        String expectedSort = SortTypeEnum.validateSortType(asc).toString();

        Assert.assertEquals(asc.toLowerCase(), expectedSort.toLowerCase());

        expectedSort = SortTypeEnum.validateSortType(desc).toString();
        Assert.assertEquals(desc.toLowerCase(), expectedSort.toLowerCase());

    }
}
