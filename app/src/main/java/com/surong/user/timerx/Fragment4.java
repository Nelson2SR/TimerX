package com.surong.user.timerx;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by user on 2016/1/31.
 */
public class Fragment4 extends Fragment {

    private Button tryAgain;
    private Button quit;
    private TextView name;

    String userName;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4,container,false);
        name = (TextView) view.findViewById(R.id.name4);
        name.setText("test");
        tryAgain = (Button) view.findViewById(R.id.tryAgain);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment2 fragment2 = new Fragment2();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container,fragment2);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        quit = (Button) view.findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBox dialogBox = new DialogBox();
                dialogBox.show(getFragmentManager(),"QUIT");

            }
        });
        return view;
    }
}
