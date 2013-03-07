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

import com.actionbarsherlock.app.SherlockFragment;
import net.simonvt.numberpicker.NumberPicker;

public class RunnerFragment extends SherlockFragment {

    private View mView;
    private int mColour;
    private float mWeight;
    private int marginLeft, marginRight, marginTop, marginBottom;
    private OnClickListener clickListener;

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

        Context context = new ContextThemeWrapper(getActivity(), R.style.SampleTheme);
        LayoutInflater localInflater = inflater.cloneInContext(context);
        View v = localInflater.inflate(R.layout.fragment_runner, container, false);

        initializeNumberPicker(v.findViewById(R.id.CreditsEdit),0,99,5);

        clickListener = new OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch(v.getId()) {
                   case R.id.runnerAPLabel:
                      APLabelClicked(v);
                      break;
                }
            }
        };

        v.findViewById(R.id.runnerAPLabel).setOnClickListener(clickListener);

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
        NumberPickerDialog numberPickerDialog = new NumberPickerDialog();
        numberPickerDialog.show(fm, "fragment_edit_name");
    }
}
