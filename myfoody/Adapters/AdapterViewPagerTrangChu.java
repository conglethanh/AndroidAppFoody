package com.cntn16.vuongcong.myfoody.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cntn16.vuongcong.myfoody.View.Fragments.AnGiFragment;
import com.cntn16.vuongcong.myfoody.View.Fragments.OdauFragment;



public class AdapterViewPagerTrangChu extends FragmentStatePagerAdapter {
    AnGiFragment anGiFragment;
    OdauFragment odauFragment;

    public AdapterViewPagerTrangChu(FragmentManager fm) {
        super(fm);
        anGiFragment = new AnGiFragment();
        odauFragment = new OdauFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return odauFragment;

            case 1:
                return anGiFragment;

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
