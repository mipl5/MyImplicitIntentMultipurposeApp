package michael.co.myimplicitintentmultipurposeapp;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SMSActivity extends AppCompatActivity {
    private EditText etInputYourPhone;
    private EditText etInputYourMessage;
    private Button btnDoSMS;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_smsactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        etInputYourPhone = (EditText)findViewById(R.id.etInputYourPhone);
        etInputYourMessage = (EditText)findViewById(R.id.etInputYourMessage);
        btnDoSMS = (Button)findViewById(R.id.btnDoSMS);
        btnReturn = (Button)findViewById(R.id.btnReturn);

        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnDoSMS.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View view) {
                if (etInputYourPhone.getText().toString().isEmpty()){
                    Toast.makeText(SMSActivity.this, "Activity is impossible without phone number", Toast.LENGTH_SHORT).show();
                }
                else if (etInputYourMessage.getText().toString().isEmpty()){
                    Toast.makeText(SMSActivity.this, "Activity is impossible without message", Toast.LENGTH_SHORT).show();
                }
                else{
                    String phoneNumber = etInputYourPhone.getText().toString();
                    String message = etInputYourMessage.getText().toString();
                    Uri uri = Uri.parse("sms:" + phoneNumber + "?body=" + Uri.encode(message));

                    // Create an implicit intent to send the SMS
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);

                    // Check if there is an app to handle the intent (SMS app)
                    try {
                        startActivity(smsIntent);  // Open SMS app with pre-filled number and message
                    } catch (ActivityNotFoundException e) {
                        Uri webUri = Uri.parse("https://play.google.com/store/apps/details?id=" + "com.google.android.apps.messaging");
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
                        startActivity(webIntent);  // Open in browser
                    }
                }
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SMSActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}