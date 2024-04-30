package za.co.wethinkcode.prompt;

import java.math.*;
import java.util.*;

/**
 * A Checker that is satisfied by any String that will construct a BigDecimal.
 */
public class DecimalChecker implements Checker {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(String candidate, ArrayList<Reply> replies) {
        try {
            new BigDecimal(candidate);
        } catch (Exception unused) {
            return false;
        }
        replies.add(new Reply(candidate));
        return true;
    }
}
