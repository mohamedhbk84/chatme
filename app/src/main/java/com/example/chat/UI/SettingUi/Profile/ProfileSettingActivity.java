package com.example.chat.UI.SettingUi.Profile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.chat.R;
import com.example.chat.UI.ReplaceActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.util.UUID;

public class ProfileSettingActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private CircularImageView profileP;

     StorageReference mStorage;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int GALLERY_INTENT = 71;

    private ProgressDialog mProgress;
    private CircularImageView image;
    SharedPreferences mSharedPreferences;
    SharedPreferences sp ;
    private EditText getNameEdt;
    private TextView Profile_Setting_Name,ReplaceTxt;
    private Uri downloadUri;
    private Uri filePath;
    //Firebase
    FirebaseStorage storage;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
//        String maxDead =  mSharedPreferences.getString("maxDead", "DEFAULT");
        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        mStorage = FirebaseStorage.getInstance().getReference();
        mProgress = new ProgressDialog(this);
        profileP = findViewById(R.id.profilePic);
        TextView ProfileNewsTxt = findViewById(R.id.ProfileNews);
        ProfileNewsTxt.setText(mSharedPreferences.getString("News","متوفر"));
         Profile_Setting_Name = findViewById(R.id.Profile_Setting_Name);
        Profile_Setting_Name.setText(mSharedPreferences.getString("maxDead","mohamed"));
        ReplaceTxt = findViewById(R.id.ReplaceTxt);
        frameLayout = findViewById(R.id.SettingProfileFrame);
        Toolbar toolbar = findViewById(R.id.SettingProfileToolbar);
        toolbar.setTitle(getResources().getString(R.string.Setting_Profile_name));
        setSupportActionBar(toolbar);
////////////////////////////////////
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
         image = findViewById(R.id.ImageCard);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_INTENT);
                uploadImage();

            }
        });

//        CardView cardView = findViewById(R.id.CardView);
//        cardView.setCardBackgroundColor(Color.parseColor("#E6E6E6"));

        final LinearLayout edit_Name = findViewById(R.id.edit_Name);
        edit_Name.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                editName();
            }
        });
        LinearLayout edit_stautes = findViewById(R.id.edit_stautes);
        edit_stautes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileSettingActivity.this,SituationNewsActivity.class));
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void editName() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.alertchangename);
         final AlertDialog alert = builder.show();
          getNameEdt =alert.findViewById(R.id.getNameEdt);
          getNameEdt.setText(mSharedPreferences.getString("maxDead","mohamed"));
        TextView TxtSave = alert.findViewById(R.id.TxtSave);
        TextView TxtDontSave = alert.findViewById(R.id.TxtDontSave);
        TxtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("maxDead", getNameEdt.getText().toString());
                editor.commit();
                editor.apply();
                alert.dismiss();
                Profile_Setting_Name.setText(mSharedPreferences.getString("maxDead","mohamed"));

//                Profile_Setting_Name.setText(getNameEdt.getText().toString());
                Toast.makeText(ProfileSettingActivity.this, "save", Toast.LENGTH_SHORT).show();
            }
        });
        TxtDontSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
alert.dismiss();
Toast.makeText(ProfileSettingActivity.this, "not change", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK  && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profileP.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

//            mProgress.setMessage("uploading image .....");
//            mProgress.show();
//            final Uri uri = data.getData();
//            StorageReference filePath = mStorage.child("Photos").child(uri.getLastPathSegment());
//            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    mProgress.dismiss();
//
//                    Task<Uri> downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl();
//                    Glide.with(ProfileSettingActivity.this).load(downloadUrl).into(profileP);
//
////                    Glide.with(ProfileSettingActivity.this).load(urlImage).into(profilePic);
////                    Picasso.get().load(downloadUrl).into(profileP);
//
//                    Toast.makeText(ProfileSettingActivity.this, "finish upload ", Toast.LENGTH_SHORT).show();
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    mProgress.dismiss();
//                    Toast.makeText(ProfileSettingActivity.this, "upload failure", Toast.LENGTH_SHORT).show();
//                }
//            });
        }

    }

    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(ProfileSettingActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ProfileSettingActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
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

