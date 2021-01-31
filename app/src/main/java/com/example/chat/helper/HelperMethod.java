package com.example.chat.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;


//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;

public class HelperMethod {
    private static ProgressDialog checkDialog;


    public static void replace(Fragment fragment, FragmentManager supportFragmentManager, int id, TextView Tool_Bar_Title, String title) {

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        if (Tool_Bar_Title != null) {
            Tool_Bar_Title.setText(title);

        }
    }
//
//    public static void onLoadImageFromUrl(ImageView imageView, String URl, Context context) {
//        Glide.with(context)
//                .load(URl)
//                .into(imageView);
//    }

    public static void setInitRecyclerViewAsLinearLayoutManager(Context context, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public static void setInitRecyclerViewAsGridLayoutManager(Activity activity, RecyclerView recyclerView, GridLayoutManager gridLayoutManager, int numberOfColumns) {
        gridLayoutManager = new GridLayoutManager(activity, numberOfColumns);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public static void disappearKeypad(Activity activity, View v) {
        try {
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {

        }
    }

    public static void showProgressDialog(Activity activity, String title) {
        try {
            if (checkDialog == null) {
                checkDialog = new ProgressDialog(activity);
                checkDialog.setMessage(title);
                checkDialog.setIndeterminate(false);
                checkDialog.setCancelable(false);

            }
            checkDialog.show();

        } catch (Exception e) {

        }
    }

    public static void dismissProgressDialog() {
        try {
            if (checkDialog != null && checkDialog.isShowing()) {
                checkDialog.dismiss();
            }
        } catch (Exception e) {

        }
    }

//    public static MultipartBody.Part convertFileToMultipart(String pathImageFile, String Key) {
//        if (pathImageFile != null) {
//            File file = new File(pathImageFile);
//            RequestBody reqFileselect = RequestBody.create(MediaType.parse("image/*"), file);
//            MultipartBody.Part Imagebody = MultipartBody.Part.createFormData(Key, file.getName(), reqFileselect);
//            return Imagebody;
//        } else {
//            return null;
//        }
//    }

//    public static RequestBody convertToRequestBody(String part) {
//        try {
//            if (!part.equals("")) {
//                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), part);
//                return requestBody;
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }

//    public static void openAlbum(int Counter, Context context, final ArrayList<AlbumFile> ImagesFiles, Action<ArrayList<AlbumFile>> action) {
//        Album album = new Album();
//        Album.initialize(AlbumConfig.newBuilder(context)
//                .setAlbumLoader(new MediaLoader())
//                .setLocale(Locale.ENGLISH).build());
//        album.image(context)// Image and video mix options.
//                .multipleChoice()// Multi-Mode, Single-Mode: singleChoice().
//                .columnCount(3) // The number of columns in the page list.
//                .selectCount(Counter)  // Choose up to a few images.
//                .camera(true) // Whether the camera appears in the Item.
//                .checkedList(ImagesFiles) // To reverse the list.
//                .widget(
//                        Widget.newLightBuilder(context)
//                                .title("")
//                                .statusBarColor(Color.WHITE) // StatusBar color.
//                                .toolBarColor(Color.WHITE) // Toolbar color.
//                                .navigationBarColor(Color.WHITE) // Virtual NavigationBar color of Android5.0+.
//                                .mediaItemCheckSelector(Color.BLUE, Color.GREEN) // Image or video selection box.
//                                .bucketItemCheckSelector(Color.RED, Color.YELLOW) // Select the folder selection box.
//                                .build()
//                )
//                .onResult(action)
//                .onCancel(new Action<String>() {
//                    @Override
//                    public void onAction(@NonNull String result) {
//// The user canceled the operation.
//                    }
//                })
//                .start();
//    }

    //checkpassword&&ConfirmPassword
    public static boolean checkCorrespondPassword(String newPassword, String ConfirmPassword) {
        return newPassword.equals(ConfirmPassword);
    }

    // check length password
    public static boolean checkLengthPassword(String newPassword) {
        return newPassword.length() > 6;
    }


}
