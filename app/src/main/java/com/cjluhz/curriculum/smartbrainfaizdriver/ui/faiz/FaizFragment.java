package com.cjluhz.curriculum.smartbrainfaizdriver.ui.faiz;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.cjluhz.curriculum.smartbrainfaizdriver.R;
import com.cjluhz.curriculum.smartbrainfaizdriver.soundPlayer;

import java.util.ArrayList;
import java.util.List;

public class FaizFragment extends Fragment {
    private final String title = "FAIZ";
    private String command = "";
    private View root;
    private Button yes;
    private Button cancel;
    private TextView panel;
    private Context mContext;
    private List<Button> dialog = new ArrayList<>();
    private RadioGroup rg;
    private soundPlayer sp;

    private static final int Faiz = 1;
    private static final int AxelForm = 2;
    private static final int BlasterForm = 3;
    private int mode;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.fragment_faiz, container,false);
        mContext = getContext();
        mode = FaizFragment.Faiz;

        yes = root.findViewById(R.id.yes);
        cancel = root.findViewById(R.id.cancel);
        panel = root.findViewById(R.id.command);
        rg = root.findViewById(R.id.faizmode);
        rg.setOnCheckedChangeListener(new groupCheck());

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
        if ("3821".equals(command)){
            sp.play("555：Jet-Sliger Got Closer!!.mp3");
            return;
        }
        if (mode == FaizFragment.Faiz) {
            switch (command) {
                case "555":
                    sp.play("555：Standing By.mp3");
                    break;
                case "":
                    new charger().execute();
                    break;
                default:
                    sp.play("555：Error.mp3");
                    break;
            }
            return;
        }
        if (mode == FaizFragment.AxelForm){
            switch (command){
                case "555":
                    sp.play("555：Standing By.mp3");
                    break;
                case "":
                    new timer().execute();
                    break;
            }
        }
        if (mode == FaizFragment.BlasterForm) {
            switch (command) {
                case "555":
                    sp.play("555 Blaster：Awakening.mp3");
                    break;
                case "5552":
                    new blasterCharger().execute();
                    break;
                default:
                    sp.play("555：Error.mp3");
                    break;
            }
        }
    }

    public void release(){
        if (mode == FaizFragment.AxelForm){
            sp.play("555 Accel：Time Out & Reformation.mp3");
        } else
            sp.play("555：Release.mp3");
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


    public class groupCheck implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.defaultForm:
                    mode = FaizFragment.Faiz;
                    break;
                case R.id.AxelForm:
                    mode = FaizFragment.AxelForm;
                    break;
                case R.id.BlasterForm:
                    mode = FaizFragment.BlasterForm;
                    break;
            }
        }
    }

    class charger extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            sp.play("555：Ready.mp3");
            try {
                Thread.sleep(4000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            sp.play("Exceed Charge.mp3");
            return null;
        }
    }

    class timer extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            sp.play("555 Accel：Start Up!.mp3");
            try {
                Thread.sleep(10000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            sp.play("555 Accel：Time Out & Reformation.mp3");
            return null;
        }
    }

    class blasterCharger extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            sp.play("Exceed Charge.mp3");
            try {
                Thread.sleep(2500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            sp.play("555 Phone Blaster：Burst Mode.mp3");
            return null;
        }
    }
}