package za.co.wethinkcode.prompt;

import java.io.*;
import java.math.*;
import java.util.*;

/**
 * Provides easy and safe collection and validation of user input.
 * <p>
 * The Prompt class is used to create prompts on a PrintStream and read whole lines from
 * an InputStream, returning those lines as Reply objects which can be used to extract
 * fundamental java types safely. Unlike java.util.Scanner, it does not throw exceptions on
 * invalid user input, it just repeats the prompt and tries again.</p>
 *
 * <p>Although the Prompt class supports arbitrary i/o streams and can take any class
 * that implements Checker, it supports the most common scenarios in two ways:
 * </p>
 * <ul>
 *     <li>Multiple constructors assume System.in and System.out unless constructed otherwise.</li>
 *     <li>The most common cases are supported by static convenience functions.</li>
 * </ul>
 *
 * <p>The snippet below will prompt the user with "Please enter the row number: " and wait for
 * the user to type characters and press enter. If the user enters a valid integer, it will
 * return, and that integer will be the 0th reply value. If the user enters a non-integer, or a
 * blank line, it will repeat the prompt.</p>
 *
 * <pre>
 *      Prompt rowPrompt = new Prompt("Please enter the row number: ",new IntegerChecker());
 *      rowPrompt.run();
 *      int rowNumber = rowPrompt.reply(0).asInteger();
 * </pre>
 *
 * <p>That same effect can be achieved by this one-liner convenience function:</p>
 *
 * <pre>
 *      int rowNumber = Prompt.anyInteger("Please enter the row number: ");
 * </pre>
 *
 * <p>
 *     A prompt may be created once and run many times. Each run is a separate process and prints new
 *     text and reads input again.
 * </p>
 *
 * @see Checker for several useful pre-defined checkers.
 */
public class Prompt {
    private final String text;
    private final ArrayList<Reply> replies = new ArrayList<>();
    private final InputStream in;
    private final PrintStream out;
    private final Checker checker;

    /**
     * Full constructor
     * @param in which inputstream to accept input on
     * @param out which output stream to print prompt(s) on.
     * @param text the text of the prompt
     * @param checkers the list of checkers -- or'd if there's more than one
     */
    public Prompt(InputStream in, PrintStream out, String text, Checker... checkers) {
        this.in = in;
        this.out = out;
        this.text = text;
        this.checker = Checker.safeChecker(checkers);
    }

    /**
     * Convenience constructor. As with the full constructor, except that {@code System.in} and
     * {@code System.out} are assumed.
     *
     * @param text the text of the prompt
     * @param checkers the list of checkers -- or'd together if more than one
     */
    public Prompt(String text, Checker... checkers) {
        this(null,null, text, checkers);
    }

    /**
     * Get the index'th reply returned by this prompt.
     * @param index which reply to return
     * @return the given Reply object
     */
    public Reply reply(int index) {
        return replies.get(index);
    }

    /**
     * Return the 0th reply from this prompt. Convenience method, as most prompts only have a single reply value.
     * @return the first Reply
     */
    public Reply asReply() {
        return reply(0);
    }

    /**
     *Return the full list of replies for this prompt.
     * @return the entire list of replies
     */
    public List<Reply> asReplies() {
        return replies;
    }

    /**
     * Return the 0th reply for this prompt as a String.
     *
     * @return what the user typed in reply
     */
    public String asString() {
        return asReply().asString();
    }

    /**
     * Return the 0th reply for this prompt as an Integer
     *
     * @return what the user typed in reply, converted to Integer
     */
    public int asInteger() {
        return asReply().asInteger();
    }

    /**
     * Return the 0th reply for this prompt as a float
     *
     * @return what the user typed in reply, converted to float
     */
    public float asFloat() {
        return asReply().asFloat();
    }

    /**
     * Return the 0th reply for this prompt as a Double
     *
     * @return what the user typed in reply, converted to Double
     */
    public double asDouble() {
        return asReply().asDouble();
    }

    /**
     * Return the 0th reply for this prompt as a Double
     *
     * @return what the user typed in reply, converted to Double
     */
    public BigDecimal asDecimal() {
        return asReply().asDecimal();
    }

    /**
     * Actually run the prompt, causing its text to be output, the gathering of a reply, and the checking that
     * the reply passes the checker(s).
     */
    public void run() {
        while (true) {
            chooseOut().print(text);
            String response = new Scanner(chooseIn()).nextLine();
            if (checker.isSatisfied(response, replies)) break;
        }
    }

    private InputStream chooseIn() {
        if (in == null) return System.in;
        return in;
    }

    private PrintStream chooseOut() {
        if (out == null) return System.out;
        return out;
    }

}
