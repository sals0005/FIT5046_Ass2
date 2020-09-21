package com.example.assignment3;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class CalorieTracker extends Fragment implements View.OnClickListener {
    View vCalorieTracker;
    private TextView welcomeText;
    private Integer userid;
    private TextView calroiBurned;
    private TextView caloriRest;
    private TextView calorGoal;
    private TextView Steps;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        vCalorieTracker = inflater.inflate(R.layout.fragment_calorie_tracker, container, false);
        SharedPreferences settings = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
         userid = settings.getInt("userid",0);
        String firstName = settings.getString("name", "");
        // Gather user input
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        TextView date= (TextView) vCalorieTracker.findViewById(R.id.tvdate);
        welcomeText = (TextView)  vCalorieTracker.findViewById(R.id.tv_name);
        calroiBurned= vCalorieTracker.findViewById(R.id.tvCalroiBurned);
        date.setText("Today is: "+currentDate);
         caloriRest = (TextView) vCalorieTracker.findViewById(R.id.tvCalorieAtRest);
        SharedPreferences sett = getActivity().getSharedPreferences("prefre", MODE_PRIVATE);
        String calroiGoalString = sett.getString("calroiGoalString", "");
        calorGoal = (TextView) vCalorieTracker.findViewById(R.id.tvCalroiGoal);
        SharedPreferences sets = getActivity().getSharedPreferences("ps", MODE_PRIVATE);
        Integer stepsInt = sets.getInt("stepsInt", 0);
        Steps = (TextView) vCalorieTracker.findViewById(R.id.tvStepsTaken);
        Steps.setText("Total steps: " +stepsInt);
        calorGoal.setText("Calorie Goal for Today: " +calroiGoalString);
        UserCaloritTrackerAsyncTask getCalori = new UserCaloritTrackerAsyncTask();
        getCalori.execute(userid);
        userCalroiRestAsyncTask getRestCalroi = new userCalroiRestAsyncTask();
        getRestCalroi.execute(userid);
        return vCalorieTracker;
    }
    private class UserCaloritTrackerAsyncTask extends AsyncTask<Integer, Void, String>
    {
        @Override
        protected String doInBackground (Integer...params){
            Date date = new Date();
            String userDetails =RestClient.getUserDetails(userid,date);
            return  userDetails;
        }
        @Override
        protected void onPostExecute (String userDetails){
            calroiBurned.setText("Total Calori Burned is: " +userDetails);
        }
    }

    private class userCalroiRestAsyncTask extends AsyncTask<Integer, Void, String>
    {
        @Override
        protected String doInBackground (Integer...params){
            String userDetails =RestClient.getrestMethods(userid);
            return  userDetails;
        }
        @Override
        protected void onPostExecute (String userDetails){
            caloriRest.setText("Total Calori Burned at rest: " +userDetails);
        }
    }
    @Override
    public void onClick(View v) {


    }
}