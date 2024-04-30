package za.co.wethinkcode.prompt;

import java.util.*;

/**
 * A Checker that is satisfied by any String that can be parsed into a float.
 */
public class IntegerChecker implements Checker {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(String candidate, ArrayList<Reply> replies) {
        try {
            Integer.parseInt(candidate);
        } catch (Exception unused) {
            return false;
        }
        replies.add(new Reply(candidate));
        return true;
    }
}
