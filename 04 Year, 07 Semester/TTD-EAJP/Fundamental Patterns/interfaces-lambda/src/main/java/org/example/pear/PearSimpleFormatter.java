package org.example.pear;

public class PearSimpleFormatter implements PearFormatter{

    @Override
    public String accept(Pear pear) {
       return "A pear of " + pear.getWeight();
    }
}
