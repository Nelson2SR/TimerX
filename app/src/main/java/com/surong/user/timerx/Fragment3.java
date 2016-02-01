package com.surong.user.timerx;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by user on 2016/1/31.
 */
public class Fragment3 extends Fragment{

    private TextView nameConfirm;
    private Button stopButton;
    private ToggleButton toggleButton;
    private TextView timeValue;
    private Handler customHandler = new Handler();

    int time;
    int oriTime;
    String userName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3,container,false);
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            time = (int) (args.getInt("inputTime")/1000);
            oriTime = time;
        }else {
            time = 10;
            oriTime = time;
        }
        timeValue = (TextView) view.findViewById(R.id.timeValue);
        stopButton = (Button) view.findViewById(R.id.stopButton);
        toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
        nameConfirm = (TextView) view.findViewById(R.id.name3);
        nameConfirm.setText("test");


//        Fragment1 fragment1 = new Fragment1();
//        fragment1.setResultListener(new Fragment1.NameTransfer() {
//            @Override
//            public void nameTransfer(String name) {
//                nameConfirm = (TextView) getView().findViewById(R.id.name3);
//                nameConfirm.setText(name);
//            }
//        });
        //initial the clock
        init();
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customHandler.removeCallbacks(updatedTimerThread);
                time = oriTime;
                int hours = (time/3600);
                int mins =  ((time%3600)/60);
                int secs =  (time%60);
                timeValue.setText(""+String.format("%02d",hours) + ":"
                        + String.format("%02d",mins) + ":"
                        + String.format("%02d",secs));
                toggleButton.setChecked(true);
            }
        });
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    customHandler.removeCallbacks(updatedTimerThread);
                }else {
                    customHandler.postDelayed(updatedTimerThread,1000);
                }
            }
        });
        return view;
    }

    public void init(){
        if(time>0){
            int hours = (time/3600);
            int mins =  ((time%3600)/60);
            int secs =  (time%60);
            timeValue.setText(""+String.format("%02d",hours) + ":"
                    + String.format("%02d",mins) + ":"
                    + String.format("%02d",secs));
        }
    }


    private Runnable updatedTimerThread = new Runnable() {
        @Override
        public void run() {

            time--;
            if(time>0){
                int hours = (time/3600);
                int mins =  ((time%3600)/60);
                int secs =  (time%60);
                timeValue.setText(""+String.format("%02d",hours) + ":"
                        + String.format("%02d",mins) + ":"
                        + String.format("%02d",secs));
                customHandler.postDelayed(this,1000);
            }
            if(time == 0){
                try {
                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getActivity(), notification);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Fragment4 fragment4 = new Fragment4();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_container,fragment4);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
    };

}
