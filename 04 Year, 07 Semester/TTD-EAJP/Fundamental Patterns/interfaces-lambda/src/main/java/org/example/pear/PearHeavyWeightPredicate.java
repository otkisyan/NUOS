package org.example.pear;

public class PearHeavyWeightPredicate implements PearPredicate {

    @Override
    public boolean predicate(Pear pear) {
        return pear.getWeight() > 150;
    }
}
