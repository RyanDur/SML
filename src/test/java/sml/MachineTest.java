package sml;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class MachineTest {

    private Machine machine;
    private LinInstruction linInstruction;
    private LinInstruction linInstruction1;
    private LinInstruction linInstruction2;
    private String line;
    private String line1;
    private String line2;

    @Before
    public void setup() {
        machine = new Machine();
        line = "f0: lin register 20 value is 6";
        linInstruction = mock(LinInstruction.class);
        when(linInstruction.toString()).thenReturn(line);

        line1 = "f1: lin register 21 value is 1";
        linInstruction1 = mock(LinInstruction.class);
        when(linInstruction1.toString()).thenReturn(line1);

        line2 = "f2: lin register 22 value is 1";
        linInstruction2 = mock(LinInstruction.class);
        when(linInstruction2.toString()).thenReturn(line2);

        machine.setProg(Stream.of(linInstruction, linInstruction1, linInstruction2)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
    }

    @Test
    public void shouldBeAbleToPrintTheProgram() {
        String program =  line + "\n" + line1 + "\n" + line2 + "\n";
        assertThat(machine.toString(), is(equalTo(program)));
    }

    @Test
    public void shouldBeAbleToExecuteTheInstructions() {
        machine.execute();
        InOrder order = inOrder(linInstruction, linInstruction1, linInstruction2);
        order.verify(linInstruction).execute(machine);
        order.verify(linInstruction1).execute(machine);
        order.verify(linInstruction2).execute(machine);
    }
}