package com.cjluhz.curriculum.smartbrainfaizdriver;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.util.Log;



public class soundPlayer {
    private MediaPlayer mplayer;
    private AssetManager am;
    private Context mContext;

    public soundPlayer(Context c){
        this.mContext = c;
        am = c.getResources().getAssets();
    }

    public void play(String filename) {
        stop();
        mplayer = new MediaPlayer();
        mplayer.setLooping(false);
        mplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            AssetFileDescriptor fileDescriptor = mContext.getAssets().openFd(filename);
            mplayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            mplayer.prepareAsync();
            mplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

        } catch (Exception e){
            Log.e("mediaplayer", "Cannot play sound");
            e.printStackTrace();
        }
    }
    public void stop(){
        if (mplayer != null && mplayer.isPlaying()){
            mplayer.stop();
            mplayer.release();
            mplayer = null;
        }
    }
}
