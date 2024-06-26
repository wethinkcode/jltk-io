package za.co.wethinkcode.io;

import za.co.wethinkcode.prompt.*;

import java.math.*;
import java.util.*;

/**
 * The Io interface is designed to allow students to  use traditionl console I/O,
 * without exposing them to exceptions, and in a fashion that makes microtesting
 * easier.
 *
 * The functionality includes both output and input.
 *
 * On output, we have {@code println(...) } and {@code print(...) }. These function
 * similarly to those found in {@link java.io.PrintStream}.
 *
 * In other words, code using {@code System.out.println(...) }should also work if
 * called on * an instance of Io.
 *
 * On input, we have a single substantive function {@code prompt(...) } and a small set
 * of convenience methods that call it. These functions will issue a prompt on the
 * output, wait for the user to enter a satisfactory response -- possibly re-prompting
 * as needed -- then return that correct response.
 *
 */
public interface Io {
    /**
     * Print the given string to the output, adding a newline.
     *
     * @param s The string to be printed.
     * @return this, to enable chaining.
     */
    Io println(String s);

    /**
     * Print newline to the output.
     *
     * @return this, to enable chaining.
     */
    Io println();

    /**
     * Print the given string to the output.
     *
     * @param s The string to be printed.
     * @return this, to enable chaining.
     */
    Io print(String s);

    /**
     * Advanced method to make a raw prompt, based in this instance of the Io class, that uses
     * the given text for its prompt, and applies the given Checkers to decide
     * whether it has the correct result.
     *
     * @param text     The text given to the user.
     * @param checkers The list of checkers to validate the user input.
     * @return The created Prompt object, bound to this instance of Io.
     */
    Prompt prompt(String text, Checker... checkers);

    /**
     * Offer the user the prompt text, and return any valid integer.
     *
     * @param text The text given to the user.
     * @return The single int entered by the user.
     */
    int anyInteger(String text);

    /**
     * Offer the user the prompt text, and return any valid string.
     * The return is guaranteed non-null, but may be empty or blank.
     *
     * @param text The text given to the user.
     * @return The String entered by the user.
     */
    String anyString(String text);

    /**
     * Offer the user the prompt text, and return any valid double.
     *
     * @param text The text given to the user.
     * @return The double entered by the user.
     */
    double anyDouble(String text);

    /**
     * Offer the user the prompt text, and return any valid float.
     *
     * @param text The text given to the user.
     * @return The double entered by the user.
     */
    float anyFloat(String text);

    /**
     * Offer the user the prompt text, and return any valid decimal value.
     *
     * @param text The text given to the user.
     * @return the BigDecimal entered by the user.
     */
    BigDecimal anyDecimal(String text);

    /**
     * Offer the user the prompt text, and return any valid non-empty/non-blank String.
     *
     * @param text The text given to the user.
     * @return The non-empty String entered by the user.
     */
    String nonEmpty(String text);

    /**
     * Offer the user the prompt text, accept a comma-delimited list of howMany integers,
     * and return them in the form of a List of Reply objects.
     *
     * @param text    The text given to the user.
     * @param howMany How many integers to require in the result.
     * @return The list of exactly howMany Reply objects.
     */
    List<Reply> manyIntegers(String text, int howMany);
}
