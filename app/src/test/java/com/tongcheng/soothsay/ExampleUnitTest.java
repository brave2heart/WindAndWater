package com.tongcheng.soothsay;

import android.util.Log;

import com.tongcheng.soothsay.utils.RegexUtils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void setRe() {
        boolean mobileSimple = RegexUtils.isMobileSimple("46897523");
        System.out.println(mobileSimple);
    }
}