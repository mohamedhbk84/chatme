package com.example.chat.UI.SettingUi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.chat.R;
import com.example.chat.UI.ConnectUi.Connect_UsActivity;
import com.example.chat.UI.ReplaceActivity;

import java.util.ArrayList;
import java.util.List;

public class SettingHelperActivity extends AppCompatActivity {
    private String[] permissions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_helper);

        FrameLayout frameLayout = findViewById(R.id.settingHelperFrame);
        Toolbar toolbar = findViewById(R.id.settingHelperToolbar);

        toolbar.setTitle(getResources().getString(R.string.Setting_Helper_name));
        setSupportActionBar(toolbar);

        LinearLayout Helper_invite = findViewById(R.id.SettingHelper_invite);
        Helper_invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullUrl = "https://m.google.com";
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setPackage("com.google.katana");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "your title text");
                    startActivity(sharingIntent);

                } catch (Exception e) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(fullUrl));
                    startActivity(i);


                }
            }
        });


        LinearLayout Helper_CallME = findViewById(R.id.SettingHelper_CallME);
        Helper_CallME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPermissionGranted()) {
                    call_action();
                }
            }
        });
        permissions = new String[]{Manifest.permission.CALL_PHONE};

        LinearLayout Helper_private=findViewById(R.id.SettingHelper_private);
        Helper_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullUrl = "https://m.google.com";
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setPackage("com.google.katana");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "your title text");
                    startActivity(sharingIntent);

                } catch (Exception e) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(fullUrl));
                    startActivity(i);


                }
            }
        });

        LinearLayout Helper_aboutApp =findViewById(R.id.SettingHelper_aboutApp);
        Helper_aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingHelperActivity.this, Connect_UsActivity.class));
                fileList();
            }
        });
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
                startActivity(new Intent(this, ReplaceActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean isPermissionGranted() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
    }


    public void call_action() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel: 01559606577" ));
        startActivity(callIntent);
    }
}

