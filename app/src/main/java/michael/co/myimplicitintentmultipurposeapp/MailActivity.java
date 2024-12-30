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

public class MailActivity extends AppCompatActivity {
    private EditText etGetRecipient;
    private EditText etGetSubject;
    private EditText etGetBodyMessage;
    private Button btnSendByMail;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        etGetRecipient = (EditText)findViewById(R.id.etGetRecipient);
        etGetSubject = (EditText)findViewById(R.id.etGetSubject);
        etGetBodyMessage = (EditText)findViewById(R.id.etGetBodyMessage);
        btnSendByMail = (Button)findViewById(R.id.btnSendByMail);
        btnReturn = (Button)findViewById(R.id.btnReturn);
        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnSendByMail.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"QueryPermissionsNeeded", "IntentReset"})
            @Override
            public void onClick(View view) {
                if (!etGetRecipient.getText().toString().isEmpty() &&
                        !etGetSubject.getText().toString().isEmpty() &&
                        !etGetBodyMessage.getText().toString().isEmpty()) {
                    // Create an Intent to send an email
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setType("text/plain");
                    emailIntent.setPackage("com.google.android.gm");
                    // Set the recipient's email address (you can add multiple recipients separated by commas)
                    String emailAddress = etGetRecipient.getText().toString();
                    emailIntent.setData(Uri.parse("mailto:" + emailAddress));  // "mailto:" indicates email
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress);
                    // Add subject and body text (message)
                    String subject = etGetSubject.getText().toString().trim();
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, String.valueOf(subject));
                    String bodyMessage = etGetBodyMessage.getText().toString().trim();
                    emailIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(bodyMessage));

                    // Check if there's an email app that can handle this intent
                    try{
                        startActivity(emailIntent);
                    } catch (ActivityNotFoundException e) {
                        // Fallback in case no email app is found
                        Toast.makeText(MailActivity.this, "No email app found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MailActivity.this, "a part missing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}