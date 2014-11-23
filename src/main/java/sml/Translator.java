package sml;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

    // word + line is the part of the current line that's not yet processed
    // word has no whitespace
    // If word and line are not empty, line begins with whitespace
    private String line = "";
    private Labels labels; // The labels of the program being translated
    private ArrayList<Instruction> program; // The program to be created
    private String fileName; // source file of SML code

    private static final String SRC = "src";

    public Translator(String fileName) {
        this.fileName = fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"
    public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {
        labels = lab;
        labels.reset();
        program = prog;
        program.clear();
        try {
            // Each iteration processes line and reads the next line into line
            Files.lines(Paths.get(SRC, fileName)).forEach(line -> {
                this.line = line;
                String label = scanWord();
                if (label.length() > 0) {
                    Instruction ins = getInstruction(label);
                    if (ins != null) {
                        labels.addLabel(label);
                        program.add(ins);
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("File: IO error to start " + e.getMessage());
            return false;
        }
        return true;
    }

    // line should consist of an MML instruction, with its label already
    // removed. Translate line into an instruction with label label
    // and return the instruction
    public Instruction getInstruction(String label) {
        if (line.equals("")) return null;
        try {
            Class<?> clazz = Class.forName(getClassName(scanWord()));
            Class<?>[] parameterList = getParameterTypes(clazz.getDeclaredConstructors()[0]);
            List<Object> params = new ArrayList<>();
            params.add(label);
            for (int i = params.size(); i < parameterList.length; i++) {
                if (parameterList[i].isPrimitive()) params.add(scanInt());
                else params.add(scanWord());
            }
            return (Instruction) clazz.getDeclaredConstructor(parameterList).newInstance(params.toArray());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            System.out.println("Holy Crap! Can't find " + e.getMessage());
        }
        return null;
    }

    private Class<?>[] getParameterTypes(Constructor constructor) {
        List<Class<?>> collect = Arrays.stream(constructor.getParameterTypes()).map(c -> {
            if (c.isPrimitive()) {
                return int.class;
            } else {
                try {
                    return c.newInstance().getClass();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }).collect(Collectors.toList());
        return collect.toArray(new Class<?>[collect.size()]);
    }

    private String getClassName(String ins) {
        return "sml." + ins.substring(0, 1).toUpperCase() +
                ins.substring(1, ins.length()) +
                "Instruction";
    }

    /*
     * Return the first word of line and remove it from line. If there is no
     * word, return ""
     */
    public String scanWord() {
        line = line.trim();
        if (line.length() == 0) return "";
        List<String> words = Arrays.stream(line.split(" ")).collect(Collectors.toList());
        String word = words.remove(0);
        line = words.stream().collect(Collectors.joining(" "));
        return word;
    }

    // Return the first word of line as an integer. If there is
    // any error, return the maximum int
    public int scanInt() {
        String word = scanWord();
        if (word.length() == 0) {
            return Integer.MAX_VALUE;
        }

        try {
            return Integer.parseInt(word);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }
}