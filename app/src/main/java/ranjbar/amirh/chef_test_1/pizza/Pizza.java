package ranjbar.amirh.chef_test_1.pizza;

/**
 * Created by amirh on 01/09/17.
 */

public class Pizza {
    //pizza attr

    static int maxDoughSize = 3; // for 1 , 2 , 4 person
    private Size size;
    private Dough dough;
    private Sausage sausage;
    private Keilbas keilbas;
    private Meat meat;
    private Flavors flavors = new Flavors();
    private Cheese cheese;

    public Cheese getCheese() {
        return cheese;
    }

    public void setCheese(Cheese cheese) {
        this.cheese = cheese;
    }

    public Flavors getFlavors() {
        return flavors;
    }

    public void setFlavors(Flavors flavors) {
        this.flavors = flavors;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size s) {
        size = s;
    }

    public Dough getDough() {
        return dough;
    }

    public void setDough(Dough d) {
        dough = d;
    }

    public Sausage getSausage() {
        return sausage;
    }

    public void setSausage(Sausage s) {
        sausage = s;
    }

    public Keilbas getKeilbas() {
        return keilbas;
    }

    public void setKeilbas(Keilbas k) {
        keilbas = k;
    }

    public Meat getMeat() {
        return meat;
    }

    public void setMeat(Meat m) {
        meat = m;
    }

    public enum Size { // only 1 , 2 , 4  values
        onePerson,
        twoPerson,
        fourPerson
    }

    public enum Dough {      // only 1 , 2 , 3  values according to thin , medium , thick
        dough_thin,
        dough_medium,
        dough_thick
    }

    public enum Sausage { // as Default 1 Sausage per pizza
        SAUSAGE1,
        SAUSAGE2,
        SAUSAGE3
    }

    public enum Keilbas { // as Default 2 Keilbas per pizza
        KEILBAS1,
        KEILBAS2,
        KEILBAS3
    }

    public enum Meat { // as Default 1 Meat per pizza
        MEAT1,
        MEAT2,
        MEAT3
    }

    public enum Cheese {
        CHEESE1,
        CHEESE2,
        CHEESE3
    }

    public class Flavors {
        boolean pepper = false;
        boolean mushroom = false;
        boolean onion = false;
        boolean corn = false;
        boolean olive = false;
    }

}
