package com.example.lr4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lr1.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphFragment extends Fragment {

    private static final String FILENAME = "result_lr3.txt";

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             @Nullable ViewGroup parent,
                             @Nullable Bundle state) {
        return inf.inflate(R.layout.fragment_graph, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle state) {
        GraphView graph = v.findViewById(R.id.graph);
        List<DataPoint> pts = new ArrayList<>();

        try (BufferedReader r = new BufferedReader(
                new InputStreamReader(requireContext().openFileInput(FILENAME)))) {

            Pattern p = Pattern.compile("x\\s*=\\s*([\\d\\.\\-]+).*?=\\s*([\\d\\.\\-]+)");
            String line;
            while ((line = r.readLine()) != null) {
                Matcher m = p.matcher(line);
                if (m.find()) {
                    double x = Double.parseDouble(m.group(1));
                    double u = Double.parseDouble(m.group(2));
                    pts.add(new DataPoint(x, u));
                }
            }

        } catch (Exception e) {
            Toast.makeText(requireContext(), "Немає даних для графіка", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pts.isEmpty()) {
            Toast.makeText(requireContext(), "Файл порожній", Toast.LENGTH_SHORT).show();
            return;
        }

        LineGraphSeries<DataPoint> s = new LineGraphSeries<>(pts.toArray(new DataPoint[0]));
        graph.addSeries(s);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("x");
        graph.getGridLabelRenderer().setVerticalAxisTitle("U(x)");
    }
}
