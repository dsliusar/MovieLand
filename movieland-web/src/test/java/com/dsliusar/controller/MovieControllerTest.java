package com.dsliusar.web.controller;

import com.dsliusar.tools.enums.SortType;
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
        String expectedSort = SortType.validateSortType(asc).toString();

        Assert.assertEquals(asc.toLowerCase(), expectedSort.toLowerCase());

        expectedSort = SortType.validateSortType(desc).toString();
        Assert.assertEquals(desc.toLowerCase(), expectedSort.toLowerCase());

    }
}
