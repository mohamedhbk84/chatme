package com.example.chat.UI.SettingUi.chatting;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.chat.R;
import com.example.chat.UI.ReplaceActivity;
import com.google.android.material.snackbar.Snackbar;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Arrays;

public class SettingChattingActivity extends AppCompatActivity {

    private TextView textView,editTxt;
    private LinearLayout ChangeSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_chatting);
        Toolbar toolbar = findViewById(R.id.SettingChattingToolbar);
        toolbar.setTitle(getResources().getString(R.string.Setting_Chatting_name));
        setSupportActionBar(toolbar);

        ///////////////
        editTxt = findViewById(R.id.ChangeTxt);
        textView = findViewById(R.id.TxtColor);
        LinearLayout SettingChattingColor = findViewById(R.id.SettingChattingColor);
        SettingChattingColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog();
            }
        });
        ////////////////////////
        LinearLayout SettingChattingBackgroundColor = findViewById(R.id.SettingChattingBackgroundColor);
        SettingChattingBackgroundColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAlertDialog();
            }
        });
        //////////////////////////////////
        ChangeSize = findViewById(R.id.ChangeSizeLine);
        ChangeSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomChangeSize();
            }
        });
        /////////////////////////
        LinearLayout ChatLogs = findViewById(R.id.ChatLogs);
        ChatLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SettingChattingActivity.this,ReplaceActivity.class));
                finish();
            }
        });

    }

    private void CustomChangeSize() {
        final String[] items =new String[] {"كبير", "متوسط" , "صغير"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(SettingChattingActivity.this);//ERROR ShowDialog cannot be resolved to a type
        builder.setTitle("حجم الخط");
        builder.setSingleChoiceItems(items, -1,
                new DialogInterface.OnClickListener() {
                    @SuppressLint("ResourceType")
                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(getApplicationContext(), items[item],
                                Toast.LENGTH_SHORT).show();
                        String s1 = Arrays.asList(items).get(item);
                        Snackbar.make(ChangeSize,
                                "Checked : " + s1,
                                Snackbar.LENGTH_SHORT
                        ).show();
                        setTheme(R.style.AppTheme);
                        switch (item) {
                            case 0:
                                String s = items[item].toString();
                                editTxt.setText("كبير");
                                dialog.dismiss();
                                break;
                            case 1:
                                editTxt.setText("متوسط");
                                dialog.dismiss();

                                break;
                            case 2:
                                editTxt.setText("صغير");
                                dialog.dismiss();
                                break;
                        }
                    }
                }).show();

    }

    private void customAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SettingChattingActivity.this);
        builder.setView(R.layout.alertchattingbackgroundcolor);
        final AlertDialog alertDialog = builder.show();
        ///galary open/////
        ////////////////////////////////////////////////////////////////////
        CircularImageView alertImageCamera = alertDialog.findViewById(R.id.alertImageCamera);
        alertImageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
          ///delete
         /////////////////////////////////////////////////////////////
        CircularImageView alertImageDelete = alertDialog.findViewById(R.id.alertImageDelete);
        alertImageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Toast.makeText(SettingChattingActivity.this, "Delete", Toast.LENGTH_SHORT).show();

            }
        });

        //////////////////////////////////////////////////
       CircularImageView alertImageColor = alertDialog.findViewById(R.id.alertImageColor);
       alertImageColor.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               alertDialog.dismiss();
               Toast.makeText(SettingChattingActivity.this, "Choice Color", Toast.LENGTH_SHORT).show();
           }
       });
       ////////////////////////////////////////
        CircularImageView alertImagePre = alertDialog.findViewById(R.id.alertImagePre);
        alertImagePre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Toast.makeText(SettingChattingActivity.this, "previous", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void alertDialog() {
        final CharSequence[] items = {"فاتح", "داكن"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(SettingChattingActivity.this);//ERROR ShowDialog cannot be resolved to a type
        builder.setTitle("المظهر");
        builder.setSingleChoiceItems(items, -1,
                new DialogInterface.OnClickListener() {
                    @SuppressLint("ResourceType")
                    public void onClick(DialogInterface dialog, int item) {
                        Toast.makeText(getApplicationContext(), items[item],
                                Toast.LENGTH_SHORT).show();


                        switch (item) {
                            case 0:
                                Toast.makeText(SettingChattingActivity.this, "white", Toast.LENGTH_SHORT).show();
                                textView.setText("فاتح");
                                break;
                            case 1:
                                Toast.makeText(SettingChattingActivity.this, "black", Toast.LENGTH_SHORT).show();
                                textView.setText("داكن");
//                                setTheme(R.style.AppTheme_Purple);
                                setTheme(R.style.AppTheme2);
                                break;
                        }

                    }
                });

        builder.setPositiveButton("تم", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(SettingChattingActivity.this, "Success", Toast.LENGTH_SHORT)
                        .show();



            }
        });

        builder.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(SettingChattingActivity.this, "Fail", Toast.LENGTH_SHORT)
                        .show();
            }
        });

        AlertDialog alert = builder.create();
        alert.getButton(R.color.black);
        alert.show();
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
