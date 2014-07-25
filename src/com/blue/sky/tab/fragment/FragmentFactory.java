package com.blue.sky.tab.fragment;

import android.app.Fragment;
import com.blue.sky.code.home.HomeFragment;
import com.blue.sky.component.R;

/**
 * Created by admin on 13-11-23.
 */
public class FragmentFactory {
    public static Fragment getInstanceByIndex(int index) {
        Fragment fragment = null;
        switch (index) {
            case R.id.tab_home:
                fragment = new HomeFragment();
                break;
            case R.id.tab_code:
                fragment = new AtmeFragment();
                break;
            case R.id.tab_mobile:
                fragment = new CommentFragment();
                break;
            case R.id.tab_web:
                fragment = new MyListFragment();
                break;
            case R.id.tab_blog:
                fragment = new GlobalFragment();
                break;
        }
        return fragment;
    }
}
