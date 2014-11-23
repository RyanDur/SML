package sml;

public class BnzInstruction extends Instruction {

    private final int register;
    private final String l2;

    public BnzInstruction(String label, int register, String l2) {
        super(label, "bzr");
        this.label = label;
        this.register = register;
        this.l2 = l2;
    }

    @Override
    public void execute(Machine m) {
        if (m.getRegisters().getRegister(register) > 0)
            m.setPc(m.getLabels().indexOf(l2));
    }

    @Override
    public String toString() {
        return super.toString() + " register " + register + " value is " + l2;
    }
}
