package sml;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddInstructionTest {

    private AddInstruction addInstruction;
    private int result;
    private int op1;
    private int op2;
    private String label;

    @Before
    public void setup() {
        label = "label";
        result = 0;
        op1 = 3;
        op2 = 5;
        addInstruction = new AddInstruction(label, result, op1, op2);
    }

    @Test
    public void shouldBeAbleToExecuteAnInstruction() {
        int value = 4;
        int value1 = 3;
        Machine machine = mock(Machine.class);
        Registers registers = mock(Registers.class);
        when(machine.getRegisters()).thenReturn(registers);
        when(registers.getRegister(op1)).thenReturn(value);
        when(registers.getRegister(op2)).thenReturn(value1);
        addInstruction.execute(machine);
        verify(registers).setRegister(result, value + value1);
    }

    @Test
    public void shouldBeAbleToPrintTheInstruction() {
        assertThat(addInstruction.toString(), is(equalTo(label + ": add " + op1 + " + " + op2 + " to " + result)));
    }
}