package michael.co.myimplicitintentmultipurposeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.mnu_take_picture){
            Intent intent = new Intent(MainActivity.this, TakePictureActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.mnu_whatsapp){
            Intent intent = new Intent(MainActivity.this, WhatsappActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.mnu_sms){
            Intent intent = new Intent(MainActivity.this, SMSActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.mnu_call){
            Intent intent = new Intent(MainActivity.this, CallActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.mnu_mail){
            Intent intent = new Intent(MainActivity.this, MailActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.mnu_clock){
            Intent intent = new Intent(MainActivity.this, ClockActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.mnu_website){
            Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.mnu_youtube){
            Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.mnu_maps){
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.mnu_waze){
            Intent intent = new Intent(MainActivity.this, WazeNavigationActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.mnu_maps_navigation){
            Intent intent = new Intent(MainActivity.this, MapsNavigationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}