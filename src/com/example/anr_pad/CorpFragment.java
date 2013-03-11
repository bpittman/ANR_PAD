package com.example.anr_pad;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.anr_pad.NumberPickerDialog.NumberPickerDialogListener;

import net.simonvt.numberpicker.NumberPicker;

public class CorpFragment extends SherlockFragment implements NumberPickerDialogListener{

    private View mView;
    private int mColour;
    private float mWeight;
    private int marginLeft, marginRight, marginTop, marginBottom;
    private OnClickListener clickListener;
    private MainActivity mActivity;

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

        initializeNumberPicker(v.findViewById(R.id.corpCreditsEdit),0,99,5);

        clickListener = new OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch(v.getId()) {
                   case R.id.corpClicksValue:
                      clicksLabelClicked(v);
                      break;
                   case R.id.corpAPLabel:
                      APLabelClicked(v);
                      break;
                   case R.id.corpBadPublicityLabel:
                       badPublicityLabelClicked(v);
                       break;
                }
            }
        };

        mActivity = (MainActivity) getActivity();

        TextView corpClicksTextView = (TextView) v.findViewById(R.id.corpClicksValue);
        TextView corpAPTextView = (TextView) v.findViewById(R.id.corpAPLabel);
        TextView corpBadPublicityTextView = (TextView) v.findViewById(R.id.corpBadPublicityLabel);

        corpClicksTextView.setOnClickListener(clickListener);
        corpAPTextView.setOnClickListener(clickListener);
        corpBadPublicityTextView.setOnClickListener(clickListener);

        corpClicksTextView.setText(Integer.toString(mActivity.getCorpClicks()));
        corpAPTextView.setText(mActivity.getCorpAP() + " " + getString(R.string.agenda_points));
        corpBadPublicityTextView.setText(mActivity.getCorpBadPublicity() + " " + getString(R.string.bad_publicity));

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

    public void clicksLabelClicked(View v) {
        int current = mActivity.getCorpClicks() - 1;
        if (current < 0) current = 3;
        mActivity.setCorpClicks(current);
    }

    public void APLabelClicked(View v) {
        FragmentManager fm = getChildFragmentManager();
        NumberPickerDialog numberPickerDialog = NumberPickerDialog.newInstance(
                this, getString(R.string.agenda_points), 0, 10, mActivity.getCorpAP());
        numberPickerDialog.show(fm, "fragment_set_ap");
    }

    public void badPublicityLabelClicked(View v) {
        FragmentManager fm = getChildFragmentManager();
        NumberPickerDialog numberPickerDialog = NumberPickerDialog.newInstance(
                this, getString(R.string.bad_publicity), 0, 99, mActivity.getCorpBadPublicity());
        numberPickerDialog.show(fm, "fragment_set_bad_publicity");
    }

    @Override
    public void dialogValueSet(String name, int value) {
        if(name == getString(R.string.agenda_points)) {
            mActivity.setCorpAP(value);
        }
        else if(name == getString(R.string.bad_publicity)) {
            mActivity.setCorpBadPublicity(value);
        }
    }
}
