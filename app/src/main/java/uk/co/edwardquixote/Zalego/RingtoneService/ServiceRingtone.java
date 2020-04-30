package uk.co.edwardquixote.Zalego.RingtoneService;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ServiceRingtone extends Service {

    private MediaPlayer mpMediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("ServiceRingtone", "onCreate() - CALLED!");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Log.e("ServiceRingtone", "onStartCommand() - CALLED!");

        mpMediaPlayer = MediaPlayer.create(ServiceRingtone.this, R.raw.audio_to_play);
        mpMediaPlayer.setLooping(true);
        mpMediaPlayer.start();

        Toast.makeText(ServiceRingtone.this, "Our Ringtone is now playing!", Toast.LENGTH_LONG).show();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("ServiceRingtone", "onDestroy() - CALLED!");

        mpMediaPlayer.stop();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
