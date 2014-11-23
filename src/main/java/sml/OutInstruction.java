package sml;

public class OutInstruction extends Instruction {

    private int register;

    public OutInstruction(String l, int register) {
        super(l, "out");
        this.register = register;
    }

    @Override
    public void execute(Machine m) {
        System.out.println(m.getRegisters().getRegister(register));
    }

    @Override
    public String toString() {
        return super.toString() + " register " + register;
    }
}
