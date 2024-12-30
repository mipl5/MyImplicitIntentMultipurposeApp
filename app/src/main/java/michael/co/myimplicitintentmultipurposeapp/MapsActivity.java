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

public class MapsActivity extends AppCompatActivity {
    private EditText etGetLongitude;
    private EditText etGetLatitude;
    private EditText etGetAddress;
    private Button btnFindByLongitudeAndLatitude;
    private Button btnFindByAddress;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maps);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        etGetLongitude = (EditText)findViewById(R.id.etGetLongitude);
        etGetLatitude = (EditText)findViewById(R.id.etGetLatitude);
        etGetAddress = (EditText)findViewById(R.id.etGetAddress);
        btnFindByLongitudeAndLatitude = (Button)findViewById(R.id.btnFindByLongitudeAndLatitude);
        btnFindByAddress = (Button)findViewById(R.id.btnFindByAddress);
        btnReturn = (Button)findViewById(R.id.btnReturn);

        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnFindByLongitudeAndLatitude.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View view) {
                if (!etGetLongitude.getText().toString().isEmpty() &&
                        !etGetLatitude.getText().toString().isEmpty()){
                    double latitude = Double.parseDouble(etGetLatitude.getText().toString());
                    double longitude = Double.parseDouble(etGetLongitude.getText().toString());
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps"));
                        startActivity(webIntent);  // Open the web version of the Play Store
                    }
                }
                else{
                    Toast.makeText(MapsActivity.this, "a part missing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnFindByAddress.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View view) {
                if (!etGetAddress.getText().toString().isEmpty())
                {
                    String searchQuery = etGetAddress.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("geo:0,0?q=" + Uri.encode(searchQuery)));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps"));
                        startActivity(webIntent);  // Open the web version of the Play Store
                    }
                }
                else{
                    Toast.makeText(MapsActivity.this, "a part missing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}