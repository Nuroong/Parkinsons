package com.bignerdranch.android.parkinsons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import org.achartengine.chart.DialChart;
import org.achartengine.chart.PieChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.DialRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class DialChartView extends View {
    private DialChart mDialChart = null;
    private PieChart mPieChart = null;
    private Paint paint = new Paint();

    public DialChartView(Context context) {
        super(context);
        setFocusable(true);
    }

    public void makeBrainDialChart() {
        int[] colors = { Color.rgb(65, 200, 85), Color.rgb(255, 222, 0), Color.RED};
        double[] divide = {9, 5, 4};

        CategorySeries pie_dataset = new CategorySeries("");
        DefaultRenderer pie_renderer = new DefaultRenderer();
        pie_renderer.setStartAngle(180);
        pie_renderer.setShowLegend(false);
        pie_renderer.setShowLabels(false);

        for(int i=0; i<3; i++) {
            pie_dataset.add(divide[i]);
            SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
            simpleSeriesRenderer.setColor(colors[i]);
            pie_renderer.addSeriesRenderer(simpleSeriesRenderer);
        }

        CategorySeries dial_dataset = new CategorySeries("");
        DialRenderer dial_renderer = new DialRenderer();
        dial_renderer.setLabelsColor(Color.BLACK);
        dial_renderer.setMaxValue(180);
        dial_renderer.setMinValue(0);
        dial_renderer.setAngleMax(-90);
        dial_renderer.setAngleMin(270);
        dial_renderer.setMajorTicksSpacing(30);
        dial_renderer.setMinorTicksSpacing(5);
        dial_renderer.setShowLegend(false);
        dial_renderer.setShowLabels(false);

        mPieChart = new PieChart(pie_dataset, pie_renderer);
        mDialChart = new DialChart(dial_dataset, dial_renderer);

        invalidate();
    }

    public void makeDialChart() {
        int[] colors = { Color.rgb(65, 200, 85), Color.rgb(255, 222, 0), Color.RED};
        double[] divide = {3, 9, 6};

        CategorySeries pie_dataset = new CategorySeries("");
        DefaultRenderer pie_renderer = new DefaultRenderer();
        pie_renderer.setStartAngle(180);
        pie_renderer.setShowLegend(false);
        pie_renderer.setShowLabels(false);

        for(int i=0; i<3; i++) {
            pie_dataset.add(divide[i]);
            SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
            simpleSeriesRenderer.setColor(colors[i]);
            pie_renderer.addSeriesRenderer(simpleSeriesRenderer);
        }

        CategorySeries dial_dataset = new CategorySeries("");
        DialRenderer dial_renderer = new DialRenderer();
        dial_renderer.setLabelsColor(Color.BLACK);
        dial_renderer.setMaxValue(180);
        dial_renderer.setMinValue(0);
        dial_renderer.setAngleMax(-90);
        dial_renderer.setAngleMin(270);
        dial_renderer.setMajorTicksSpacing(30);
        dial_renderer.setMinorTicksSpacing(5);
        dial_renderer.setShowLegend(false);
        dial_renderer.setShowLabels(false);

        mPieChart = new PieChart(pie_dataset, pie_renderer);
        mDialChart = new DialChart(dial_dataset, dial_renderer);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        paint.setColor(Color.BLACK);

        if (mPieChart != null) {
            mPieChart.draw(canvas, 0, 0, width, height-10, paint);
        }
        if (mDialChart != null) {
            mDialChart.draw(canvas, 0, 0, width, height-10, paint);
        }
    }
}
