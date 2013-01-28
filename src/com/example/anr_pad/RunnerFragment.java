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

public class RunnerFragment extends SherlockFragment {

    private View mView;
    private int mColour;
    private float mWeight;
    private int marginLeft, marginRight, marginTop, marginBottom;

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

        initializeNumberPicker(v.findViewById(R.id.CreditsEdit));
        initializeNumberPicker(v.findViewById(R.id.APEdit));
        initializeNumberPicker(v.findViewById(R.id.TagsEdit));
        initializeNumberPicker(v.findViewById(R.id.BrainDamageEdit));

        return v;
    }

    private void initializeNumberPicker(View v)
    {
        NumberPicker n = (NumberPicker) v;
        n.setMaxValue(99);
        n.setMinValue(0);
        n.setFocusable(true);
        n.setFocusableInTouchMode(true);
        return;
    }
}
