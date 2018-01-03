package com.pluralsight.bookstore.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class TextUtilTest {

    // ======================================
    // =            Test methods            =
    // ======================================

    @Test
    public void shouldSanitize() {
        Assert.assertEquals("lorem ipsum dolor sit.", new TextUtil().sanitize("lorem  ipsum   dolor \n sit."));
        Assert.assertEquals("lorem ipsum dolor sit.", new TextUtil().sanitize("lorem ipsum dolor sit."));
        Assert.assertEquals("lorem ipsum dolor sit.", new TextUtil().sanitize("lorem ipsum dolor  sit."));
    }
}
