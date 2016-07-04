package tddcycle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TDDCycleTest {
    @Test
    public void Test1(){
        TDDCycle p = new TDDCycle("green");
        assertEquals(true, p.isCompiling("public class Class{}", "public class TestClass{}"));
    }

    @Test
    public void Test2(){
        TDDCycle p = new TDDCycle("red");
        p.next("public class Class{}", "public class TestClass{}");
        assertEquals("green", p.getPhase());
    }
}
