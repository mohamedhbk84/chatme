package com.example.chat.UI.Location;

import android.Manifest;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chat.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class LocationActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    Button btnShowLocation ,mRecordBtn;
    private static final int REQUEST_CODE_PERMISSION =2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    TrackGPS gps;
    TextView location , mRecordText;
    private StorageReference mStorageRef;

    private static String fileName = null;
    private static final String LOG_TAG = "AudioRecordTest";
    private MediaRecorder recorder = null;


    private MediaPlayer player = null;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
//        mRecordBtn = findViewById(R.id.RecordLabel);
//        mRecordText = findViewById(R.id.TextRecord);
//
//
//        mProgress = new ProgressDialog(this);
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//        // Record to the external cache directory for visibility
////        fileName = getExternalCacheDir().getAbsolutePath();
//        fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
//        fileName += "/audio_record.3gp";
//
//        first();
//
//        Toolbar toolbar5 = findViewById(R.id.settingToolbar);
//
//        toolbar5.setTitle(getResources().getString(R.string.Setting_Helper));
//        setSupportActionBar(toolbar5);
//
//
//        try {
//            if (ActivityCompat.checkSelfPermission(this,mPermission)!= PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(this,new String [] {mPermission},REQUEST_CODE_PERMISSION  );
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        btnShowLocation = findViewById(R.id.btn_GetLocation);
//        btnShowLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gps = new TrackGPS(LocationActivity.this);
//                location = findViewById(R.id.Location);
//                if (gps.canGetLocation){
//                    double latitude = gps.getLatitude();
//                    double longitude = gps.getLongitude();
//                    database = FirebaseDatabase.getInstance();
//                    btnShowLocation.setBackgroundColor(Color.GREEN);
//                    location.setText(latitude +"        "+longitude);
//                    Toast.makeText(getApplicationContext(),"Longitude:"+Double.toString(longitude)+"\nLatitude:"+Double.toString(latitude),Toast.LENGTH_SHORT).show();
//                    myRef = database.getReference("Location");
//                    myRef.setValue(latitude+","+longitude);
//
//                }else {
//                    gps.showSettingsAlert();
//                }
//
//            }
//        });
//    }
//
//    private void first() {
//        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
//        LinearLayout ll = new LinearLayout(this);
//
//        mRecordBtn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN){
//                    mRecordBtn.setBackgroundColor(Color.BLUE);
//
//                    startRecording();
//                    mRecordText.setText("Recording Started .......");
//
//                }else if (event.getAction() ==MotionEvent.ACTION_UP){
//                    mRecordBtn.setBackgroundColor(Color.GREEN);
//                    stopRecording();
//                    mRecordText.setText("Recording Stopped .......");
//                }
//
//                return false;
//            }
//        });
//
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case REQUEST_RECORD_AUDIO_PERMISSION:
//                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                break;
//        }
//        if (!permissionToRecordAccepted ) finish();
//
//    }
//
//    private void onPlay(boolean start) {
//        if (start) {
//            startPlaying();
//        } else {
//            stopPlaying();
//        }
//    }
//
//    private void startPlaying() {
//        player = new MediaPlayer();
//        try {
//            player.setDataSource(fileName);
//            player.prepare();
//            player.start();
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "prepare() failed");
//        }
//    }
//
//    private void stopPlaying() {
//        player.release();
//        player = null;
//    }
//
//
//
//
//
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.settingmenu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
//        switch (item.getItemId()) {
//            case R.id.back_item:
//                startActivity(new Intent(this, MainActivity.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    private void startRecording() {
//        recorder = new MediaRecorder();
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        recorder.setOutputFile(fileName);
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//        try {
//            recorder.prepare();
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "prepare() failed");
//        }
//
//        recorder.start();
//    }
//
//    private void stopRecording() {
//        recorder.stop();
//        recorder.release();
//        recorder = null;
//        uploadAudio();
//    }
//
//    private void uploadAudio() {
//        mProgress.setMessage("uploading Audio ....");
//        mProgress.show();
//
//        StorageReference filepath = mStorageRef.child("Audio").child("new_Audio.3gp");
//        Uri uri = Uri.fromFile(new File(fileName));
//        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                mProgress.dismiss();
//                mRecordText.setText("uploading finish");
//
//            }
//        });

    }


}
