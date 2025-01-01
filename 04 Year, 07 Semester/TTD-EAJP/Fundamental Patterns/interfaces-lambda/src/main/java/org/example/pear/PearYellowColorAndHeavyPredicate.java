package org.example.pear;

import org.example.color.Color;

public class PearYellowColorAndHeavyPredicate implements PearPredicate {
    @Override
    public boolean predicate(Pear pear) {
        return Color.YELLOW.equals(pear.getColor()) && pear.getWeight() > 150;
    }
}
