package com.example.assignment3;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class DailyDiet extends Fragment implements View.OnClickListener {
     View vDailyDiet;
    private Spinner spFoodCat, spFoodName;
    private TextView tvStart;
    private String foodCat;
    ArrayList<String> foodList;
    private TextView tventeredFood;
    private EditText edenteredFood;
    private Button addFood;
    private Button searchFoodInfo;
    private Button searchFoodImage;
    String name="";
    String ru="";
    String calori="";
    String fat="";
    private TextView foodName;
    private TextView servingUnit;
    private TextView Calorie;
    private TextView fatView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Set Variables and listener
        vDailyDiet = inflater.inflate(R.layout.fragment_daily_diet, container, false);
        tvStart = (TextView) vDailyDiet.findViewById(R.id.tvWelecome);
        spFoodCat =(Spinner) vDailyDiet.findViewById(R.id.food_catgory);
        foodName =(TextView) vDailyDiet.findViewById(R.id.tvfoodName);
        servingUnit=(TextView) vDailyDiet.findViewById(R.id.tvru);
        Calorie= (TextView) vDailyDiet.findViewById(R.id.tvCalorie);
        fatView = (TextView) vDailyDiet.findViewById(R.id.tvFat);
        spFoodCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                foodCat= parent.getItemAtPosition(pos).toString();
                // Bundle b = new Bundle();
                // b.putString("calorieGoalString",calroiGoalString);
                //   b.putSerializable("calroiGoalString",calroiGoalString);
                //   CalorieTracker calorieTracker1 = new CalorieTracker();
                //  calorieTracker1.setArguments(b);
                if(foodCat!=null)
                {
                    foodCategoryAsyncTask findFood = new foodCategoryAsyncTask();
                    findFood.execute(foodCat);
                    //Toast.makeText(parent.getContext(),"Calori Goal is" + calroiGoalString,Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spFoodName=(Spinner) vDailyDiet.findViewById(R.id.food_name);
        tventeredFood = (TextView) vDailyDiet.findViewById(R.id.tvaddFood);
        edenteredFood = (EditText) vDailyDiet.findViewById(R.id.edFood);
        addFood = (Button) vDailyDiet.findViewById(R.id.btnfood);
        addFood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (!edenteredFood.getText().toString().trim().equals(""))
                {
                    addFoodAsyncTask addFoodAsyncTask = new addFoodAsyncTask();
                    String food = edenteredFood.getText().toString().trim();

                    if (!food.isEmpty()) {
                        addFoodAsyncTask.execute(food);
                    }
                }
            }
        });
        searchFoodInfo = (Button) vDailyDiet.findViewById(R.id.btnFoodinfo);
        searchFoodInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (!edenteredFood.getText().toString().trim().equals(""))
                {
                    searchFoodInfoAsyncTask searchFoodInfoAsyncTask = new searchFoodInfoAsyncTask();
                    String food = edenteredFood.getText().toString().trim();

                    if (!food.isEmpty()) {
                        searchFoodInfoAsyncTask.execute(food);
                    }
                }
            }
        });

        searchFoodImage = (Button) vDailyDiet.findViewById(R.id.btnFoodImage);
        searchFoodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edenteredFood.getText().toString().trim().equals("")) {
                    searchFoodImageAsyncTask searchFoodImageAsyncTask = new searchFoodImageAsyncTask();
                    String food = edenteredFood.getText().toString().trim();

                    if (!food.isEmpty()) {
                        searchFoodImageAsyncTask.execute(food);
                    }
                }
            }
        });
        return vDailyDiet;
    }

    private class foodCategoryAsyncTask extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground (String...params){
            String userDetails =RestClient.getFoodList(foodCat);
            return  userDetails;
        }
        @Override
        protected void onPostExecute (String userDetails){
            foodList = new ArrayList<String>();
            try {
                JSONArray jsonArray = new JSONArray(userDetails);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject reader = jsonArray.getJSONObject(i);
                    String name = reader.getString("fname");
                    foodList.add(name);
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(vDailyDiet.getContext(), android.R.layout.simple_spinner_item, foodList);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spFoodName.setAdapter(spinnerArrayAdapter);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            // calroiBurned.setText("Total Calori Burned is: " +userDetails);
        }
    }

    public class searchFoodInfoAsyncTask  extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params) {
            return  RestClient.findFoodInfo(params[0], new String[]{"num"}, new String[]{"1"});
        }
        @Override
        protected void onPostExecute(String result) {
            String snippet="";
            String searchurl="";
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                if(jsonArray != null && jsonArray.length() > 0) {
                    snippet =jsonArray.getJSONObject(0).getString("snippet");
                    searchurl =jsonArray.getJSONObject(0).getString("formattedUrl");
                }
            }catch (Exception e){
                e.printStackTrace();
                snippet = "NO INFO FOUND";
            }
                TextView tv= (TextView) vDailyDiet.findViewById(R.id.tvResult);
                tv.setText(snippet + "To read more "+searchurl);
        }
    }
    public class searchFoodImageAsyncTask  extends AsyncTask<String, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... params) {
        String details =RestClient.findFoodImage(params[0], new String[]{"num"}, new
                String[]{"1"});
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(details).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            ImageView imageView = vDailyDiet.findViewById(R.id.imageFood);
            imageView.setImageBitmap(result);
        }}

    public Bitmap StringToBitMap(String image){
        try{
            byte [] encodeByte=Base64.decode(image,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }


    public class addFoodAsyncTask extends AsyncTask<String, Void, String>
    {
        SharedPreferences sharedpreferences;
        @Override
        protected String doInBackground(String... params) {
            String ndnob = RestClient.findFoodApi(params[0]);
            //  RestClient.findFoodDetails(ndnob);
            return  RestClient.findFoodDetails(ndnob);
        }
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject info = new JSONObject(result);
                JSONArray jsonArray = info.getJSONArray("foods");
                name = jsonArray.getJSONObject(0).getJSONObject("food").getJSONObject("desc").getString("name");
                ru = jsonArray.getJSONObject(0).getJSONObject("food").getJSONObject("desc").getString("ru");
                foodName.setText("The food name is "+name);
                servingUnit.setText("Serving unit is " + ru);
                JSONArray jsonArray1 = jsonArray.getJSONObject(0).getJSONObject("food").getJSONArray("nutrients");
                calori = jsonArray1.getJSONObject(0).getString("value");
                fat =jsonArray1.getJSONObject(2).getString("value");
                //Log.d("debug", calori);
                Calorie.setText("Calorie is" + calori);
                fatView.setText("The fat is "+ fat);
            }
            catch(JSONException e)
            {

            }
        }
    }
    /**
     * When the submit button is clicked
     */
    @Override
    public void onClick(View v) {

    }
}