package com.bignerdranch.android.parkinsons;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.NumberPicker;

/**
 * Created by user on 2016-01-05.
 */
public class AgeDialogFragment extends android.app.DialogFragment {
    public static final String TEST_VERSION = "com.bignerdranch.android.parkinsons.test_version";
    public static final String TESTER_AGE = "com.bignerdranch.android.parkinsons.tester_age";

    private int mver;

    public static AgeDialogFragment newInstance(int version) {
        Bundle args = new Bundle();
        args.putSerializable(TEST_VERSION, version);

        AgeDialogFragment fragment = new AgeDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle SavedInsatanceState) {
        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_agepicker, null);

        mver = getArguments().getInt(TEST_VERSION);

        final NumberPicker numberPicker = (NumberPicker) v.findViewById(R.id.dialog_age_picker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setValue(50);
        numberPicker.setEnabled(true);
        numberPicker.setDividerDrawable(getResources().getDrawable(R.drawable.divider));
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                getArguments().putSerializable(TESTER_AGE, newVal);
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.id.age_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (mver) {
                            case 0:
                                startActivity(new Intent(getActivity(), TouchTestActivity.class));
                                break;
                            case 1:
                                startActivity(new Intent(getActivity(), ShakeTestActivity.class));
                                break;
                            case 2:
                                startActivity(new Intent(getActivity(), BrainTestActivity.class));
                                break;
                        }
                    }
                })
                .create();
    }
}
