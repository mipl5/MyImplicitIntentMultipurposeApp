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

public class CallActivity extends AppCompatActivity {
    private EditText etPhoneNumberInput;
    private Button btnDoCall;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_call);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        etPhoneNumberInput = (EditText)findViewById(R.id.etPhoneNumberInput);
        btnDoCall = (Button)findViewById(R.id.btnDoCall);
        btnReturn = (Button)findViewById(R.id.btnReturn);
        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnDoCall.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View view) {
                if (etPhoneNumberInput.getText().toString().isEmpty()){
                    Toast.makeText(CallActivity.this, "you can't call without phone number", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + etPhoneNumberInput.getText().toString()));
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Uri webUri = Uri.parse("https://play.google.com/store/apps/details?id=" + "com.google.android.dialer");
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
                        startActivity(webIntent);
                    }
                }
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CallActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}