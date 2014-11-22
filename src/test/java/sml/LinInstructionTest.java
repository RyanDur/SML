package sml;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LinInstructionTest {

    private LinInstruction linInstruction;
    private String label;
    private int register;
    private int value;

    @Before
    public void setup() {
        value = 4;
        register = 3;
        label = "label";
        linInstruction = new LinInstruction(label, register, value);
    }

    @Test
    public void shouldBeAbleToExecute() {
        Machine machine = mock(Machine.class);
        Registers registers = mock(Registers.class);
        when(machine.getRegisters()).thenReturn(registers);
        linInstruction.execute(machine);
        verify(machine).getRegisters();
        verify(registers).setRegister(anyInt(), anyInt());
    }

    @Test
    public void shouldBeAbleToPrintOut() {
        String opcode = "lin";
        assertThat(linInstruction.toString(), is(equalTo(label + ": " + opcode + " register " + register + " value is " + value)));
    }
}