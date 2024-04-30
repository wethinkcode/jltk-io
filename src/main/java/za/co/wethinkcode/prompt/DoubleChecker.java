package za.co.wethinkcode.prompt;

import java.util.*;

/**
 * A Checker that is satisfied by any String that can be parsed into a Double.
 */
public class DoubleChecker implements Checker {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(String candidate, ArrayList<Reply> replies) {
        try {
            Double.parseDouble(candidate);
        } catch (Exception unused) {
            return false;
        }
        replies.add(new Reply(candidate));
        return true;
    }
}
