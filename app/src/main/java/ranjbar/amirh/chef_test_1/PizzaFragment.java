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

import static android.content.ContentValues.TAG;

/**
 * Created by amirh on 10/08/17.
 */

public class PizzaFragment extends Fragment
implements PizzaDoughFragment.onDoughSet{

    private int activeFragmentLevel;

    PizzaDoughFragment pizzaDoughFragment ;
    PizzaMaterialsFragment pizzaMaterialsFragment;

    Pizza pizza;

    FragmentTransaction transaction;
    PizzaFragmentListener pizzaFragmentListener;

    private ImageView nextButton;
    private ImageView backButton;
    private ImageView level1;
    private ImageView level2;
    private ImageView level3;
    private ImageView level4;

    public interface PizzaFragmentListener{
        void backToMenusFragment();
    }

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

        pizzaMaterialsFragment = new PizzaMaterialsFragment();
        pizzaDoughFragment = new PizzaDoughFragment();

        pizza = new Pizza();

        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.pizza_fragment , pizzaDoughFragment);
        transaction.commit();

        return view;
    }

    private View.OnClickListener nextButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nextButton.setVisibility(View.INVISIBLE);

            Log.d(TAG , " on nextButtonClickListener : pizza.size : " + pizza.size);
            Log.d(TAG , " on nextButtonClickListener : pizza.dough : " + pizza.dough);
            Log.d(TAG , " on nextButtonClickListener : activeFragmentLevel : " + activeFragmentLevel);

            switch (activeFragmentLevel){
                case PizzaDoughFragment.fragmentLevel:
                    pizzaDoughFragment.nextLevel();
                    break;
            }
        }
    };

    private View.OnClickListener backButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            nextButton.setVisibility(View.VISIBLE);

            Log.d(TAG , " on backButtonClickListener : pizza.size : " + pizza.size);
            Log.d(TAG , " on backButtonClickListener : pizza.dough : " + pizza.dough);
            Log.d(TAG , " on backButtonClickListener : activeFragmentLevel : " + activeFragmentLevel);

            switch (activeFragmentLevel){
                case PizzaDoughFragment.fragmentLevel:
                    pizzaDoughFragment.backLevel();
                    break;

                case PizzaMaterialsFragment.fragmentLevel:

                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.pizza_fragment , pizzaDoughFragment);
                    transaction.commit();

                    pizzaDoughFragment.setPreviousState(pizza.dough , pizza.size);

                    activeFragmentLevel = PizzaDoughFragment.fragmentLevel;

                    break;
//                default:
//                    //back to menusFragment
//
//                    break;
            }

        }
    };

    @Override
    public void doughSizeChanged(int level , Pizza.Size size) {
        nextButton.setVisibility(View.VISIBLE);

        pizza.size = size;

        activeFragmentLevel = level;

        Log.d(TAG , " on doughSizeChanged : pizza.size : " + pizza.size);
        Log.d(TAG , " on doughSizeChanged : activeFragmentLevel : " + activeFragmentLevel);
    }

    @Override
    public void doughTypeChanged(int level , Pizza.Dough dough) {
        nextButton.setVisibility(View.VISIBLE);

        pizza.dough = dough;

        activeFragmentLevel = level;

        Log.d(TAG , " on doughTypeChanged : pizza.dough : " + pizza.dough);
        Log.d(TAG , " on doughTypeChanged : activeFragmentLevel : " + activeFragmentLevel);

    }

    @Override
    public void doughFragmentIsSet(int level) {

        //Starting Material Fragment : level = 2
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.pizza_fragment , pizzaMaterialsFragment);
        transaction.commit();

        activeFragmentLevel = level + 1;
        Log.d(TAG , " on doughFragmentIsSet : activeFragmentLevel : " + activeFragmentLevel);

    }

    @Override
    public void backToMenusFragment() {

//        activeFragmentLevel = 0;
        pizzaFragmentListener.backToMenusFragment();
    }
}
