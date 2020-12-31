package com.cjluhz.curriculum.smartbrainfaizdriver.ui.delta;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.cjluhz.curriculum.smartbrainfaizdriver.R;
import com.cjluhz.curriculum.smartbrainfaizdriver.soundPlayer;

import java.util.ArrayList;
import java.util.List;

public class DeltaFragment extends Fragment {
    private final String title = "DELTA";
    private String command = "";
    private View root;
    private Button yes;
    private Button cancel;
    private TextView panel;
    private Context mContext;
    private List<Button> dialog = new ArrayList<>();
    private soundPlayer sp;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.fragment_delta, container,false);
        mContext = getContext();

        yes = root.findViewById(R.id.yes);
        cancel = root.findViewById(R.id.cancel);
        panel = root.findViewById(R.id.command);
        sp = new soundPlayer(mContext);
        initdialog();
        initConfirm();
        return root;
    }

    private class onCommandListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            sp.stop();
            Button thisb =  root.findViewById(view.getId());
            command += thisb.getText();
            if (panel.getText().toString().equals(title)){
                panel.setText("");
            }
            String tmp = panel.getText().toString() + thisb.getText().toString();
            panel.setText(tmp);
        }
    }

    private class onConfirmListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Button thisb =  root.findViewById(view.getId());
            if (thisb.getId() == R.id.yes){
                henshin();
            } else release();
            command = "";
            panel.setText(title);
        }
    }

    public void henshin(){
        if (command.equals(""))
            sp.play("333：Standing By.mp3");
        else
            sp.play("Exceed Charge Beep.mp3");
    }

    public void release(){
        sp.play("333：Release.mp3");
    }


    public void initdialog(){
        dialog.add(root.findViewById(R.id.b1));
        dialog.add(root.findViewById(R.id.b2));
        dialog.add(root.findViewById(R.id.b3));
        dialog.add(root.findViewById(R.id.b4));
        dialog.add(root.findViewById(R.id.b5));
        dialog.add(root.findViewById(R.id.b6));
        dialog.add(root.findViewById(R.id.b7));
        dialog.add(root.findViewById(R.id.b8));
        dialog.add(root.findViewById(R.id.b9));
        dialog.add(root.findViewById(R.id.b0));

        for (Button b : dialog){
            b.setOnClickListener(new onCommandListener());
        }
    }

    public void initConfirm(){
        yes.setOnClickListener(new onConfirmListener());
        cancel.setOnClickListener(new onConfirmListener());
    }
}