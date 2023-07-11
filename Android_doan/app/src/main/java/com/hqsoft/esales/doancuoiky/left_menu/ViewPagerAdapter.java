package com.hqsoft.esales.doancuoiky.left_menu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new fragment_home_index();
            case 1:
                return new fragment_home_compiler();
            case 2:
                return new fragment_home_program();
            case 3:
                return new fragment_home_description();
            default:
                return new fragment_home();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Index";
                break;
            case 1:
                title = "Compiler";
                break;
            case 2:
                title = "Program";
                break;
            case 3:
                title = "Description";
                break;

        }
        return title;
    }
}
