package br.edu.farol.gadoplus.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import br.edu.farol.gadoplus.R;
import br.edu.farol.gadoplus.model.GastoTotalizado;
import br.edu.farol.gadoplus.ui.gastos.GastosViewModel;

public class HomeFragment extends Fragment {

    private GastosViewModel gastosViewModel;

    private PieChart mPieChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gastosViewModel =
                ViewModelProviders.of(this).get(GastosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);





        mPieChart = root.findViewById(R.id.chartGastos);
        mPieChart.setNoDataText("Sem dados para exibir o relatório!");
        mPieChart.setNoDataTextColor(Color.rgb(44, 62, 80));



        gastosViewModel.getGastoToalizado().observe(this, new Observer<List<GastoTotalizado>>() {
            @Override
            public void onChanged(@Nullable List<GastoTotalizado> gastoTotalizados) {
                assert gastoTotalizados != null;
                if (gastoTotalizados.size()>0){
                    //Fazer a Magica Acontecer!
                    PieData mPieData = getPieData(gastoTotalizados);
                    showChart(mPieChart, mPieData);
                }

            }
        });

        return root;
    }

    private void showChart (PieChart pieChart,PieData pieData){

        pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleRadius(64f);


        Description description = new Description();
        description.setText(""); //não uso
        description.setTextSize(18f);
        pieChart.setDescription(description);

        pieChart.setExtraOffsets(5, 10, 5, 5);


        pieChart.setDrawCenterText(false);
        pieChart.setDrawHoleEnabled(true);

        pieChart.setRotationAngle(90);
        pieChart.setRotationEnabled(true);

        pieChart.setUsePercentValues(true);
        //pieChart.setCenterText("Conteudo Centro");


        pieChart.setData(pieData);

        pieChart.setEntryLabelColor(Color.rgb(52, 73, 94));

        pieChart.getLegend().setXEntrySpace(7f);
        pieChart.getLegend().setYEntrySpace(5f);

        pieChart.animateXY(1500, 1500);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getContext(), "R$ " + e.getY(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private PieData getPieData (List<GastoTotalizado> gastoTotalizados){

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (GastoTotalizado gastoTotalizado: gastoTotalizados){
            entries.add(new PieEntry((float)gastoTotalizado.getValor(), gastoTotalizado.getNome()));
        }


        PieDataSet mPieDataSet = new PieDataSet(entries, "");
        mPieDataSet.setSliceSpace(2f);
        mPieDataSet.setValueTextColor(Color.rgb(44, 62, 80));
        mPieDataSet.setValueTextSize(20f);

        ArrayList<Integer>  colors =  new ArrayList<>();

        colors.add(Color.rgb(255, 73, 75));
        colors.add(Color.rgb(79, 145, 255));
        colors.add(Color.rgb(255, 152, 152));
        colors.add(Color.rgb(252, 184, 64));
        colors.add(Color.rgb(140, 201, 158));

       for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());


        mPieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5*(metrics.densityDpi/160f);
        mPieDataSet.setSelectionShift(px);

        PieData pieData = new PieData(mPieDataSet);

        pieData.setValueFormatter(new PercentFormatter());
        return pieData;
    }


}