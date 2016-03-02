package layout;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bignerdranch.android.parkinsons.ChartView;
import com.bignerdranch.android.parkinsons.DialChartView;
import com.bignerdranch.android.parkinsons.R;

/**
 * Created by user on 2016-01-13.
 */
public class ShakeResultPagerFragment extends Fragment {
    public static final String ARG_PARAM1 = "res_pos";
    public static final String ARG_PARAM2 = "res_shake";
    public static final String ARG_PARAM3 = "res_data";

    private int mver;
    private double mshake_result;
    private double[] mdata;
    private ChartView mChartView;
    private DialChartView mDialChartView;

    public ShakeResultPagerFragment() {
        setArguments(getArguments());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mver = getArguments().getInt(ARG_PARAM1);
        mshake_result = getArguments().getDouble(ARG_PARAM2);
        mdata = getArguments().getDoubleArray(ARG_PARAM3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_result, container, false);

        if(mver == 0) {
            LinearLayout textlayout = (LinearLayout) rootView.findViewById(R.id.res_text_layout);
            textlayout.setVisibility(View.VISIBLE);

            FrameLayout dynamicLayout = (FrameLayout) rootView.findViewById(R.id.res_fragment_layout);
            FrameLayout.LayoutParams layoutParam = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            layoutParam.gravity = Gravity.CENTER;

            //차트를 출력하는 뷰객체(ChartView) 생성
            mDialChartView = new DialChartView(rootView.getContext());
            dynamicLayout.addView(mDialChartView, layoutParam);
            mDialChartView.makeDialChart();            //차트 그리기

            ImageView mNeedle = (ImageView) rootView.findViewById(R.id.res_needle);
            dynamicLayout.removeView(mNeedle);
            mNeedle.setImageBitmap(makeNeedle(mshake_result));
            dynamicLayout.addView(mNeedle, layoutParam);

            return rootView;
        }
        else {
            LinearLayout textlayout = (LinearLayout) rootView.findViewById(R.id.res_text_layout);
            textlayout.setVisibility(View.INVISIBLE);
            //차트를 출력하는 뷰객체(ChartView) 생성
            mChartView = new ChartView(rootView.getContext());
            FrameLayout dynamicLayout = (FrameLayout) rootView.findViewById(R.id.res_fragment_layout);

            //리니어 레이아웃에 차트뷰 추가( 폭, 높이 가득차게 )
            dynamicLayout.addView(mChartView, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

            mChartView.makeShakeChart(mdata);

            return rootView;
        }
    }

    public Bitmap makeNeedle(double angle) {
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.needle_black);

        Matrix mMatrix = new Matrix();
        if(angle*2 > 355) mMatrix.postRotate(355);
        else mMatrix.postRotate((float) (angle*2));

        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), mMatrix, true);
    }
}
