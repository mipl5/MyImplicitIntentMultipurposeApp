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

public class WebsiteActivity extends AppCompatActivity {
    private EditText etGetWebSearchSource;
    private Button btnDoSearch;
    private Button btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_website);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        etGetWebSearchSource = (EditText)findViewById(R.id.etGetWebSearchSource);
        btnDoSearch = (Button)findViewById(R.id.btnDoSearch);
        btnReturn = (Button)findViewById(R.id.btnReturn);
        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnDoSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etGetWebSearchSource.getText().toString().isEmpty()){
                    Toast.makeText(WebsiteActivity.this, "can't do web search without link", Toast.LENGTH_SHORT).show();
                }
                else{
                    btnDoSearch.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("QueryPermissionsNeeded")
                        @Override
                        public void onClick(View view) {
                            String searchSource = etGetWebSearchSource.getText().toString();
                            String searchUrl = "https://www.google.com/search?q=" + searchSource;
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl));
                            try{
                                startActivity(intent);
                            }catch (ActivityNotFoundException e){
                                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.android.chrome"));
                                startActivity(webIntent);
                            }
                        }
                    });
                }
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WebsiteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}