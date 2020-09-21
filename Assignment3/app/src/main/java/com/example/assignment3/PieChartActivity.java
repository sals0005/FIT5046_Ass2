package com.example.assignment3;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {
    private PieChart pieChart;
    private List<PieEntry> pieEntries;
    private Integer userid;
    EditText datePicker=null;
    DatePicker picker;
    Button btnPie;
    Button btnBack;
    String datePickStr="";
    Date date;
    private String details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        pieChart = (PieChart) findViewById(R.id.pieChart);
        SharedPreferences settings = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userid = settings.getInt("userid",0);
        datePicker= (EditText) findViewById(R.id.dataPicker);
       // picker=(DatePicker)findViewById(R.id.datePicker1);
        btnPie = (Button) findViewById(R.id.btnPic);
       
        datePicker.setFocusable(false);
        datePicker.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    datePicker();
            }
        });
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });


        btnPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!datePicker.getText().toString().trim().equals(""))
                {
                    datePickStr = datePicker.getText().toString();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        date =sdf.parse(datePickStr);
                    }
                    catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
                    GenratePieChart genratePieChart = new GenratePieChart();
                    genratePieChart.execute();
                }else {
                    showText("Date Can not be empty.");
                }
            }
        });
    }
    private void datePicker() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(this,AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String date = year + "-" + (month+1) + "-" + day;
                datePicker.setText(date);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }
    public class GenratePieChart extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            details= RestClient.getDataforPie(userid,date);
            // data = details.split(",");
            return details;
        }
        @Override
        protected void onPostExecute(String details) {
            if (!details.equals("[]")) {
                Integer userReport[] = new Integer[3];
                try {
                    JSONArray jsonArray = new JSONArray(details);
                    Integer totalcaloriesconsumed= jsonArray.getJSONObject(0).getInt("totalcaloriesconsumed");
                    userReport[0] = Integer.valueOf(totalcaloriesconsumed);
                    Integer totalcaloriesburned= jsonArray.getJSONObject(0).getInt("totalcaloriesburned");
                    userReport[1] = Integer.valueOf(totalcaloriesburned);
                    Integer remainingCalorie= jsonArray.getJSONObject(0).getInt("remainingCalorie");
                    userReport[2] = Integer.valueOf(remainingCalorie);
                }
                catch (JSONException e){
                }
                pieEntries = new ArrayList<>();
                    if (!String.valueOf(userReport[0]).isEmpty() &&
                            !String.valueOf(userReport[1]).isEmpty()
                    &&!String.valueOf(userReport[2]).isEmpty()){
                        pieEntries.add(new PieEntry(userReport[0],"Calories Consumed",0));
                        pieEntries.add(new PieEntry(userReport[1], "Calories Burned",1));
                        pieEntries.add(new PieEntry(userReport[2],"Calories Remaining",2));
                    }
                PieDataSet pieDataSet = new PieDataSet(pieEntries, "User Report");
                pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
                pieDataSet.setValueTextSize(13f);
                pieDataSet.setValueTextColor(Color.DKGRAY);
                PieData pieData = new PieData(pieDataSet);
                pieData.setValueFormatter(new PercentFormatter());
                pieChart.setUsePercentValues(true);
                pieChart.setDrawHoleEnabled(false);
                pieChart.setData(pieData);
                pieChart.invalidate();
            } else {
                showText("No data to show");
            }
        }
    }
    public void showText(String string) {
        Toast.makeText(PieChartActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
