package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.parkinsons.HowtoContents;
import com.bignerdranch.android.parkinsons.R;

public class HowtoFragment extends Fragment {
    public static final String ARG_PARAM1 = "howto_img";
    public static final String ARG_PARAM2 = "howto_title";
    public static final String ARG_PARAM3 = "howto_txt";

    private int mImage;
    private String mTitle;
    private String mText;

    public HowtoFragment() {
        setArguments(getArguments());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImage = getArguments().getInt(ARG_PARAM1);
            mTitle = getArguments().getString(ARG_PARAM2);
            mText = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_howto, container, false);
        ((ImageView) rootView.findViewById(R.id.howtoImage)).setImageResource(mImage);
        ((TextView) rootView.findViewById(R.id.howtoTitle)).setText(mTitle);
        ((TextView) rootView.findViewById(R.id.howtoText)).setText(mText);

        return rootView;
    }
}
