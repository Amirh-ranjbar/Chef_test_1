package ranjbar.amirh.chef_test_1;

/**
 * Created by amirh on 01/09/17.
 */

public class Pizza {
    //pizza attr

    static int maxDoughSize = 3; // for 1 , 2 , 4 person

    public enum Size { // only 1 , 2 , 4  values
        onePerson,
        twoPerson,
        fourPerson
    }

    Size size;

    public enum Dough{      // only 1 , 2 , 3  values according to thin , medium , thick
        dough_thin,
        dough_medium,
        dough_thick
    }

    Dough dough;

}
