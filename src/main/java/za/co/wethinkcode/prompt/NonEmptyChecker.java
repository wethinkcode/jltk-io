package za.co.wethinkcode.prompt;

import java.util.*;

/**
 * A Checker that is satisfied by any String that is neither empty nor blank.
 */
public class NonEmptyChecker implements Checker {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(String candidate, ArrayList<Reply> replies) {
        if (candidate.isBlank()) return false;
        replies.add(new Reply(candidate));
        return true;
    }
}
