package com.cjluhz.curriculum.smartbrainfaizdriver.ui.kaixa;

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

import androidx.fragment.app.Fragment;


import com.cjluhz.curriculum.smartbrainfaizdriver.R;
import com.cjluhz.curriculum.smartbrainfaizdriver.soundPlayer;

import java.util.ArrayList;
import java.util.List;

public class KaixaFragment extends Fragment {
    private final String title = "KAIXA";
    private String command = "";
    private View root;
    private Button yes;
    private Button cancel;
    private TextView panel;
    private Context mContext;
    private List<Button> dialog = new ArrayList<>();
    private RadioGroup rg;
    private soundPlayer sp;

    private static final int GoldSmash = 1;
    private static final int GranImpact = 2;
    private static final int KaiserSlash = 3;
    private int mode;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.fragment_kaixa, container,false);
        mContext = getContext();
        mode = KaixaFragment.GoldSmash;

        yes = root.findViewById(R.id.yes);
        cancel = root.findViewById(R.id.cancel);
        panel = root.findViewById(R.id.command);
        rg = root.findViewById(R.id.kaixakill);
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
        if ("3821".equals(command))
            sp.play("913：Jet-Sliger Got Closer!!.mp3");
        if ("913".equals(command))
            sp.play("913：Standing By.mp3");
        else if("".equals(command)) {
            new charge().execute();
        } else
            sp.play("913：Error.mp3");
    }

    public void release(){
        sp.play("913：Release.mp3");
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
                case R.id.GoldSmash:
                    mode = GoldSmash;
                    break;
                case R.id.GranImpact:
                    mode = GranImpact;
                    break;
                case R.id.KaiserSlash:
                    mode = KaiserSlash;
                    break;
            }
        }
    }

    class charge extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            sp.play("913：Ready.mp3");
            try {
                Thread.sleep(4000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            if (mode == KaixaFragment.GoldSmash)
                sp.play("913：Exceed Charge Beep.mp3");
            else if (mode == KaixaFragment.GranImpact)
                sp.play("913：Exceed Charge.mp3");
            else if (mode == KaixaFragment.KaiserSlash)
                sp.play("913：Exceed Charge Long version.mp3");
            return null;
        }
    }
}