package com.example.chat.model;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chat.UI.HomeUi.UsersFragment;
import com.example.chat.UI.HomeUi.CameraFragment;
import com.example.chat.UI.HomeUi.ChattingFragment;
import com.example.chat.UI.HomeUi.StatusFragment;


public class Tab_Home_Adapter extends FragmentPagerAdapter {

    Fragment fragments []= {new CameraFragment(), new ChattingFragment(), new StatusFragment(),new UsersFragment()};
    private String title;
//    private List<Fragment> fragmentList = new ArrayList<>();
//    private List<String> stringList = new ArrayList<>();

    public Tab_Home_Adapter(FragmentManager childFragmentManager) {
        super(childFragmentManager);
    }



    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0){
            title = "";
        }
        if (position == 1){
            title = " الدردشة";
        }
        if (position == 2){
            title = "الحالة";
        }
        if (position == 3){
            title = "المستخدم";
        }
        return title;
   }




//    public void  addFragment(Fragment fragment ,String title){
//
//       fragmentList.add(fragment);
//       stringList.add(title);
//    }
}
