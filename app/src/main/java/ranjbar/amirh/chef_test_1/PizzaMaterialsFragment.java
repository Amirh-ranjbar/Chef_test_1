package ranjbar.amirh.chef_test_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.magiepooh.recycleritemdecoration.ItemDecorations;

import static android.content.ContentValues.TAG;


/**
 * Created by amirh on 04/09/17.
 */

public class PizzaMaterialsFragment extends Fragment {

    public final static int fragmentLevel = 2;
    public final static int totalFragmentStepLevel = 1;
    private int stepLevel = 0;

    private ImageView pizzaDough;
//    private ImageView keilbasa;
    private ImageView sausage;
    private ImageView meet;

    private RecyclerView keilbasaRecyclerView;
    private RecyclerView sausageRecyclerView;
    private RecyclerView meetRecyclerView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pizza_materials_fragment, container, false);

        pizzaDough = (ImageView) view.findViewById(R.id.pizzaMaterialFragment_dough);
//        keilbasa = (ImageView) view.findViewById(R.id.keilbasa);

        sausage = (ImageView) view.findViewById(R.id.sausage);
        meet = (ImageView) view.findViewById(R.id.meet);

        RecyclerView.ItemDecoration decoration = ItemDecorations.vertical(getContext()).create();

        keilbasaRecyclerView = (RecyclerView) view.findViewById(R.id.keilbasaRecyclerView);
        keilbasaRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false));
        keilbasaRecyclerView.addItemDecoration(decoration);

        setImageViewsPosition();

        return view;
    }


    private void setImageViewsPosition(){

        //set dough position
        pizzaDough.setX(pizzaDough.getX());
        //set other doughs positions
//        keilbasa.setY(keilbasa.getY() + pizzaDough.getLayoutParams().height/2
//                - keilbasa.getLayoutParams().height*3/2 );
//        keilbasa.setX(keilbasa.getX() + keilbasa.getLayoutParams().width );

        sausage.setY(sausage.getY() + pizzaDough.getLayoutParams().height / 2 - sausage.getLayoutParams().height/3);

        meet.setY(meet.getY() + pizzaDough.getLayoutParams().height / 2 + meet.getLayoutParams().height);
        meet.setX(meet.getX() - meet.getLayoutParams().width);

        Log.d(TAG, "PizzaMaterialsFragment pizza dough , height :  " + pizzaDough.getLayoutParams().height);
        Log.d(TAG, "PizzaMaterialsFragment pizza dough , width :  " + pizzaDough.getLayoutParams().width);

    }

}
