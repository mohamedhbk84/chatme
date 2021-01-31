package com.example.chat.UI.SettingUi.Profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chat.R;

public class SettingProfileActivity extends AppCompatActivity {

//    private FrameLayout frameLayout;
//    private CircularImageView profilePic;
//
//    private StorageReference mStorage;
//    private static final int CAMERA_REQUEST_CODE = 1;
//
//    private ProgressDialog mProgress;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);
    }

//        mStorage = FirebaseStorage.getInstance().getReference();
//        mProgress = new ProgressDialog(this);
//
//        frameLayout = findViewById(R.id.SettingProfileFrame);
//        Toolbar toolbar = findViewById(R.id.SettingProfileToolbar);
//        profilePic = findViewById(R.id.profilePic);
//        toolbar.setTitle(getResources().getString(R.string.Setting_Profile_name));
//        setSupportActionBar(toolbar);
//        CardView cardView = findViewById(R.id.CardView);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,CAMERA_REQUEST_CODE);
//            }
//        });
//
////        CardView cardView = findViewById(R.id.CardView);
////        cardView.setCardBackgroundColor(Color.parseColor("#E6E6E6"));
//
//
//    }
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
//
//            mProgress.setMessage("uploading image .....");
//            mProgress.show();
//
//
//            Uri uri = data.getData();
//            final StorageReference filepath = mStorage.child("Photos").child(uri.getLastPathSegment());
//
//            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            // Get a URL to the uploaded content
//                            Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
//                            while(!uri.isComplete());
//                            Uri url = uri.getResult();
//                            Toast.makeText(SettingProfileActivity.this, "Upload Success, download URL " +
//                                    url.toString(), Toast.LENGTH_LONG).show();
//                            Log.i("FBApp1 URL ", url.toString());
//
//                        }
//                    })
//                    .addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception exception) {
//                            // Handle unsuccessful uploads
//                            // ...
//                        }
//                    });
//
//        }
//    }
//        public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.settingmenu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
//        switch (item.getItemId()) {
//            case R.id.back_item:
//                startActivity(new Intent(this, ReplaceActivity.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
