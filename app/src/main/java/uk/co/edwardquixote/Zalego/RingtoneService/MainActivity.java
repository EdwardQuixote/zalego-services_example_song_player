package uk.co.edwardquixote.Zalego.RingtoneService;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Intent inRingtoneService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

    }


    private void initializeViews() {

        Button btnStartRingtone = (Button) this.findViewById(R.id.btnStartRingtone);
        Button btnStopRingtone = (Button) this.findViewById(R.id.btnStopRingtone);
        btnStartRingtone.setOnClickListener(clkMain);
        btnStopRingtone.setOnClickListener(clkMain);

    }

    private boolean checkForReadExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(MainActivity.this, "We need this permission, to play the ringtone.", Toast.LENGTH_LONG).show();

                return false;
            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 10101);

                return true;
            }

        } else {
            return true;
        }

    }

    private void startRingtoneService() {

        inRingtoneService = new Intent(MainActivity.this, ServiceRingtone.class);
        this.startService(inRingtoneService);

    }

    private void stopRingtoneService() {

        if (inRingtoneService != null) {
            this.stopService(inRingtoneService);
        }

    }


    private View.OnClickListener clkMain = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btnStartRingtone:

                    if (checkForReadExternalStoragePermission()) {
                        startRingtoneService();
                    }

                    break;

                case R.id.btnStopRingtone:
                    stopRingtoneService();
                    break;

            }

        }

    };

}
