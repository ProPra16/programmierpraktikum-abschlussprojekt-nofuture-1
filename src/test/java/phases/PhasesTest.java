package phases;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhasesTest {
    @Test
    public void Test1(){
        Phases p = new Phases("green");
        assertEquals("green", p.getPhase());
    }

    @Test
    public void Test2(){
        Phases p = new Phases("abc");
        assertEquals("abc", p.getPhase());
    }
}
