package sml;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistersTest {

    private Registers registers;

    @Before
    public void setup() {
        registers = new Registers();
    }

    @Test
    public void shouldHaveRegistersSetToZero() {
        Arrays.stream(registers.getRegisters()).forEach(register -> assertThat(register, is(equalTo(0))));
    }

    @Test
    public void shouldBeAbleToSetARegister() {
        int value = 6;
        int register = 3;
        registers.setRegister(register, value);
        assertThat(registers.getRegister(register), is(equalTo(value)));
    }
}