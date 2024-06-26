package za.co.wethinkcode.io;

import za.co.wethinkcode.prompt.*;

import java.io.*;
import java.math.*;
import java.util.*;

public class StandardIo implements Io {

    private final InputStream in;
    private final PrintStream out;

    /**
     * Constructor to explicitly assign the input and output streams.
     *
     * @param in
     * @param out
     */
    public StandardIo(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Constructor to implicitly use the normal {@link System.in} and {@link System.out} values.
     */
    public StandardIo() {
        this(System.in, System.out);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Io println(String s) {
        out.println(s);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Io println() {
        out.println();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Io print(String s) {
        out.print(s);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prompt prompt(String text, Checker... checkers) {
        return new Prompt(in, out, text, checkers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int anyInteger(String text) {
        Prompt prompt = prompt(text, new IntegerChecker());
        prompt.run();
        return prompt.asInteger();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String anyString(String text) {
        Prompt prompt = prompt(text, new StringChecker());
        prompt.run();
        return prompt.asString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double anyDouble(String text) {
        Prompt prompt = prompt(text, new DoubleChecker());
        prompt.run();
        return prompt.asDouble();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float anyFloat(String text) {
        Prompt prompt = prompt(text, new FloatChecker());
        prompt.run();
        return prompt.asFloat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal anyDecimal(String text) {
        Prompt prompt = prompt(text, new DecimalChecker());
        prompt.run();
        return prompt.asDecimal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String nonEmpty(String text) {
        Prompt prompt = prompt(text, new NonEmptyChecker());
        prompt.run();
        return prompt.asString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Reply> manyIntegers(String text, int howMany) {
        Checker[] checkers = new Checker[howMany];
        for (int checker = 0; checker < howMany; checker++) checkers[checker] = new IntegerChecker();
        Prompt prompt = prompt(text, new SeriesChecker("[\b,]", checkers));
        prompt.run();
        return prompt.asReplies();
    }

}
