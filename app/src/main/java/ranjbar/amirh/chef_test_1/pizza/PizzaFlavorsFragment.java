package ranjbar.amirh.chef_test_1.pizza;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.magiepooh.recycleritemdecoration.ItemDecorations;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import ranjbar.amirh.chef_test_1.R;
import ranjbar.amirh.chef_test_1.adapters.RecyclerViewAdapter;

import static android.content.ContentValues.TAG;

/**
 * Created by amirh on 21/09/17.
 */

public class PizzaFlavorsFragment extends Fragment
        implements RecyclerViewAdapter.onViewHolderListener {

    public final static int fragmentLevel = 3;
    public final static int totalFragmentStepLevel = 1;
    private static int heightIncreaseValue = 100; // 50dp

    //    private Pizza.Dough dough;
//    private Pizza.Size size;
    private static int widthIncreaseValue = 100; // 50dp increase level
    //In  jp.wasabeef.recyclerview.adapters Library
    AlphaInAnimationAdapter alphaAdapter;
    ScaleInAnimationAdapter scaleAdapter;
    //in com.github.magiepooh.recycleritemdecoration.ItemDecorations Library
    RecyclerView.ItemDecoration decoration;
    //in adapters Directory
    RecyclerViewAdapter flavorsAdapter;
    pizzaFlavorsFragmentListener flavorsFragmentListener;
    private int stepLevel = 0;
    private Pizza pizza = new Pizza();
    private ImageView pizzaDough;
    private int pizzaDoughHeight;
    private int pizzaDoughWidth;
    private RecyclerView flavorsRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        flavorsFragmentListener = (pizzaFlavorsFragmentListener) getActivity()
                .getSupportFragmentManager().findFragmentById(R.id.orderTable);

        Log.d(TAG, " on onAttach : ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        flavorsFragmentListener = null;
        Log.d(TAG, " on onDetach : ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pizza_flavors_fragment, container, false);

        pizzaDough = (ImageView) view.findViewById(R.id.dough);
        pizzaDoughHeight = pizzaDough.getLayoutParams().height;
        pizzaDoughWidth = pizzaDough.getLayoutParams().width;

        decoration = ItemDecorations.horizontal(getContext())
//                .first(R.drawable.shape_seprator_dark_grey)
                .type(0, R.drawable.shape_seprator_dark_grey)
//                .last(R.drawable.shape_seprator_dark_grey)
                .create();

        flavorsRecyclerView = (RecyclerView) view.findViewById(R.id.flavorsRecyclerView);

        flavorsRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        flavorsAdapter = new RecyclerViewAdapter(this, getFlavorsDrawablesArrayList());
        alphaAdapter = new AlphaInAnimationAdapter(flavorsAdapter);
        alphaAdapter.setDuration(10000);
        alphaAdapter.setInterpolator(new OvershootInterpolator());
        alphaAdapter.setFirstOnly(false);
        scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        scaleAdapter.setFirstOnly(false);
        scaleAdapter.setDuration(1000);
        flavorsRecyclerView.setAdapter(scaleAdapter);

        flavorsRecyclerView.addItemDecoration(decoration);

        loadPreviousState();

        return view;
    }

    //RecyclerView Constructor feed
    public ArrayList<Integer> getFlavorsDrawablesArrayList() {

        ArrayList<Integer> drawablesList = new ArrayList<>();

        drawablesList.add(R.drawable.keilbas_1);
        drawablesList.add(R.drawable.sausage_1);
        drawablesList.add(R.drawable.steak_1);
        drawablesList.add(R.drawable.cheese_1);

        return drawablesList;
    }

    //set and load Dough state from PizzaDoughFragment
    private void loadPreviousState() {
        switch (pizza.getDough()) {
            case dough_thin: // Pizza.Dough.dough_thin
                pizzaDough.setImageResource(R.drawable.dough_thin);
                break;
            case dough_medium:
                pizzaDough.setImageResource(R.drawable.dough_medium);
                break;
            case dough_thick:
                pizzaDough.setImageResource(R.drawable.dough_thick);
                break;
        }
        switch (pizza.getSize()) {
            case onePerson:
                pizzaDough.setLayoutParams(new LinearLayout.LayoutParams(
                        pizzaDoughHeight,
                        pizzaDoughWidth
                ));
                break;
            case twoPerson:
                pizzaDough.setLayoutParams(new LinearLayout.LayoutParams(
                        pizzaDoughHeight + 1 * heightIncreaseValue,
                        pizzaDoughWidth + 1 * widthIncreaseValue
                ));
                break;
            case fourPerson:
                pizzaDough.setLayoutParams(new LinearLayout.LayoutParams(
                        pizzaDoughHeight + 2 * heightIncreaseValue,
                        pizzaDoughWidth + 2 * widthIncreaseValue
                ));
        }
        //// TODO: 21/09/17 loading chosen Flavors
    }

    public void setPerviousState(Pizza p) {

        pizza.setDough(p.getDough());
        pizza.setSize(p.getSize());
        //Todo set chosen Flavors
    }

    //RecyclerViewTouches handle
    //Setting Pizza Sausage , Keilbas and Meat
    @Override
    public void notifyImageViewIsTouched(@DrawableRes int resId) {
        Log.d(TAG, " notifyImageViewIsTouchedddddddddddd ::::: " + resId);

        //TODO Listening Flavors View
//        switch (resId){
//            case 0:
////                pizzaDough.setImageResource(R.drawable.pizza_menu);
//                break;
//            case 1:
//                pizzaDough.setImageResource(R.drawable.pizza_2_1_0);
//                break;
//            case 2:
////                pizzaDough.setImageResource(R.drawable.pizza_2_2_0);
//                break;
//            case 3:
//                pizzaDough.setImageResource(R.drawable.pizza_2_2_0);
//                break;
//        }

        //for TEST set some value for materials
        pizza.getFlavors().corn = true;
        pizza.getFlavors().mushroom = true;
        //TODO : Save Pizza Current Flavors and condition
        flavorsFragmentListener.onFlavorsIsChoose(fragmentLevel, pizza);// change fragmentLevel to next Level

    }

    public void setToNextLevel() {

        flavorsFragmentListener.onFlavorsFragmentIsSet(fragmentLevel + 1);// change fragmentLevel to next Level

    }

    public interface pizzaFlavorsFragmentListener {
        void onFlavorsIsChoose(int level, Pizza p);

        void onFlavorsFragmentIsSet(int level);
    }

    //ToDo : RecyclerView Items Motion -> for Animation
    private class recyclerViewItemTouchListener implements RecyclerView.OnItemTouchListener {

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            return true;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            if (MotionEvent.ACTION_UP == e.getAction()) {
                rv.setTop(rv.getTop() + 1);
                Log.d(TAG, "countttttttttt ::: " + rv.getTop());
                Log.d(TAG, "countttttttttt ::: " + rv.getChildCount());

            }
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
