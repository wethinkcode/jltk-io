package za.co.wethinkcode.prompt;

import java.math.*;

/**
 * A class to hold an arbitrary String and convert it using the asX methods.
 *
 * The String should have already been validated for the given asX type, because
 * these parsing methods can throw.
 */
public class Reply {

    private final String text;

    public Reply(String text) {
        this.text = text;
    }

    int asInteger() {
        return Integer.parseInt(text);
    }

    String asString() {
        return text;
    }

    double asDouble() {
        return Double.parseDouble(text);
    }

    BigDecimal asDecimal() {
        return new BigDecimal(text);
    }

    public float asFloat() {
        return Float.parseFloat(text);
    }
}
