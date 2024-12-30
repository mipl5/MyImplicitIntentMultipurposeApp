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

public class WazeNavigationActivity extends AppCompatActivity {
    private EditText etGetTo;
    private Button btnDoNavigation;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_waze_navigation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
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
                if (etGetTo.getText().toString().isEmpty()){
                    Toast.makeText(WazeNavigationActivity.this, "don't forget to parameter", Toast.LENGTH_SHORT).show();
                }
                else{
                    String to = etGetTo.getText().toString();
                    String wazeUri = "https://waze.com/ul?q=<"+to+">&navigate=yes";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(wazeUri));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e){
                        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.waze"));
                        startActivity(webIntent);
                    }
                }
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WazeNavigationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}