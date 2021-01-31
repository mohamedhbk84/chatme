package com.example.chat.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.chat.MainActivity;
import com.example.chat.R;
import com.example.chat.UI.SettingUi.Profile.ProfileSettingActivity;
import com.example.chat.UI.SettingUi.SettingHelperActivity;
import com.example.chat.UI.SettingUi.StorageInformationActivity;
import com.example.chat.UI.SettingUi.account.SettingAccountActivity;
import com.example.chat.UI.SettingUi.alert.SettingAlertActivity;
import com.example.chat.UI.SettingUi.chatting.SettingChattingActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;

public class ReplaceActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    SharedPreferences mSharedPreferences;
    private CircularImageView circularImageView;
    private StorageReference mStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());


        TextView ReplaceTxt = findViewById(R.id.ReplaceTxt);
        ReplaceTxt.setText(mSharedPreferences.getString("maxDead", "mohamed"));

        frameLayout = findViewById(R.id.Frame_layout);
        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        toolbar1.setTitle(getResources().getString(R.string.setting_name));
        setSupportActionBar(toolbar1);
//        toolbar1.inflateMenu(R.menu.chatmenu);
//        toolbar1.setTitle("Title");
        circularImageView = findViewById(R.id.circularImageView);
// Set Color
        circularImageView.setCircleColor(Color.WHITE);
// or with gradient
        circularImageView.setCircleColorStart(Color.BLACK);
        circularImageView.setCircleColorEnd(Color.RED);
        circularImageView.setCircleColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Set Border
        circularImageView.setBorderWidth(10f);
        circularImageView.setBorderColor(Color.BLACK);
// or with gradient
        circularImageView.setBorderColorStart(Color.BLACK);
        circularImageView.setBorderColorEnd(Color.RED);
        circularImageView.setBorderColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Add Shadow with default param
        circularImageView.setShadowEnable(true);
// or with custom param
        circularImageView.setShadowRadius(7f);
        circularImageView.setShadowColor(Color.RED);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);

        mStorage = FirebaseStorage.getInstance().getReference();

        File file = new File(String.valueOf("gs://chat2-43c85.appspot.com/Photos/320618.png"));
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("Photos");

        storageRef.child(file.getName()).putFile(Uri.parse("gs://chat2-43c85.appspot.com/Photos/320618.png"))
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(ReplaceActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        Task<Uri> downloadUri = taskSnapshot.getStorage().getDownloadUrl();

                        if (downloadUri.isSuccessful()) {
                            String generatedFilePath = downloadUri.getResult().toString();
                            System.out.println("## Stored path is " + generatedFilePath);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });






        /////////////////////////////////////////////////////////

        LinearLayout inviteFriends=findViewById(R.id.invite_Friend_Layout);
        inviteFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShareClicked();
                Toast.makeText(ReplaceActivity.this, "invite", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout helper = findViewById(R.id.setting_help);
        helper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReplaceActivity.this, SettingHelperActivity.class));
            }
        });

        LinearLayout setting_data_storage = findViewById(R.id.setting_data_storage);
        setting_data_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReplaceActivity.this, StorageInformationActivity.class));
                finish();
            }
        });

        LinearLayout setting_profile = findViewById(R.id.setting_profile);
        setting_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReplaceActivity.this, ProfileSettingActivity.class));
                finish();

            }
        });

        LinearLayout setting_account = findViewById(R.id.setting_account);

        setting_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReplaceActivity.this, SettingAccountActivity.class));
                finish();
            }
        });
        LinearLayout setting_chatting = findViewById(R.id.setting_chatting);
        setting_chatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReplaceActivity.this, SettingChattingActivity.class));
                finish();
            }
        });
        LinearLayout setting_alert = findViewById(R.id.setting_alert);
        setting_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReplaceActivity.this, SettingAlertActivity.class));
                finish();
            }
        });

    }

    private void onShareClicked() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settingmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.back_item:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
