package com.test.assertion;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
 
@Listeners({com.test.assertion.AssertionListener.class})
public class TestAssert {
 
     
    @Test
    public void testAssert3(){ 
        Assertion.verifyEquals(2, 3, "比较两个数是否相等：");
        Assertion.verifyEquals(1, 2, "比较两个数是否相等：");    
    }
     
    @Test
    public void testAssert4(){ 
        Assertion.verifyEquals(4, 3, "比较两个数是否相等：");
        Assertion.verifyEquals(2, 2, "比较两个数是否相等：");    
    }
 
}