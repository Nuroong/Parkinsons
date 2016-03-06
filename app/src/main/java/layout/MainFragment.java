package layout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.android.parkinsons.AgeDialogFragment;
import com.bignerdranch.android.parkinsons.HowtoActivity;
import com.bignerdranch.android.parkinsons.R;

public class MainFragment extends Fragment {
    public static final String DIALOG_AGE = "age";

    public static final String ARG_PARAM = "main_fragmentVER";

    private static final String TAG = "MainFragment";
    private int mver;

    public MainFragment() {
        setArguments(getArguments());
    }

    public static MainFragment newInstance(int version) {
        MainFragment f = new MainFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, version);
        f.setArguments(args);

        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mver = getArguments().getInt(ARG_PARAM);    //여기서 오류남
            Log.i(TAG, "Fragment" + mver + " created");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        ImageButton Button1 = (ImageButton) rootView.findViewById(R.id.TestBtn1);
        ImageButton Button2 = (ImageButton) rootView.findViewById(R.id.TestBtn2);
        TextView Text1 = (TextView) rootView.findViewById(R.id.TestText1);
        TextView Text2 = (TextView) rootView.findViewById(R.id.TestText2);

        if(mver == 0) {
            Button1.setImageResource(R.drawable.hand_touch_icon);
            Button2.setImageResource(R.drawable.hand_shake_icon);
            Text1.setText(R.string.title_activity_touch);
            Text2.setText(R.string.title_activity_shake);

            Button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getFragmentManager();
                    AgeDialogFragment mAgeDialog = AgeDialogFragment.newInstance(0);
                    mAgeDialog.show(fm, DIALOG_AGE);
                }
            });

            Button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getFragmentManager();
                    AgeDialogFragment mAgeDialog = AgeDialogFragment.newInstance(1);
                    mAgeDialog.show(fm, DIALOG_AGE);
                }
            });
        }
        else {
            Button1.setImageResource(R.drawable.brain_icon);
            Button2.setImageResource(R.drawable.howto_icon);
            Text1.setText(R.string.title_activity_brain);
            Text2.setText(R.string.title_activity_howto);

            Button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = getFragmentManager();
                    AgeDialogFragment mAgeDialog = AgeDialogFragment.newInstance(2);
                    mAgeDialog.show(fm, DIALOG_AGE);
                }
            });

            Button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity().getApplication(), HowtoActivity.class));
                }
            });
        }

        Log.i(TAG, "Fragment" + mver + " created view");
        return rootView;
    }
}
