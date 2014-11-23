package sml;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class TranslatorTest {

    private Translator translator;
    private Labels labels;
    private ArrayList<Instruction> list;

    @Before
    public void setup() {
        translator = new Translator("instructions.txt");
        labels = mock(Labels.class);
        list = new ArrayList<>();
    }

    @Test
    public void shouldBeAbleToReadAndTranslate() {
        assertThat(translator.readAndTranslate(labels, list), is(true));
    }

    @Test
    public void shouldBeAbleToGetTheFirstWordFromALine() {
        String line = "Add something";
        System.out.println(translator.scanWord());
    }
}