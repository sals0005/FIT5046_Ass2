package com.example.assignment3;

import android.content.Context;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    View vMain;
    private Spinner caloriesGoal;
    Integer userid;
    private String calroiGoalString;
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
    String currentDateandTime = sdf2.format(new Date());
    private TextView tvcloari;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        vMain = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences settings = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String firstName = settings.getString("name","");
        userid = settings.getInt("userid",0);
        CalorieTracker calorieTracker = new CalorieTracker();
        tvcloari = (TextView) vMain.findViewById(R.id.tvCalroi);
        tvcloari.setText("Please select your Today's goal");
        caloriesGoal = (Spinner) vMain.findViewById(R.id.calorigoal);
        caloriesGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                calroiGoalString= parent.getItemAtPosition(pos).toString();
                if(calroiGoalString!=null)
                {
                    //Toast.makeText(parent.getContext(),"Calori Goal is" + calroiGoalString,Toast.LENGTH_LONG).show();
                    SharedPreferences sharedpreferences = getActivity().getSharedPreferences("prefre", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    System.out.println(calroiGoalString);
                    editor.putString("calroiGoalString", calroiGoalString);
                    editor.commit();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        TextView welcomeText =  vMain.findViewById(R.id.welcome);
        welcomeText.setText("Welecome " + firstName);
        TextView time = vMain.findViewById(R.id.dateText);
        time.setText(currentDateandTime);
        ImageView wall = vMain.findViewById(R.id.imageView4);
        return vMain;
    }

   /* private class UserCaloriGoalAsyncTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground (String...params){

            Date date = new Date();
            String userDetails =RestClient.updateCaloriGoal(userid,date);
            return  userDetails;
        }
        @Override
        protected void onPostExecute (String userDetails){
           // calroiBurned.setText("Total Calori Burned is: " +userDetails);
        }
    }*/



}