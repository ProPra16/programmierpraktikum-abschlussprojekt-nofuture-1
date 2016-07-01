package phases;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PhasesTest {
    @Test
    public void Test1(){
        Phases p = new Phases();
        p.setPhase("green");
        assertEquals("green", p.getPhase());
    }
}
