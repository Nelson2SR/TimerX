package com.surong.user.timerx;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 2016/1/31.
 */
public class Fragment1 extends Fragment {

    private EditText editText;
    private Button enter;
    onDataInputListener nameCallBack;

    public interface NameTransfer{
        public void nameTransfer(String name);
    }
    private NameTransfer nCallBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);
        init(view);
        return view;
    }

    public  void setResultListener(NameTransfer listener){
        nCallBack = listener;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            nameCallBack = (onDataInputListener) getActivity();
         //   nCallBack = (NameTransfer) getActivity();
        } catch (Exception e) {
            throw new ClassCastException(activity.toString() + "must implement onNameInput");
        }
    }


    public void init(View view){
        editText = (EditText) view.findViewById(R.id.userName);
        enter = (Button) view.findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                nameCallBack.onNameInput(name);
              //  nCallBack.nameTransfer(name);
            }
        });
    }
}