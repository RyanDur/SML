package sml;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TranslatorTest {

    @Test
    public void shouldBeAbleToReadAndTranslate() {
        Translator translator = new Translator("instructions.txt");
        Labels labels = new Labels();
        ArrayList<Instruction> list = new ArrayList<>();
        assertThat(translator.readAndTranslate(labels, list), is(true));
    }
}