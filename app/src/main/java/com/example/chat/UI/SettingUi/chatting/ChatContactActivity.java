package com.example.chat.UI.SettingUi.chatting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.chat.R;
import com.example.chat.UI.ReplaceActivity;

public class ChatContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_contact);
        Toolbar toolbar = findViewById(R.id.SettingChatContactToolbar);
        toolbar.setTitle(getResources().getString(R.string.SettingChatContactToolbar));
        setSupportActionBar(toolbar);

        LinearLayout Archive = findViewById(R.id.Archive);
        Archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();
            }
        });

        /////////////////////////////////
        LinearLayout deleteAll = findViewById(R.id.deleteAll);
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDelete();
            }
        });
    }

    private void dialogDelete() {
        final CharSequence[] items = {"حذف الوسائط فى الدردشات"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(ChatContactActivity.this);//ERROR ShowDialog cannot be resolved to a type
        builder.setTitle("هل تريد حقا حذف جميع الدرددشات بما فيها من رسائل ؟");
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    // If the user checked the item, add it to the selected items
                }

            }
        }).setPositiveButton("تم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    private void Dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("هل تريد حقأ أرشفة جميع الدردشات ؟");
        builder.setPositiveButton("تم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ChatContactActivity.this, "finish", Toast.LENGTH_SHORT).show();

            }
        }).setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ChatContactActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });
                builder.show();
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
