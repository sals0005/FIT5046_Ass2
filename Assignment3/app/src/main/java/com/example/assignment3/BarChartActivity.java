package com.example.assignment3;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {
    private BarChart barChart;
    EditText startDate=null;
    EditText endDate=null;
    private Integer userid;
    String startDateStr="";
    String endDateStr="";
    Date satDate;
    Date enDate;
    private List<BarEntry> barEntries;
    private String details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        barChart= (BarChart) findViewById(R.id.bar_chart);

        SharedPreferences settings = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userid = settings.getInt("userid",0);
        Button button = (Button) findViewById(R.id.btnBarChart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDate = (EditText) findViewById(R.id.startDate);
                endDate = (EditText) findViewById(R.id.endDate);
               if (!startDate.getText().toString().trim().equals("")
                        && !endDate.getText().toString().trim().equals("")){
                startDateStr = startDate.getText().toString();
                endDateStr = endDate.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try{
                    satDate =sdf.parse(startDateStr);
                    enDate =sdf.parse(endDateStr);
                }
                catch (ParseException e)
                {
                    e.printStackTrace();
                }
                GenrateBarChart genrateBarChart = new GenrateBarChart();
                genrateBarChart.execute();
               }
               else {
                   showText("Dates Can not be empty.");
               }
            }
        });

    }
    public class GenrateBarChart extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params){
             details= RestClient.getUserReport(userid,satDate,enDate);
            // data = details.split(",");
           return details;
        }

        @Override
        protected void onPostExecute(String details){
            if (!details.equals("[]"))
            {
            Integer userReport[] = new Integer[4];
            try {

                JSONArray jsonArray = new JSONArray(details);
                Integer totalcaloriegoal= jsonArray.getJSONObject(0).getInt("totalcaloriegoal");
                userReport[0] = Integer.valueOf(totalcaloriegoal);
                Integer totalcaloriesburned= jsonArray.getJSONObject(0).getInt("totalcaloriesburned");
                userReport[1] = Integer.valueOf(totalcaloriesburned);
                Integer totalcaloriesconsumed= jsonArray.getJSONObject(0).getInt("totalcaloriesconsumed");
                userReport[2] = Integer.valueOf(totalcaloriesconsumed);
                Integer totalstepstaken= jsonArray.getJSONObject(0).getInt("totalstepstaken");
                userReport[3] = Integer.valueOf(totalstepstaken);
            }
            catch (JSONException e){
            }
            barEntries = new ArrayList<>();
            for (int i = 0; i < userReport.length; i ++){
                if (!String.valueOf(userReport[i]).isEmpty()) {
                    barEntries.add(new BarEntry(i, userReport[i]));
                }
            }
            BarDataSet barDataSet = new BarDataSet(barEntries, "User Report");
            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);//set colors for the bars
            BarData barData = new BarData(barDataSet);
            barData.setBarWidth(0.70f);// set custom bar width
            barChart.setData(barData);
            barChart.setFitBars(true); // make the x-axis fit exactly all bars
            barChart.invalidate();
            }
            else{
                showText("No data to show");}
        }
    }

    public void showText(String string) {
        Toast.makeText(BarChartActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}

