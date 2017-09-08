package ranjbar.amirh.chef_test_1;

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
 * Created by amirh on 07/08/17.
 */

public class MenusFragment extends Fragment {

    private ImageView ready_menu;
    private ImageView create_food_menu;
    private ImageView pizzaImageView;
    private ImageView sandwichImageView;
    private ImageView saladImageView;
    private ImageView friedChickenImageView;
    private ImageView otherImageView;

    PizzaFragment pizzaFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.menus_fragment, container , false);

        ready_menu = (ImageView) view.findViewById(R.id.ready_menu_imageview);
        create_food_menu = (ImageView) view.findViewById(R.id.create_food_imageview);
        pizzaImageView = (ImageView) view.findViewById(R.id.pizza_imageview);
        sandwichImageView = (ImageView) view.findViewById(R.id.sandwich_imageview);
        saladImageView = (ImageView) view.findViewById(R.id.salad_imageview);
        friedChickenImageView = (ImageView) view.findViewById(R.id.fried_chicken_imageview);
        otherImageView = (ImageView) view.findViewById(R.id.other_imageview);

        ready_menu.setOnClickListener(clickListener);
        create_food_menu.setOnClickListener(clickListener);
        pizzaImageView.setOnClickListener(clickListener);
        sandwichImageView.setOnClickListener(clickListener);
        saladImageView.setOnClickListener(clickListener);
        friedChickenImageView.setOnClickListener(clickListener);
        otherImageView.setOnClickListener(clickListener);

        return view;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.create_food_imageview:
                    ready_menu.setVisibility(View.INVISIBLE);
                    create_food_menu.setVisibility(View.INVISIBLE);

                    pizzaImageView.setVisibility(View.VISIBLE);
                    sandwichImageView.setVisibility(View.VISIBLE);
                    saladImageView.setVisibility(View.VISIBLE);
                    friedChickenImageView.setVisibility(View.VISIBLE);
                    otherImageView.setVisibility(View.VISIBLE);
                    break;
                case R.id.ready_menu_imageview:
                    //ready menu Fragment

                    break;
                case R.id.pizza_imageview:
                    //pizza Fragment
                    Log.d(TAG , "pizza mikhadddddddddddddddd");

                    pizzaFragment = new PizzaFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.orderTable , pizzaFragment);
                    transaction.commit();

                    break;
                case R.id.sandwich_imageview:
                    //sandwich fragment
                    break;
            }
        }
    };
}
