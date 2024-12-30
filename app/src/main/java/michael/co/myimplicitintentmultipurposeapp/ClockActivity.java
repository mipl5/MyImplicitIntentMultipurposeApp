package michael.co.myimplicitintentmultipurposeapp;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ClockActivity extends AppCompatActivity {
    private TextView tvHoursShow;
    private TextView tvMinutesShow;
    private SeekBar seekBarHours;
    private SeekBar seekBarMinutes;
    private Button btnSetAlarm;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clock);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        tvHoursShow = (TextView)findViewById(R.id.tvHoursShow);
        tvMinutesShow = (TextView)findViewById(R.id.tvMinutesShow);
        seekBarHours = (SeekBar)findViewById(R.id.seekBarHours);
        seekBarHours.setMax(23); // < 24 hours - not to permit 24
        seekBarMinutes = (SeekBar)findViewById(R.id.seekBarMinutes);
        seekBarMinutes.setMax(59); // < 60 minutes - not to permit put 60
        btnSetAlarm = (Button)findViewById(R.id.btnSetAlarm);
        btnReturn = (Button)findViewById(R.id.btnReturn);
        initializeListeners();
    }

    private void initializeListeners() {
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View view) {
                int hour = Integer.parseInt(tvHoursShow.getText().toString());
                int minute = Integer.parseInt(tvMinutesShow.getText().toString());

                // Create an implicit intent to set the alarm
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);

                // Add extras for time, label, etc.
                // don't know how to solver this bug.
                // it doesn't navigate me to clock application on device
                intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
                intent.putExtra(AlarmClock.EXTRA_SKIP_UI, false);  // true to skip UI and directly set alarm (if supported)
                // Check if there is an app that can handle this intent (e.g., Alarm Clock app)
                try {
                    startActivity(intent);  // Open the default alarm clock app with the set time
                }
                catch (ActivityNotFoundException e){
                    Toast.makeText(ClockActivity.this, "no clock app installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        seekBarHours.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvHoursShow.setText(String.valueOf(seekBarHours.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBarMinutes.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvMinutesShow.setText(String.valueOf(seekBarMinutes.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClockActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}