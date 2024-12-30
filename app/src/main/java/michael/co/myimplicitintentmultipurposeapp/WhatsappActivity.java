package michael.co.myimplicitintentmultipurposeapp;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
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

public class WhatsappActivity extends AppCompatActivity {
    private EditText etInputPhone;
    private Button btnEnter;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_whatsapp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        etInputPhone = (EditText)findViewById(R.id.etInputPhone);
        btnEnter = (Button)findViewById(R.id.btnEnter);
        btnReturn = (Button)findViewById(R.id.btnReturn);

        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View view) {
                if (etInputPhone.getText().toString().isEmpty()){
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "WhatsApp");
                    intent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.HomeActivity"));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    try {
                        startActivity(intent);  // Start the WhatsApp activity
                    } catch (ActivityNotFoundException e) {
                        Uri webUri = Uri.parse("https://play.google.com/store/apps/details?id=" + "com.whatsapp");
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
                        startActivity(webIntent);
                    }
                }
                else{
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://wa.me/" + etInputPhone.getText().toString()));  // URL format for WhatsApp

                    // Check if WhatsApp is installed before proceeding
                    try {
                        startActivity(intent);  // Start the WhatsApp activity
                    } catch (ActivityNotFoundException e) {
                        Uri webUri = Uri.parse("https://play.google.com/store/apps/details?id=" + "com.whatsapp");
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
                        startActivity(webIntent);
                    }
                }
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WhatsappActivity.this, MainActivity.class));
            }
        });
    }
}