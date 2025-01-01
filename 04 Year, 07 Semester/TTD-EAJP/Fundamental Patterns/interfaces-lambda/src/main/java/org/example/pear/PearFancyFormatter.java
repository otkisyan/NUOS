package org.example.pear;

public class PearFancyFormatter implements PearFormatter{
    @Override
    public String accept(Pear pear) {
       String characteristic = pear.getWeight() > 150 ? "heavy" : "light";
       return "A " + characteristic + " " +  pear.getColor() + " pear";
    }
}
