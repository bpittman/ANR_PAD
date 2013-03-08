package com.example.anr_pad;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;

import com.actionbarsherlock.app.SherlockDialogFragment;
import net.simonvt.numberpicker.NumberPicker;

public class NumberPickerDialog extends SherlockDialogFragment {

    private NumberPicker mNumberPicker;

    public NumberPickerDialog() {
        // Empty constructor required for DialogFragment
    }

    static NumberPickerDialog newInstance(String title, int min, int max, int value) {
        NumberPickerDialog f = new NumberPickerDialog();

        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("min", min);
        args.putInt("max", max);
        args.putInt("value", value);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        int min = getArguments().getInt("min");
        int max = getArguments().getInt("max");
        int value = getArguments().getInt("value");

        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View view = factory.inflate(R.layout.popup, null);

        mNumberPicker = (NumberPicker) view.findViewById(R.id.popupNumberPicker);
        initializeNumberPicker(mNumberPicker,min,max,value);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setView(view)
                .setPositiveButton(R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ((MainActivity)getActivity()).doPositiveClick();
                        }
                    }
                )
                .setNegativeButton(R.string.cancel,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ((MainActivity)getActivity()).doNegativeClick();
                        }
                    }
                )
                .create();
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