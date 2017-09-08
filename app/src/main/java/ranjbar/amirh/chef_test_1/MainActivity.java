package ranjbar.amirh.chef_test_1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity
implements PizzaFragment.PizzaFragmentListener{

    MenusFragment menusFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideSystemUI();
        setContentView(R.layout.activity_main);

         Log.d(TAG , "Screen sizeeeeeeeee  : " +
                 (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK )+
                 " == " + Configuration.SCREENLAYOUT_SIZE_NORMAL );

        HomeWatcher mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(homePressedListener);
        mHomeWatcher.startWatch();

        menusFragment = new MenusFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.orderTable , menusFragment);
        transaction.commit();
//        menusFragment = (MenusFragment) getSupportFragmentManager().findFragmentById(R.id.orderTable);

    }

    private HomeWatcher.OnHomePressedListener homePressedListener = new HomeWatcher.OnHomePressedListener() {
        @Override
        public void onHomePressed() {
            Log.d(TAG , " onHomePressedddddddddddddddddd");
            restartApp();
            hideSystemUI();
        }

        @Override
        public void onHomeLongPressed() {
            Log.d(TAG , " onHomeLongPressedddddddddddddddddd");
            restartApp();
            hideSystemUI();
        }
    };

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG , " onPostCreateeeeeeeeeeeee");

        hideSystemUI();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG , " onWindowFocusChangedddddddddddddd");

        hideSystemUI();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG , " onResumeeeeeeeeeeeeeeeee");

        hideSystemUI();
    }
    @Override
    public void onUserInteraction() {
//        super.onUserInteraction();
        Log.d(TAG , " onUserInteractionnnnnnnnnnnn");

        hideSystemUI();
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Log.d(TAG , " onBackPresseddddddddddddddd");

        hideSystemUI();
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

    Log.d(TAG , " onPostCreateeeeeeeeeeeee");

        if(keyCode == KeyEvent.KEYCODE_MENU){
            Log.d(TAG , " onMenuPressedddddddddddddddddd_onKeyUp");

            hideSystemUI();
            return true;
        }
//        else if( keyCode == KeyEvent.KEYCODE_APP_SWITCH)
        else if (keyCode == KeyEvent.KEYCODE_HOME){
            Log.d(TAG , " onHomePressedddddddddddddddddd_onKeyUp");
            hideSystemUI();
            return true;
        }
        else if(keyCode == KeyEvent.FLAG_VIRTUAL_HARD_KEY){
            hideSystemUI();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onPause() {
        super.onPause();
            Log.d(TAG , " onPostCreateeeeeeeeeeeee");
//        if (this.isFinishing()){
        hideSystemUI();
    }

    private void restartApp(){

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    // This snippet shows the system bars. It does this by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {

        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);

        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void backToMenusFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.orderTable , menusFragment);
        transaction.commit();
    }
}
