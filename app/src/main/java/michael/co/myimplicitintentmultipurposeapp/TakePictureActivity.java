package michael.co.myimplicitintentmultipurposeapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class TakePictureActivity extends AppCompatActivity {
    private ImageView ivShowTakenPicture;
    private MaterialButton btnTakePicture;
    private MaterialButton btnReturnToMainActivity;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_take_picture);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
    }

    private void initializeViews() {
        ivShowTakenPicture = (ImageView)findViewById(R.id.ivShowTakenPicture);
        btnTakePicture = (MaterialButton)findViewById(R.id.btnTakePicture);
        btnReturnToMainActivity = (MaterialButton)findViewById(R.id.btnReturn);

        initializeOnClickListeners();
    }

    private void initializeOnClickListeners() {
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
        btnReturnToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TakePictureActivity.this, MainActivity.class));
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
            Toast.makeText(TakePictureActivity.this, "There is a problem with your camera", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result is from the camera and if it was successful
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // The photo is returned as a Bitmap in the data Intent
            Bitmap imageBitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");

            // Set the captured image to the ImageView
            ivShowTakenPicture.setImageBitmap(imageBitmap);
        }
    }
}