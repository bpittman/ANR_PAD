package com.example.anr_pad;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends SherlockFragmentActivity
                          implements ActionBar.TabListener {

    private RunnerFragment runnerFrag;
    private CorpFragment corpFrag;

    private int runnerClicks = 4;
    private int runnerCredits = 5;
    private int runnerAP = 0;
    private int runnerLink = 0;
    private int runnerTags = 0;
    private int runnerBrainDamage = 0;

    private int corpClicks = 3;
    private int corpCredits = 5;
    private int corpAP = 0;
    private int corpBadPublicity = 0;

    public int getRunnerClicks() {
        return runnerClicks;
    }

    public int getRunnerCredits() {
        return runnerCredits;
    }

    public int getRunnerAP() {
        return runnerAP;
    }

    public int getRunnerLink() {
        return runnerLink;
    }

    public int getRunnerTags() {
        return runnerTags;
    }

    public int getRunnerBrainDamage() {
        return runnerBrainDamage;
    }

    public int getCorpClicks() {
        return corpClicks;
    }

    public int getCorpCredits() {
        return corpCredits;
    }

    public int getCorpAP() {
        return corpAP;
    }

    public int getCorpBadPublicity() {
        return corpBadPublicity;
    }

    public void setRunnerClicks(int value) {
        runnerClicks = value;
        TextView runnerClicksTextView = (TextView) runnerFrag.getView().findViewById(R.id.runnerClicksValue);
        runnerClicksTextView.setText(Integer.toString(value));
    }

    public void setRunnerCredits(int value) {
        runnerCredits = value;
    }

    public void setRunnerAP(int value) {
        runnerAP = value;
        TextView runnerAPTextView = (TextView) runnerFrag.getView().findViewById(R.id.runnerAPLabel);
        runnerAPTextView.setText(Integer.toString(value) + " " + getString(R.string.agenda_points));
    }

    public void setRunnerLink(int value) {
        runnerLink = value;
        TextView runnerLinkTextView = (TextView) runnerFrag.getView().findViewById(R.id.runnerLinkLabel);
        runnerLinkTextView.setText(Integer.toString(value) + " " + getString(R.string.link));
    }

    public void setRunnerTags(int value) {
        runnerTags = value;
        TextView runnerTagsTextView = (TextView) runnerFrag.getView().findViewById(R.id.runnerTagsLabel);
        runnerTagsTextView.setText(Integer.toString(value) + " " + getString(R.string.tags));
    }

    public void setRunnerBrainDamage(int value) {
        runnerBrainDamage = value;
        TextView runnerBDTextView = (TextView) runnerFrag.getView().findViewById(R.id.runnerBrainDamageLabel);
        runnerBDTextView.setText(Integer.toString(value) + " " + getString(R.string.brain_damage));
    }

    public void setCorpClicks(int value) {
        corpClicks = value;
        TextView corpClicksTextView = (TextView) corpFrag.getView().findViewById(R.id.corpClicksValue);
        corpClicksTextView.setText(Integer.toString(value));
    }

    public void setCorpAP(int value) {
        corpAP = value;
        TextView corpAPTextView = (TextView) corpFrag.getView().findViewById(R.id.corpAPLabel);
        corpAPTextView.setText(Integer.toString(value) + " " + getString(R.string.agenda_points));
    }

    public void setCorpBadPublicity(int value) {
        corpBadPublicity = value;
        TextView corpBadPublicityTextView = (TextView) corpFrag.getView().findViewById(R.id.corpBadPublicityLabel);
        corpBadPublicityTextView.setText(Integer.toString(value) + " " + getString(R.string.bad_publicity));
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

		final ActionBar ab = getSupportActionBar();

        // set defaults for logo & home up
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayUseLogoEnabled(false);

        // create a couple of simple fragments as placeholders
        final int MARGIN = 16;
        runnerFrag = new RunnerFragment(getResources().getColor(
                R.color.android_green), 1f, MARGIN, MARGIN / 2, MARGIN, MARGIN);
        corpFrag = new CorpFragment(getResources().getColor(
                R.color.honeycombish_blue), 2f, MARGIN / 2, MARGIN, MARGIN,
                MARGIN);

        ab.addTab(ab.newTab().setText("Runner").setTabListener(this));
        ab.addTab(ab.newTab().setText("Corp").setTabListener(this));
        showTabsNav();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.root, runnerFrag);
        ft.add(R.id.root, corpFrag);
        ft.commit();
	}

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Boolean keepScreenOn = sharedPref.getBoolean("pref_screen_always_on", true);
        if(keepScreenOn) {
            Log.i("anr_pad","pref_screen_always_on true");
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        else {
            Log.i("anr_pad","pref_screen_always_on false");
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    private void showTabsNav() {
        ActionBar ab = getSupportActionBar();
        if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_TABS) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        switch (tab.getPosition()) {
        case 0:
            ft.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out,
                                   android.R.anim.fade_in,android.R.anim.fade_out);
            ft.show(runnerFrag);
            break;
        case 1:
            ft.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out,
                                   android.R.anim.fade_in,android.R.anim.fade_out);
            ft.show(corpFrag);
            break;
        }
    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        switch (tab.getPosition()) {
        case 0:
            ft.hide(runnerFrag);
            break;
        case 1:
            ft.hide(corpFrag);
            break;
        }
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // FIXME implement this
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
                case R.id.menu_settings:
                    startActivity (new Intent (this, SettingsActivity.class));
                    return true;
        }
        return false;
    }
}
