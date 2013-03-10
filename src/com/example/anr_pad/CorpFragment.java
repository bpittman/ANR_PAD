package com.example.anr_pad;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;
import net.simonvt.numberpicker.NumberPicker;

public class CorpFragment extends SherlockFragment {

    private View mView;
    private int mColour;
    private float mWeight;
    private int marginLeft, marginRight, marginTop, marginBottom;

    // need a public empty constructor for framework to instantiate
    public CorpFragment() {

    }

    public CorpFragment(int colour, float weight, int margin_left,
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
        mView.setLayoutParams(lp);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //return mView;

        View v = inflater.inflate(R.layout.fragment_corp, container, false);

        initializeNumberPicker(v.findViewById(R.id.CorpCreditsEdit),0,99,5);
        initializeNumberPicker(v.findViewById(R.id.CorpAPEdit),0,9,0);
        initializeNumberPicker(v.findViewById(R.id.BadPublicityEdit),0,99,0);

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
}
