package com.bignerdranch.android.parkinsons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import org.achartengine.chart.LineChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


public class ChartView extends View {
    private String TAG = "MakeChart";
    private int chart_ver = 0;

    private LineChart mLineChart = null;
    private Paint paint = new Paint();

    public ChartView(Context context) {
        super(context);
        setFocusable(true);
    }

    public ChartView(Context context, AttributeSet attrs){
        super(context, attrs);
        setFocusable(true);
    }

    public ChartView(Context context, AttributeSet attrs, int defaultStyle)
    {
        super(context, attrs, defaultStyle);
        setFocusable(true);
    }

    public void makeTouchChart(double[] test_data){
        chart_ver = 1;

        int[] x = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        double[] p_time = {1.326, 5.272, 6.693, 1.107, 2.34, 0.952, 5.943, 1.155, 1.31, 0.874, 2.028};
        double[] n_time = {0.749, 0.405, 0.437, 0.468, 0.453, 0.436, 0.687, 0.343, 0.375, 0.499, 0.405};

        XYSeries patientSeries = new XYSeries("환자");
        XYSeries normalSeries = new XYSeries("일반인");
        XYSeries testerSeries = new XYSeries("검사자");

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        for(int i=0; i<11; i++){
            patientSeries.add(x[i], p_time[i]);
            normalSeries.add(x[i], n_time[i]);
            testerSeries.add(x[i], test_data[i]);
        }

        dataset.addSeries(patientSeries);
        dataset.addSeries(normalSeries);
        dataset.addSeries(testerSeries);

        XYSeriesRenderer patientRenderer = new XYSeriesRenderer();
        patientRenderer.setColor(Color.RED);
        patientRenderer.setPointStyle(PointStyle.CIRCLE);
        patientRenderer.setFillPoints(true);
        patientRenderer.setLineWidth(10);
        patientRenderer.setDisplayChartValues(false);

        XYSeriesRenderer normalRenderer = new XYSeriesRenderer();
        normalRenderer.setColor(Color.rgb(65, 200, 85));
        normalRenderer.setPointStyle(PointStyle.CIRCLE);
        normalRenderer.setFillPoints(true);
        normalRenderer.setLineWidth(10);
        normalRenderer.setDisplayChartValues(false);

        XYSeriesRenderer testerRenderer = new XYSeriesRenderer();
        testerRenderer.setColor(Color.BLUE);
        testerRenderer.setPointStyle(PointStyle.CIRCLE);
        testerRenderer.setFillPoints(true);
        testerRenderer.setLineWidth(10);
        testerRenderer.setDisplayChartValues(false);

        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

        multiRenderer.addSeriesRenderer(patientRenderer);
        multiRenderer.addSeriesRenderer(normalRenderer);
        multiRenderer.addSeriesRenderer(testerRenderer);
        multiRenderer.setLegendTextSize(50);

        // Creating a Line Chart
        mLineChart = new LineChart(dataset, multiRenderer);

        invalidate();
    }

    public void makeShakeChart(double[] test_data){
        chart_ver = 2;
        double[] p_time = {426, 372, 293, 207, 435, 452, 153, 315, 161, 474, 326, 372, 223, 407, 235, 152, 153, 215, 461, 474, 226, 372, 393, 177, 255, 452, 253, 225, 311, 164};
        double[] n_time = {11, 24, 22, 13, 4, 10, 25, 13, 28, 53, 36, 17, 22, 5, 19, 34, 23, 33, 34, 14, 9, 1, 8, 14, 22, 31, 27, 19, 16, 33};

        XYSeries patientSeries = new XYSeries("환자");
        XYSeries normalSeries = new XYSeries("일반인");
        XYSeries testerSeries = new XYSeries("검사자");

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

        for(int i=0; i<30; i++){
            patientSeries.add(i+1, p_time[i]);
            normalSeries.add(i+1, n_time[i]);
            testerSeries.add(i+1, test_data[i]);
        }

        dataset.addSeries(patientSeries);
        dataset.addSeries(normalSeries);
        dataset.addSeries(testerSeries);

        XYSeriesRenderer patientRenderer = new XYSeriesRenderer();
        patientRenderer.setColor(Color.RED);
        patientRenderer.setFillPoints(true);
        patientRenderer.setLineWidth(5);
        patientRenderer.setDisplayChartValues(false);

        XYSeriesRenderer normalRenderer = new XYSeriesRenderer();
        normalRenderer.setColor(Color.rgb(65, 200, 85));
        normalRenderer.setFillPoints(true);
        normalRenderer.setLineWidth(5);
        normalRenderer.setDisplayChartValues(false);

        XYSeriesRenderer testerRenderer = new XYSeriesRenderer();
        testerRenderer.setColor(Color.BLUE);
        testerRenderer.setFillPoints(true);
        testerRenderer.setLineWidth(5);
        testerRenderer.setDisplayChartValues(false);

        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

        multiRenderer.addSeriesRenderer(patientRenderer);
        multiRenderer.addSeriesRenderer(normalRenderer);
        multiRenderer.addSeriesRenderer(testerRenderer);
        multiRenderer.setLegendTextSize(50);
        // Creating a Line Chart
        mLineChart = new LineChart(dataset, multiRenderer);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        paint.setColor(Color.BLACK);

        if (mLineChart != null) {
            mLineChart.draw(canvas, 0, 0, width - 10, height - 10, paint);
            Log.i(TAG, "Make version: " + chart_ver);
        }
    }
}
