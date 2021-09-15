package com.yakravets.retrofit21;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yakravets.retrofit21.fragments.ListViewFragment;
import com.yakravets.retrofit21.fragments.RecyclerFragment;

public class PageAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "ListView", "RecyclerView" };
    private Context context;

    public PageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override public int getCount() {
        return PAGE_COUNT;
    }

    @Override public Fragment getItem(int position) {
        position +=1;

        Fragment fr;
        switch (position)
        {
            case 1:
                fr = ListViewFragment.newInstance();
                break;
            case 2:
                fr = RecyclerFragment.newInstance();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return fr;
    }

    @Override public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
