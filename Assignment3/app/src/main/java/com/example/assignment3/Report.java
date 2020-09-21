package com.example.assignment3;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.Context.MODE_PRIVATE;

public class Report extends Fragment  {
    View vReport;
    private Button btPie;
    private Button btnBar;
    private Integer userid;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vReport = inflater.inflate(R.layout.fragment_report, container, false);
        btPie = (Button) vReport.findViewById(R.id.btnPie);
        btnBar = (Button) vReport.findViewById(R.id.btnBar);

        SharedPreferences settings = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userid = settings.getInt("userid",0);
        btPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vReport.getContext(), PieChartActivity.class);
                startActivity(intent);
            }
        });

        btnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vReport.getContext(), BarChartActivity.class);
                startActivity(intent);
            }
        });
        return vReport;
    }
}
