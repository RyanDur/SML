package sml;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LabelsTest {

    private Labels labels;

    @Before
    public void setup() {
        labels = new Labels();
    }

    @Test
    public void shouldBeAbleToAddALabelAndGetItsIndex() {
        assertThat(labels.addLabel("label"), is(equalTo(0)));
        assertThat(labels.addLabel("label"), is(equalTo(1)));
    }

    @Test
    public void shouldBeAbleToGetTheIndexOfALabel() {
        String label3 = "label3";
        labels.addLabel("label1");
        labels.addLabel("label2");
        labels.addLabel(label3);
        labels.addLabel("label4");
        assertThat(labels.indexOf(label3), is(equalTo(2)));
    }

    @Test
    public void shouldGetNegativeOneIfLabelDoesNotExist() {
        String label3 = "label3";
        labels.addLabel("label1");
        labels.addLabel("label2");
        labels.addLabel(label3);
        labels.addLabel("label4");
        assertThat(labels.indexOf("label5"), is(equalTo(-1)));
    }

    @Test
    public void shouldBeAbleToPrintOutTheLabels() {
        String label1 = "label1";
        String label2 = "label2";
        String label3 = "label3";
        String label4 = "label4";
        labels.addLabel(label1);
        labels.addLabel(label2);
        labels.addLabel(label3);
        labels.addLabel(label4);
        assertThat(labels.toString(), is(equalTo("(" + label1 + ", "+ label2 + ", " + label3 + ", " + label4 + ")")));
    }

    @Test
    public void shouldBeAbleToResetTheLabels() {
        String label1 = "label1";
        String label2 = "label2";
        String label3 = "label3";
        String label4 = "label4";
        labels.addLabel(label1);
        labels.addLabel(label2);
        labels.addLabel(label3);
        labels.addLabel(label4);
        labels.reset();
        assertThat(labels.toString(), is(equalTo("()")));
    }
}