package se.lexicon;

public class Beverage extends Product {

    private int volumeMl;

    public Beverage(int id, String name, int price, int quantity, int volumeMl) {
        super(id, name, price, quantity);
        setVolumeMl(volumeMl);
    }

    public int getVolumeMl() {return volumeMl;}

    void setVolumeMl (int volumeMl) {
        if ( volumeMl < 1) {
            throw new IllegalArgumentException("Volume Ml must be a positive integer");
        }
        this.volumeMl = volumeMl;
    }

    @Override
    public String describe() {
        return getName() + " (" + getClass().getSimpleName() + ", " + getVolumeMl() + "ml)";
    }
}
