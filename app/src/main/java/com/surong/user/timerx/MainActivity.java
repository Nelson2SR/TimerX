package com.surong.user.timerx;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


public class MainActivity extends FragmentActivity implements onDataInputListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add the first fragment
        if (findViewById(R.id.frame_container) != null){
            if (savedInstanceState != null) {
                return;
            }
        }
        Fragment1 fragment1 = new Fragment1();
        fragment1.setArguments(getIntent().getExtras());
        getFragmentManager().beginTransaction()
                .add(R.id.frame_container,fragment1,"fragment1")
                .addToBackStack("fragment1").commit();
    }

    public void onNameInput (String userName){
        //turn to Fragment2
        Fragment2 fragment2 = new Fragment2();
        Bundle bundle2 = new Bundle();
        bundle2.putString("Name",userName);
        fragment2.setArguments(bundle2);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment2,"fragment2");
        transaction.addToBackStack("fragment");
        transaction.commit();
    }

    public void onTimeInput (int Time) {
        //turn to Fragment3
        Fragment3 fragment3 = new Fragment3();
        Bundle args = new Bundle();
        args.putInt("inputTime",Time);
        fragment3.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment3,"fragment3");
        transaction.addToBackStack("fragment3");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
