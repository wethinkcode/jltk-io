package za.co.wethinkcode.io;

import za.co.wethinkcode.prompt.*;
import za.co.wethinkcode.script.*;

import java.io.*;
import java.math.*;
import java.util.*;

public class TestingIo implements Io {

    private Script script = new Script();
    private final StandardIo console;


    public TestingIo() {
        console = new StandardIo(
                new ScriptInputStream(script),
                new PrintStream(new ScriptOutputStream(script))
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Io println(String s) {
        console.println(s);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Io println() {
        console.println();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Io print(String s) {
        console.print(s);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prompt prompt(String text, Checker... checkers) {
        return console.prompt(text, checkers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int anyInteger(String text) {
        return console.anyInteger(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String anyString(String text) {
        return console.anyString(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double anyDouble(String text) {
        return console.anyDouble(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float anyFloat(String text) {
        return console.anyFloat(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal anyDecimal(String text) {
        return console.anyDecimal(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String nonEmpty(String text) {
        return console.nonEmpty(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reply> manyIntegers(String text, int howMany) {
        return console.manyIntegers(text, howMany);
    }

    /**
     * Add a line to the testing script for this object that will verify that the
     * program wrote the matching output onto the Io object.
     *
     * @param line Expected text the computer will output.
     * @return this, to allow chaining.
     */
    public TestingIo computerPrompts(String line) {
        script.add(new ComputerPrompts(line));
        return this;
    }

    /**
     * Add a line to the testing script for this object that will simulate the user
     * entering the given text followed by a newline.
     *
     * @param line Expected text the computer will output.
     * @return this, to allow chaining.
     */
    public TestingIo humanSays(String line) {
        script.add(new HumanSays(line));
        return this;
    }
}
