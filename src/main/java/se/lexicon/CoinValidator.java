package se.lexicon;

import java.util.Set;

public class CoinValidator {

    private static final Set<Integer> ACCEPTED_COINS = Set.of(1, 2, 5, 10, 20, 50);

    private CoinValidator() {}

    public static boolean isValidValue(int value) {
        return ACCEPTED_COINS.contains(value);
    }
}
