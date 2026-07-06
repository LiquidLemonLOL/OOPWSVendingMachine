package se.lexicon;

public class Snack extends Product {

    private int weightGrams;

    public Snack(int id, String name, int price, int quantity, int weightGrams) {
        super(id, name, price, quantity);
        setWeightGrams(weightGrams);
    }

    public int getWeightGrams() { return weightGrams; }

    void setWeightGrams (int weightGrams) {
        if (weightGrams < 1) {
            throw new IllegalArgumentException("Volume Ml must be a positive integer");
        }
        this.weightGrams = weightGrams;
    }

    @Override
    public String describe() {
        return getName() + " (" + getClass().getSimpleName() + ", " + getWeightGrams() + "g)";
    }
}
