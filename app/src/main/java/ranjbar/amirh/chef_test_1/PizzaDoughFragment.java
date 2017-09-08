package ranjbar.amirh.chef_test_1;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import static android.content.ContentValues.TAG;

/**
 * Created by amirh on 11/08/17.
 */

public class PizzaDoughFragment extends Fragment {

    //pizza level 1 order
    public final static int fragmentLevel = 1;
    public final static int totalFragmentStepLevel = 2;
    private int activeLevel = 0;

    private int pizzaSize = 1;

    private Pizza.Dough dough;
    private Pizza.Size size;

    onDoughSet doughSet;

    private ImageView pizzaDough;
    private int pizzaDoughHeight;
    private int pizzaDoughWidth;
    private static int heightIncreaseValue = 100; // 50dp
    private static int widthIncreaseValue = 100; // 50dp increase level

    private ImageView arrowUp;
    private ImageView arrowDown;
    private ImageView dough_thin;
    private ImageView dough_medium;
    private ImageView dough_thick;
    private ScaleGestureDetector SGD;
    private float scale = 1f;

    private boolean pinchEnableOnTouch = true; // must be true to be enable for first time
    private boolean doughPinchEnable = true; // must be true to be enable for first time

    public interface onDoughSet {
        void doughSizeChanged(int level , Pizza.Size size);
        void doughTypeChanged(int level , Pizza.Dough dough);
//        void doughShapeChanged(int level);
        void doughFragmentIsSet(int level);
        void backToMenusFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        doughSet = (onDoughSet) getActivity().getSupportFragmentManager().findFragmentById(R.id.orderTable);
        Log.d(TAG , " on onAttach : ");
    }
    @Override
    public void onDetach() {
        super.onDetach();
        doughSet = null;
        Log.d(TAG , " on onDetach : ");

    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG , " on onPause : ");

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG , " on onResume : ");

        if(dough!=null && size!=null)
            loadPreviousState();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pizza_dough_fragment, container, false);

        Log.d(TAG , " on onCreateView : pizzaSize : " + pizzaSize);
        Log.d(TAG , " on onCreateView : doughPinchEnable : " + doughPinchEnable);

        pizzaDough = (ImageView) view.findViewById(R.id.pizzaDoughFragment_dough);
        arrowUp = (ImageView) view.findViewById(R.id.arrow_up);
        arrowDown = (ImageView) view.findViewById(R.id.arrow_down);
        dough_thin = (ImageView) view.findViewById(R.id.dough_thin);
        dough_medium = (ImageView) view.findViewById(R.id.dough_medium);
        dough_thick = (ImageView) view.findViewById(R.id.dough_thick);

        pizzaDoughHeight = pizzaDough.getLayoutParams().height;
        pizzaDoughWidth = pizzaDough.getLayoutParams().width;

        dough_thin.setOnClickListener(selectDoughViewListener);
        dough_medium.setOnClickListener(selectDoughViewListener);
        dough_thick.setOnClickListener(selectDoughViewListener);

        pizzaDough.setOnTouchListener(doughTouchListener);

        setImageViewsPosition();

        SGD = new ScaleGestureDetector(getContext(), new ScaleListener());

        return view;
    }

    public View.OnTouchListener doughTouchListener = new View.OnTouchListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            SGD.onTouchEvent(motionEvent);

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    break;
                case MotionEvent.ACTION_UP:
                    pinchEnableOnTouch =true;

                    break;
                case MotionEvent.ACTION_MOVE:

                    break;
            }
            return true;
        }
    };

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {

            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));

            if(pinchEnableOnTouch && doughPinchEnable) {
                pinchEnableOnTouch = false;  // become enable when user hand off  -> motionEvent.ACTION_UP

                if (pizzaSize < Pizza.maxDoughSize && scale > 1) {
                    pizzaDough.setLayoutParams(new LinearLayout.LayoutParams(
                            pizzaDough.getLayoutParams().height + heightIncreaseValue ,
                            pizzaDough.getLayoutParams().width + heightIncreaseValue
                    ));
                    pizzaSize++;
                }
                else if (pizzaSize > 1 && scale < 1){
                    pizzaDough.setLayoutParams(new LinearLayout.LayoutParams(
                            pizzaDough.getLayoutParams().height - heightIncreaseValue ,
                            pizzaDough.getLayoutParams().width - heightIncreaseValue
                    ));
                    pizzaSize--;
                }
                else {
                    pizzaDough.setLayoutParams(new LinearLayout.LayoutParams(
                            pizzaDoughHeight ,
                            pizzaDoughWidth
                    ));
                    pizzaSize = 1;
                }
                //dough size is changed
                switch (pizzaSize){
                    case 1:
                        doughSet.doughSizeChanged(fragmentLevel , Pizza.Size.onePerson);
                        break;
                    case 2:
                        doughSet.doughSizeChanged(fragmentLevel , Pizza.Size.twoPerson);
                        break;

                    case 3:
                        doughSet.doughSizeChanged(fragmentLevel , Pizza.Size.fourPerson);
                        break;
                }
            }
            Log.d(TAG , " onScale  listener , scale : " + scale);
            Log.d(TAG , " onScale  listener , PizzaSize : " + pizzaSize);
            Log.d(TAG , " onScale  listener , doughPinchEnable : " + doughPinchEnable);

            arrowDown.setVisibility(View.INVISIBLE);
            arrowDown.setEnabled(false);
            arrowUp.setVisibility(View.INVISIBLE);
            arrowUp.setEnabled(false);

            return true;
        }
    }

    View.OnClickListener selectDoughViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.dough_thin:
                    pizzaDough.setImageResource(R.drawable.dough_thin);

                    doughSet.doughTypeChanged(fragmentLevel , Pizza.Dough.dough_thin);
                    break;
                case R.id.dough_medium:
                    pizzaDough.setImageResource(R.drawable.dough_medium);

                    doughSet.doughTypeChanged(fragmentLevel , Pizza.Dough.dough_medium);
                    break;
                case R.id.dough_thick:
                    pizzaDough.setImageResource(R.drawable.dough_thick);

                    doughSet.doughTypeChanged(fragmentLevel , Pizza.Dough.dough_thick);
                    break;
            }
        }
    };

    private void setImageViewsPosition(){

        //set dough position
        pizzaDough.setX(pizzaDough.getX() - arrowUp.getLayoutParams().width / 2);
        //set arrowDown position
        arrowDown.setY(arrowDown.getY() + pizzaDough.getLayoutParams().height - arrowDown.getLayoutParams().height);
        arrowDown.setX(arrowDown.getX() - arrowDown.getLayoutParams().width);
        //set other doughs positions
        dough_thin.setY(dough_thin.getY() + pizzaDough.getLayoutParams().height / 2 - arrowDown.getLayoutParams().height);
        dough_thin.setX(dough_thin.getX() - dough_thin.getLayoutParams().width * 2);
        dough_medium.setY(dough_medium.getY() + pizzaDough.getLayoutParams().height / 2);
        dough_thick.setY(dough_thick.getY() + pizzaDough.getLayoutParams().height / 2 + arrowDown.getLayoutParams().height);


        Log.d(TAG, "sizzeeeeeeeeeee pizza dough , height :  " + pizzaDough.getLayoutParams().height);
        Log.d(TAG, "sizeeeeeeeeeeee pizza dough , width :  " + pizzaDough.getLayoutParams().width);

        Log.d(TAG, "sizzeeeeeeeeeee arrowUP , height :  " + arrowUp.getLayoutParams().height);
        Log.d(TAG, "sizeeeeeeeeeeee arrowUp, width :  " + arrowUp.getLayoutParams().width);

    }

    public void nextLevel(){

        switch (activeLevel){
            case 0:
                doughPinchEnable = false;

                dough_thick.setVisibility(View.VISIBLE);
                dough_medium.setVisibility(View.VISIBLE);
                dough_thin.setVisibility(View.VISIBLE);

                break;
            case 1:

                doughSet.doughFragmentIsSet(fragmentLevel);

                break;
//            case 2: for pizza dough shape -> rectangle or cycle
//
//                break;
        }

        activeLevel++;

        Log.d(TAG , " nextLevelllll , stepLevel++ : " + activeLevel);
        Log.d(TAG , " nextLevelllll , fragmentLevel : " + fragmentLevel);

//        if( stepLevel > totalFragmentStepLevel )
    }

    public void backLevel(){

        switch (activeLevel) {
            case 0:
                doughSet.backToMenusFragment();
                break;
            case 1:
                doughPinchEnable = true;

                dough_thick.setVisibility(View.INVISIBLE);
                dough_medium.setVisibility(View.INVISIBLE);
                dough_thin.setVisibility(View.INVISIBLE);
                activeLevel--;
                break;

            case 2:
                doughPinchEnable = false;

                dough_thick.setVisibility(View.VISIBLE);
                dough_medium.setVisibility(View.VISIBLE);
                dough_thin.setVisibility(View.VISIBLE);
                activeLevel--;
                break;

//            case 3:  for pizza dough shape
//
//                break;
        }


        Log.d(TAG , " backLevelllll , stepLevel-- : " + activeLevel);

//        if(stepLevel<0)
    }

    public void loadPreviousState(){

        Log.d(TAG , " loadPreviousStateeeeeeeeeee , Pizza.Dough : " + dough);
        Log.d(TAG , " loadPreviousStateeeeeeeeeee , Pizza.Size : " + size);

        switch (dough){

            case dough_thin: // Pizza.Dough.dough_thin
                pizzaDough.setImageResource(R.drawable.dough_thin);
//                doughSet.doughTypeChanged(fragmentLevel , Pizza.Dough.dough_thin);
                break;

            case dough_medium:
                pizzaDough.setImageResource(R.drawable.dough_medium);
//                doughSet.doughTypeChanged(fragmentLevel , Pizza.Dough.dough_medium);
                break;

            case dough_thick:
                pizzaDough.setImageResource(R.drawable.dough_thick);
//                doughSet.doughTypeChanged(fragmentLevel , Pizza.Dough.dough_thick);
                break;
        }

        switch (size){

            case onePerson:
                pizzaDough.setLayoutParams(new LinearLayout.LayoutParams(
                        pizzaDoughHeight ,
                        pizzaDoughWidth
                ));
                pizzaSize = 1;
                break;

            case twoPerson:
                pizzaDough.setLayoutParams(new LinearLayout.LayoutParams(
                        pizzaDoughHeight + 1*heightIncreaseValue ,
                        pizzaDoughWidth + 1*widthIncreaseValue
                ));
                pizzaSize = 2;
                break;

            case fourPerson:
                pizzaDough.setLayoutParams(new LinearLayout.LayoutParams(
                        pizzaDoughHeight + 2*heightIncreaseValue ,
                        pizzaDoughWidth + 2*widthIncreaseValue
                ));
                pizzaSize = 3;
        }

        doughPinchEnable = false;

        dough_thick.setVisibility(View.VISIBLE);
        dough_medium.setVisibility(View.VISIBLE);
        dough_thin.setVisibility(View.VISIBLE);

        activeLevel = 1;

        arrowDown.setVisibility(View.INVISIBLE);
        arrowDown.setEnabled(false);
        arrowUp.setVisibility(View.INVISIBLE);
        arrowUp.setEnabled(false);

        Log.d(TAG , " loadPreviousStateeeeeeeeeee PizzaDoughFragment , stepLevel : " + activeLevel);
        Log.d(TAG , " loadPreviousStateeeeeeeeeee PizzaDoughFragment , doughPinchEnable : " + doughPinchEnable);

    }

    public void setPreviousState(Pizza.Dough d , Pizza.Size s){

        dough = d;
        size = s;
    }
}