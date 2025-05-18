package com.example.lr4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lr1.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ValuesFragment extends Fragment {

    private static final String FILENAME = "result_lr3.txt";

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             @Nullable ViewGroup parent,
                             @Nullable Bundle state) {
        return inf.inflate(R.layout.fragment_values, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle state) {
        TextView tv = v.findViewById(R.id.tvValues);

        try (BufferedReader r = new BufferedReader(
                new InputStreamReader(requireContext().openFileInput(FILENAME)))) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) sb.append(line).append('\n');
            tv.setText(sb.toString());

        } catch (Exception e) {
            tv.setText("Файл не знайдено. Збережи дані у ЛР-3 ☝️");
        }
    }
}
