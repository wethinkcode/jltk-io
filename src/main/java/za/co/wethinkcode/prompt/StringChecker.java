package za.co.wethinkcode.prompt;

import java.util.*;
/**
 * A Checker that is satisfied by any String, including empty or blank ones.
 */
public class StringChecker implements Checker {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(String candidate, ArrayList<Reply> replies) {
        replies.add(new Reply(candidate));
        return true;
    }
}
