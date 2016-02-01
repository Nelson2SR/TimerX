package com.surong.user.timerx;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 2016/1/31.
 */
public class Fragment2 extends Fragment {

    private TextView nameConfirm;
    private EditText time;
    private Button timer_start;
    final static String NAME = "Name";
    String receiveName;
    onDataInputListener timeCallBack;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2,container,false);
          //set time;
        setTimer_start(view);
        return view;
    }

    public void setTimer_start(View view){
        time = (EditText) view.findViewById(R.id.inputTime);
        timer_start = (Button) view.findViewById(R.id.star_timer);
        timer_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int inputTime = Integer.valueOf(time.getText().toString());
                timeCallBack.onTimeInput(inputTime);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateUserName(args.getString(NAME));
        } else if (receiveName != null) {
            // Set article based on saved instance state defined during onCreateView
            updateUserName(receiveName);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            timeCallBack = (onDataInputListener) getActivity();
        } catch (Exception e) {
            throw new ClassCastException(activity.toString() + "must implement onTimeInput");
        }
    }

    public void updateUserName(String name) {
        nameConfirm = (TextView) view.findViewById(R.id.name2);
        nameConfirm.setText(name);
        receiveName = name;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            receiveName = savedInstanceState.getString(NAME);
        }else {
            if (receiveName != null) {

            } else{
                receiveName = "Test";
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NAME,receiveName);
    }
}
