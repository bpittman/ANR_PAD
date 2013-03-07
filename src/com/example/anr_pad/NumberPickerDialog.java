package com.example.anr_pad;

import net.simonvt.numberpicker.NumberPicker;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockDialogFragment;

public class NumberPickerDialog extends SherlockDialogFragment {

    private NumberPicker mNumberPicker;

    public NumberPickerDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	Context context = new ContextThemeWrapper(getActivity(), R.style.SampleTheme);
        LayoutInflater localInflater = inflater.cloneInContext(context);
        View view = localInflater.inflate(R.layout.popup, container, false);
        mNumberPicker = (NumberPicker) view.findViewById(R.id.popupNumberPicker);
        initializeNumberPicker(mNumberPicker,0,10,0);
        getDialog().setTitle("Set AP");

        return view;
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