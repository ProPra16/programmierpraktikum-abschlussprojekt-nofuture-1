package phases;

public class Phases implements InterfaceTDDT{

    private String phase;

    public Phases(String phase){
        setPhase(phase);
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

}
