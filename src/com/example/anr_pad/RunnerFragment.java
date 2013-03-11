package com.example.anr_pad;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.anr_pad.NumberPickerDialog.NumberPickerDialogListener;

import net.simonvt.numberpicker.NumberPicker;

public class RunnerFragment extends SherlockFragment implements NumberPickerDialogListener{

    private View mView;
    private int mColour;
    private float mWeight;
    private int marginLeft, marginRight, marginTop, marginBottom;
    private OnClickListener clickListener;
    private MainActivity mActivity;

    // need a public empty constructor for framework to instantiate
    public RunnerFragment() {

    }

    public RunnerFragment(int colour, float weight, int margin_left,
            int margin_right, int margin_top, int margin_bottom) {
        mColour = colour;
        mWeight = weight;
        marginLeft = margin_left;
        marginRight = margin_right;
        marginTop = margin_top;
        marginBottom = margin_bottom;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*mView = new View(getActivity());

        GradientDrawable background = (GradientDrawable) getResources()
                .getDrawable(R.drawable.rounded_rect);
        background.setColor(mColour);

        mView.setBackgroundDrawable(background);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                LayoutParams.FILL_PARENT, mWeight);
        lp.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        mView.setLayoutParams(lp);
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //return mView;

        View v = inflater.inflate(R.layout.fragment_runner, container, false);

        initializeNumberPicker(v.findViewById(R.id.runnerCreditsEdit),0,99,5);

        clickListener = new OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch(v.getId()) {
                   case R.id.runnerAPLabel:
                      APLabelClicked(v);
                      break;
                   case R.id.runnerLinkLabel:
                       linkLabelClicked(v);
                       break;
                   case R.id.runnerTagsLabel:
                       tagsLabelClicked(v);
                       break;
                   case R.id.runnerBrainDamageLabel:
                       brainDamageLabelClicked(v);
                       break;
                }
            }
        };

        mActivity = (MainActivity) getActivity();

        TextView runnerAPTextView = (TextView) v.findViewById(R.id.runnerAPLabel);
        TextView runnerLinkTextView = (TextView) v.findViewById(R.id.runnerLinkLabel);
        TextView runnerTagsTextView = (TextView) v.findViewById(R.id.runnerTagsLabel);
        TextView runnerBDTextView = (TextView) v.findViewById(R.id.runnerBrainDamageLabel);

        runnerAPTextView.setOnClickListener(clickListener);
        runnerLinkTextView.setOnClickListener(clickListener);
        runnerTagsTextView.setOnClickListener(clickListener);
        runnerBDTextView.setOnClickListener(clickListener);

        runnerAPTextView.setText(mActivity.getRunnerAP() + " " + getString(R.string.agenda_points));
        runnerLinkTextView.setText(mActivity.getRunnerLink() + " " + getString(R.string.link));
        runnerTagsTextView.setText(mActivity.getRunnerTags() + " " + getString(R.string.tags));
        runnerBDTextView.setText(mActivity.getRunnerBrainDamage() + " " + getString(R.string.brain_damage));

        return v;
    }

    private void initializeNumberPicker(View v, int min, int max, int value)
    {
        NumberPicker n = (NumberPicker) v;
        n.setMaxValue(max);
        n.setMinValue(min);
        n.setValue(value);
        n.setFocusable(true);
        n.setFocusableInTouchMode(true);
        return;
    }

    public void APLabelClicked(View v) {
        FragmentManager fm = getChildFragmentManager();
        NumberPickerDialog numberPickerDialog = NumberPickerDialog.newInstance(
                this, getString(R.string.agenda_points), 0, 10, mActivity.getRunnerAP());
        numberPickerDialog.show(fm, "fragment_set_ap");
    }

    public void linkLabelClicked(View v) {
        FragmentManager fm = getChildFragmentManager();
        NumberPickerDialog numberPickerDialog = NumberPickerDialog.newInstance(
                this, getString(R.string.link), 0, 99, mActivity.getRunnerLink());
        numberPickerDialog.show(fm, "fragment_set_link");
    }

    public void tagsLabelClicked(View v) {
        FragmentManager fm = getChildFragmentManager();
        NumberPickerDialog numberPickerDialog = NumberPickerDialog.newInstance(
                this, getString(R.string.tags), 0, 99, mActivity.getRunnerTags());
        numberPickerDialog.show(fm, "fragment_set_tags");
    }

    public void brainDamageLabelClicked(View v) {
        FragmentManager fm = getChildFragmentManager();
        NumberPickerDialog numberPickerDialog = NumberPickerDialog.newInstance(
                this, getString(R.string.brain_damage), 0, 99, mActivity.getRunnerBrainDamage());
        numberPickerDialog.show(fm, "fragment_set_brain_damage");
    }

    @Override
    public void dialogValueSet(String name, int value) {
        if(name == getString(R.string.agenda_points)) {
            mActivity.setRunnerAP(value);
        }
        else if(name == getString(R.string.link)) {
            mActivity.setRunnerLink(value);
        }
        else if(name == getString(R.string.tags)) {
            mActivity.setRunnerTags(value);
        }
        else if(name == getString(R.string.brain_damage)) {
            mActivity.setRunnerBrainDamage(value);
        }
    }
}
