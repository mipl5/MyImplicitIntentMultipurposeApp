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

public class MapsNavigationActivity extends AppCompatActivity {
    private EditText etGetFrom;
    private EditText etGetTo;
    private Button btnDoNavigation;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maps_navigation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        etGetFrom = (EditText)findViewById(R.id.etGetFrom);
        etGetTo = (EditText)findViewById(R.id.etGetTo);
        btnDoNavigation = (Button)findViewById(R.id.btnDoNavigation);
        btnReturn = (Button)findViewById(R.id.btnReturn);
        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnDoNavigation.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View view) {
                if (etGetFrom.getText().toString().isEmpty()){
                    Toast.makeText(MapsNavigationActivity.this, "don't forget from parameter", Toast.LENGTH_SHORT).show();
                }
                else if (etGetTo.getText().toString().isEmpty()){
                    Toast.makeText(MapsNavigationActivity.this, "don't forget to parameter", Toast.LENGTH_SHORT).show();
                }
                else{
                    String fromAddress = etGetFrom.getText().toString();
                    String toAddress = etGetFrom.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com/maps/dir/?api=1&origin=" + fromAddress + "&destination=" + toAddress));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps"));
                        startActivity(webIntent);
                    }
                }
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsNavigationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}