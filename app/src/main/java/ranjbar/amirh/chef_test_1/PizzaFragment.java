package ranjbar.amirh.chef_test_1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ranjbar.amirh.chef_test_1.pizza.Pizza;
import ranjbar.amirh.chef_test_1.pizza.PizzaCheeseFragment;
import ranjbar.amirh.chef_test_1.pizza.PizzaDoughFragment;
import ranjbar.amirh.chef_test_1.pizza.PizzaFlavorsFragment;
import ranjbar.amirh.chef_test_1.pizza.PizzaMaterialsFragment;

import static android.content.ContentValues.TAG;

/**
 * Created by amirh on 10/08/17.
 */

public class PizzaFragment extends Fragment
        implements PizzaDoughFragment.pizzaDoughFragmentListener,
        PizzaMaterialsFragment.pizzaMaterialFragmentListener,
        PizzaCheeseFragment.pizzaCheeseFragmentListener,
        PizzaFlavorsFragment.pizzaFlavorsFragmentListener {

    PizzaDoughFragment pizzaDoughFragment ;
    PizzaMaterialsFragment pizzaMaterialsFragment;
    PizzaFlavorsFragment pizzaFlavorsFragment;
    PizzaCheeseFragment pizzaCheeseFragment;
    Pizza pizza;
    FragmentTransaction transaction;
    PizzaFragmentListener pizzaFragmentListener;
    private int activeFragmentLevel = 1;
    private ImageView nextButton;
    private ImageView backButton;
    private ImageView level1;
    private ImageView level2;
    private ImageView level3;
    private ImageView level4;
    private View.OnClickListener nextButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nextButton.setVisibility(View.INVISIBLE);

            Log.d(TAG, " on nextButtonClickListener : pizza.size : " + pizza.getSize());
            Log.d(TAG, " on nextButtonClickListener : pizza.dough : " + pizza.getDough());
            Log.d(TAG, " on nextButtonClickListener : activeFragmentLevel : " + activeFragmentLevel);

            switch (activeFragmentLevel) {
                case PizzaDoughFragment.fragmentLevel:
                    pizzaDoughFragment.setToNextLevel();
                    break;
                case PizzaMaterialsFragment.fragmentLevel:
                    pizzaMaterialsFragment.setToNextLevel();
                    break;

                case PizzaFlavorsFragment.fragmentLevel:
                    pizzaFlavorsFragment.setToNextLevel();
            }
        }
    };
    private View.OnClickListener backButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            nextButton.setVisibility(View.VISIBLE);

            Log.d(TAG, " on backButtonClickListener : pizza.size : " + pizza.getSize());
            Log.d(TAG, " on backButtonClickListener : pizza.dough : " + pizza.getDough());
            Log.d(TAG, " on backButtonClickListener : activeFragmentLevel : " + activeFragmentLevel);

            switch (activeFragmentLevel) {
                case PizzaDoughFragment.fragmentLevel:
                    pizzaDoughFragment.setToBackLevel();
                    break;

                case PizzaMaterialsFragment.fragmentLevel:
                    commitPizzaDoughFragment();
                    break;

                case PizzaFlavorsFragment.fragmentLevel:
                    commitPizzaMaterialsFragment();
                    break;

                case PizzaCheeseFragment.fragmentLevel:
                    commitPizzaFlavorsFragment();
                    break;
            }

        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        pizzaFragmentListener = (PizzaFragmentListener) context ;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        pizzaFragmentListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pizza_fragment ,container , false );

        nextButton = (ImageView) view.findViewById(R.id.nextButton) ;
        nextButton.setVisibility(View.INVISIBLE);
        backButton = (ImageView) view.findViewById(R.id.backButton) ;
        level1 = (ImageView) view.findViewById(R.id.pizzaLevel1) ;
        level2 = (ImageView) view.findViewById(R.id.pizzaLevel2) ;
        level3 = (ImageView) view.findViewById(R.id.pizzaLevel3) ;
        level4 = (ImageView) view.findViewById(R.id.pizzaLevel4) ;

        nextButton.setOnClickListener(nextButtonClickListener);
        backButton.setOnClickListener(backButtonClickListener);

        pizzaDoughFragment = new PizzaDoughFragment();
        pizzaMaterialsFragment = new PizzaMaterialsFragment();
        pizzaFlavorsFragment = new PizzaFlavorsFragment();
        pizzaCheeseFragment = new PizzaCheeseFragment();

        pizza = new Pizza();

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.pizza_fragment , pizzaDoughFragment);
        transaction.commit();


        return view;
    }

    private void commitPizzaDoughFragment() {

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.pizza_fragment, pizzaDoughFragment);
        transaction.commit();

        activeFragmentLevel = PizzaDoughFragment.fragmentLevel;

        if (pizza != null) {
            pizzaDoughFragment.setPreviousState(pizza);
        }
    }

    private void commitPizzaMaterialsFragment() {

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.pizza_fragment, pizzaMaterialsFragment);
        transaction.commit();

        activeFragmentLevel = PizzaMaterialsFragment.fragmentLevel;

        if (pizza != null) {
            pizzaMaterialsFragment.setPerviousState(pizza);

        }
    }

    private void commitPizzaFlavorsFragment() {

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.pizza_fragment, pizzaFlavorsFragment);
        transaction.commit();

        activeFragmentLevel = PizzaFlavorsFragment.fragmentLevel;

        if (pizza != null) {
            pizzaFlavorsFragment.setPerviousState(pizza);

        }
    }

    private void commitPizzaCheeseFragment() {

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.pizza_fragment, pizzaCheeseFragment);
        transaction.commit();

        activeFragmentLevel = PizzaCheeseFragment.fragmentLevel;

        if (pizza != null) {
            pizzaCheeseFragment.setPerviousState(pizza);

        }
    }

    @Override
    public void doughSizeChanged(int level , Pizza.Size size) {
        nextButton.setVisibility(View.VISIBLE);

        pizza.setSize(size);

        activeFragmentLevel = level;

        Log.d(TAG, " on doughSizeChanged : pizza.size : " + pizza.getSize());
        Log.d(TAG , " on doughSizeChanged : activeFragmentLevel : " + activeFragmentLevel);
    }

    @Override
    public void doughTypeChanged(int level , Pizza.Dough dough) {
        nextButton.setVisibility(View.VISIBLE);

        pizza.setDough(dough);

        activeFragmentLevel = level;

        Log.d(TAG, " on doughTypeChanged : pizza.dough : " + pizza.getDough());
        Log.d(TAG , " on doughTypeChanged : activeFragmentLevel : " + activeFragmentLevel);

    }

    @Override
    public void onDoughFragmentIsSet(int level) {
        //Starting Material Fragment : level = 2
        commitPizzaMaterialsFragment();

        Log.d(TAG, " on doughFragmentIsSet : activeFragmentLevel : " + activeFragmentLevel);
    }

    @Override
    public void backToMenusFragment() {
//        activeFragmentLevel = 0;
        pizzaFragmentListener.backToMenusFragment();
    }

    @Override
    public void onMaterialIsChoose(int level, Pizza p) {
        nextButton.setVisibility(View.VISIBLE);

        activeFragmentLevel = level;

        pizza.setMeat(p.getMeat());
        pizza.setSausage(p.getSausage());
        pizza.setKeilbas(p.getKeilbas());

        Log.d(TAG, " on doughSizeChanged : activeFragmentLevel : " + activeFragmentLevel);
    }

    @Override
    public void onMaterialFragmentIsSet(int level) {

        commitPizzaFlavorsFragment();
        Log.d(TAG, " on onMaterialFragmentIsSet : activeFragmentLevel : " + activeFragmentLevel);
    }

    @Override
    public void onFlavorsIsChoose(int level, Pizza p) {
        nextButton.setVisibility(View.VISIBLE);

        activeFragmentLevel = level;

        pizza.setFlavors(p.getFlavors());

        Log.d(TAG, " on doughSizeChanged : activeFragmentLevel : " + activeFragmentLevel);
    }

    @Override
    public void onFlavorsFragmentIsSet(int level) {

        commitPizzaCheeseFragment();
        Log.d(TAG, " on onFlavorsFragmentIsSet : activeFragmentLevel : " + activeFragmentLevel);
    }

    @Override
    public void onCheeseIsChoose(int level, Pizza p) {
        nextButton.setVisibility(View.VISIBLE);

        activeFragmentLevel = level;

        pizza.setCheese(p.getCheese());

        Log.d(TAG, " on doughSizeChanged : activeFragmentLevel : " + activeFragmentLevel);
    }

    @Override
    public void onCheeseFragmentIsSet(int level) {

    }

    public interface PizzaFragmentListener {
        void backToMenusFragment();
    }

}
