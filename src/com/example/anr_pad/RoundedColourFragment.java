package com.example.anr_pad;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.actionbarsherlock.app.SherlockFragment;

import net.simonvt.widget.NumberPicker;

public class RoundedColourFragment extends SherlockFragment {

    private View mView;
    private int mColour;
    private float mWeight;
    private int marginLeft, marginRight, marginTop, marginBottom;

    // need a public empty constructor for framework to instantiate
    public RoundedColourFragment() {

    }

    public RoundedColourFragment(int colour, float weight, int margin_left,
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
        mView = new View(getActivity());

        GradientDrawable background = (GradientDrawable) getResources()
                .getDrawable(R.drawable.rounded_rect);
        background.setColor(mColour);

        mView.setBackgroundDrawable(background);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0,
                LayoutParams.FILL_PARENT, mWeight);
        lp.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        mView.setLayoutParams(lp);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //return mView;

        Context context = new ContextThemeWrapper(getActivity(), R.style.SampleTheme);
        LayoutInflater localInflater = inflater.cloneInContext(context);
        View v = localInflater.inflate(R.layout.fragment_runner, container, false);

        NumberPicker creditsEdit = (NumberPicker) v.findViewById(R.id.CreditsEdit);
        creditsEdit.setMaxValue(99);
        creditsEdit.setMinValue(0);
        creditsEdit.setFocusable(true);
        creditsEdit.setFocusableInTouchMode(true);

        NumberPicker APEdit = (NumberPicker) v.findViewById(R.id.APEdit);
        APEdit.setMaxValue(99);
        APEdit.setMinValue(0);
        APEdit.setFocusable(true);
        APEdit.setFocusableInTouchMode(true);
        return v;
    }

}
