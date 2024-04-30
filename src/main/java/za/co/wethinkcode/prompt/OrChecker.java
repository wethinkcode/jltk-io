package za.co.wethinkcode.prompt;

import java.util.*;

/**
 * A Checker that is satisfied if any one of its own Checkers is satisfied.
 */
public class OrChecker implements Checker {
    private Checker[] checkers;

    /**
     * Constructor assigning its internal checkers.
     *
     * @param checkers A variable list of checkers
     */
    public OrChecker(Checker... checkers) {
        this.checkers = checkers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfied(String candidate, ArrayList<Reply> replies) {
        if (checkers == null || checkers.length == 0) return true;
        boolean result = false;
        for (Checker checker : checkers) {
            if (checker.isSatisfied(candidate, replies)) return true;
        }
        return false;
    }
}
