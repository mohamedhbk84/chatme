package com.example.chat.UI.SettingUi;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.chat.R;
import com.example.chat.UI.ReplaceActivity;

public class StorageInformationActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_information);
        frameLayout = findViewById(R.id.StorageInformationFrame);
        Toolbar toolbar = findViewById(R.id.StorageInformationToolbar);

        toolbar.setTitle(getResources().getString(R.string.StorageInformation_name));
        setSupportActionBar(toolbar);
        LinearLayout used_data_phone =findViewById(R.id.used_data_phone);
        used_data_phone.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
        LinearLayout used_WiFi_phone = findViewById(R.id.used_WiFi_phone);
        used_data_phone.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View v) {
                ShowDialog();
            }
        });
        LinearLayout betweenCall =findViewById(R.id.between_Call);
        betweenCall.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                showDialogAlert();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    private void showDialogAlert() {
        AlertDialog.Builder builderM = new AlertDialog.Builder(this);
        builderM.setView(R.layout.dialogstoragedata);
        builderM.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(StorageInformationActivity.this, "true", Toast.LENGTH_SHORT).show();
            }
        });
        final AlertDialog show = builderM.show();
        View view = show.findViewById(R.id.dialog_Text_finish);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowNewDialog();
                show.dismiss();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ShowNewDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.nextdialogstorage);
        builder.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(StorageInformationActivity.this, "true", Toast.LENGTH_SHORT).show();
            }
        });
        final AlertDialog show = builder.show();
        View viewB = show.findViewById(R.id.finish_dialog);
        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });


    }


    //dialog in Storage
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void ShowDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialogstoragedata);
        builder.setPositiveButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(StorageInformationActivity.this, "true", Toast.LENGTH_SHORT).show();
            }
        });
        final AlertDialog show = builder.show();
        show.findViewById(R.id.text_dialog);
        show.findViewById(R.id.checkbox_photo);
        View viewById = show.findViewById(R.id.dialog_Text_finish);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StorageInformationActivity.this, "finish", Toast.LENGTH_SHORT).show();
                show.dismiss();
            }
        });
        View id = show.findViewById(R.id.dialog_Text_dec);
        id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StorageInformationActivity.this, "cancel", Toast.LENGTH_SHORT).show();
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
}
