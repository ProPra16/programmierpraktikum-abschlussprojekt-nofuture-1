package tddcycle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TDDCycleTest {
    @Test
    public void Test1(){
        TDDCycle p = new TDDCycle("green");
        p.compile("public class Class{}", "public class TestClass{}");
        assertEquals(false, p.hasCompileErrors());
    }
}
