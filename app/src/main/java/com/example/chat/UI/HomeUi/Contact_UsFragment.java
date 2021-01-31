package com.example.chat.UI.HomeUi;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.chat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contact_UsFragment extends Fragment {


    private FrameLayout frameLayout;

    public Contact_UsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_contact__us, container, false);

        frameLayout = inflate.findViewById(R.id.Frame_layout1);
        Toolbar toolbar1 = inflate.findViewById(R.id.toolbar1);
        toolbar1.setTitle(getResources().getString(R.string.app_name));
        return inflate;

    }

}
