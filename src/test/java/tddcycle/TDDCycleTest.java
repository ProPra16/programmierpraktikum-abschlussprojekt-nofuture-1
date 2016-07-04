package tddcycle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TDDCycleTest {
    @Test
    public void Test1(){
        TDDCycle p = new TDDCycle("green");
        assertEquals(true, p.isCompiling("public class Class{}", "public class TestClass{}"));
    }
}
