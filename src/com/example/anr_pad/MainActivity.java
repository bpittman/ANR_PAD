package com.example.anr_pad;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends SherlockFragmentActivity  implements ActionBar.TabListener {

    private RoundedColourFragment leftFrag;
    private RoundedColourFragment rightFrag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final ActionBar ab = getSupportActionBar();

        // set defaults for logo & home up
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayUseLogoEnabled(false);

        ab.addTab(ab.newTab().setText("Runner").setTabListener(this));
        ab.addTab(ab.newTab().setText("Corp").setTabListener(this));
        showTabsNav();

        // create a couple of simple fragments as placeholders
        final int MARGIN = 16;
        leftFrag = new RoundedColourFragment(getResources().getColor(
                R.color.android_green), 1f, MARGIN, MARGIN / 2, MARGIN, MARGIN);
        rightFrag = new RoundedColourFragment(getResources().getColor(
                R.color.honeycombish_blue), 2f, MARGIN / 2, MARGIN, MARGIN,
                MARGIN);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.root, leftFrag);
        ft.add(R.id.root, rightFrag);
        ft.commit();
	}

    private void showTabsNav() {
        ActionBar ab = getSupportActionBar();
        if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_TABS) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // FIXME implement this
    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // FIXME implement this
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // FIXME implement this
    }
}
