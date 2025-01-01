package org.example.pear;

import org.example.color.Color;

public class PearGreenColorPredicate implements PearPredicate{
    @Override
    public boolean predicate(Pear pear) {
        return Color.GREEN.equals(pear.getColor());
    }
}
