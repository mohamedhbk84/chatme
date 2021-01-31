package com.example.chat.UI.HomeUi;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.chat.R;
import com.example.chat.UI.ReplaceActivity;
import com.example.chat.helper.HelperMethod;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int GALLERY_INTENT = 2;
    private CircularImageView image;
    private ProgressDialog mProgress;
    StorageReference mStorage;
    StorageReference Storagereference;


    private LinearLayout add_Status;
    private CircularImageView imageViewState;

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_status, container, false);
        setHasOptionsMenu(true);
        mStorage = FirebaseStorage.getInstance().getReference();
        imageViewState = inflate.findViewById(R.id.imageViewState);
        add_Status = inflate.findViewById(R.id.add_Status);
        add_Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent,GALLERY_INTENT);


                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,
                        CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

            }
        });

        return inflate;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // convert byte array to Bitmap

            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                    byteArray.length);
            imageViewState.setImageBitmap(bitmap);

//            mProgress.setMessage("uploading image .....");
//            mProgress.show();

//            Uri uri = data.getData();
//            StorageReference reference = mStorage.child("CameraPhotos").child(uri.getLastPathSegment());
//            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(getContext(), "upload finish", Toast.LENGTH_SHORT).show();
//                    Task<Uri> downloadUri = taskSnapshot.getMetadata().getReference().getDownloadUrl();
//                    Picasso.with(getContext()).load(String.valueOf(downloadUri)).into(imageViewState);
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getContext(), "upload failed", Toast.LENGTH_SHORT).show();
//                }
//            });
        }

    }

//
//    Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
//                    Glide.with(getContext()).load(downloadUrl).into(imageViewState);


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.statemenu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getActivity(), "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {
            case R.id.state_connect_us_item:

                Contact_UsFragment contact_usFragment = new Contact_UsFragment();
                HelperMethod.replace(contact_usFragment,getActivity().getSupportFragmentManager(),R.id.Frame_layout,null,null);

                return true;
            case R.id.state_upload_item:
                startActivity(new Intent(getActivity(), ReplaceActivity.class));
                getActivity().finish();
                // do your code
                return true;
            default:
                break;
        }

        return false;
    }



}
