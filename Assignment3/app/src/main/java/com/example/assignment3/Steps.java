package com.example.assignment3;

import android.app.Fragment;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Steps extends Fragment implements View.OnClickListener {
    View vSteps;
    StepsDatabase db = null;
    EditText editSteps = null;
    TextView textView_update = null;
    TextView textView_read = null;
    private Integer userid;
    private Button updateButton;
    private Button readButton;
    List<HashMap<String, String>> unitListArray;
    SimpleAdapter myListAdapter;
    ListView unitList;
    HashMap<String,String> map = new HashMap<String,String>();
    String[] colHEAD = new String[] {"DATE/Time","STEPS"};
    int[] dataCell = new int[] {R.id.stepsDate,R.id.stepsList};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        vSteps = inflater.inflate(R.layout.fragment_steps, container, false);
        SharedPreferences settings = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userid = settings.getInt("userid",0);

        editSteps = (EditText) vSteps.findViewById(R.id.edSteps);

        unitList = vSteps.findViewById(R.id.list_view); unitListArray = new
                ArrayList<HashMap<String, String>>();
         updateButton = (Button) vSteps.findViewById(R.id.updateButton);
         readButton = (Button) vSteps.findViewById(R.id.readButton);
         textView_update = (TextView) vSteps.findViewById(R.id.tvSteps);
        textView_read = (TextView) vSteps.findViewById(R.id.tvStepsRead);
         return vSteps;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = StepsDatabase.getDatabase(getContext().getApplicationContext());
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDatabase updateDatabase = new UpdateDatabase();
                updateDatabase.execute();
            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadSteps readSteps = new ReadSteps();
                readSteps.execute();
            }
        });
    }
    private class UpdateDatabase extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
          if(!editSteps.getText().toString().isEmpty())
          {
              System.currentTimeMillis();
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm a");
              String dateTime = new Date(System.currentTimeMillis()).toString();
              Integer stepsInt = Integer.valueOf(editSteps.getText().toString());
              StepsSQL stepsSQL = new StepsSQL(dateTime, stepsInt);
              long id = db.stepsDAO().insert(stepsSQL);
              Log.d("Steps", "" + db.stepsDAO().getAll().size());
              return (id+ " Today is "+ dateTime +" " + stepsInt + " steps");
          }
          else
              return "";
        }
        @Override
        protected void onPostExecute(String details) {
            textView_update.setText("Updated steps: "+ details);
        }
    }

    private class ReadSteps extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            List<StepsSQL> steps = db.stepsDAO().getAll();
            if(!(steps.isEmpty() || steps==null))
            {
                String allSteps = " ";
                for(StepsSQL step: steps)
                {
                    String stepStr= (step.getDateTime()+ ","+ step.getStepsTaken()+ ",");
                    allSteps= allSteps + stepStr;
                }
                Integer stepsInt = 0;

                for(StepsSQL step: steps)
                {
                    Integer stepsTaken= (step.getStepsTaken());
                    stepsInt= stepsInt+ stepsTaken;
                }
                SharedPreferences sharedpreferences = getActivity().getSharedPreferences("ps", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                System.out.println(stepsInt);
                editor.putInt("stepsInt", stepsInt);
                editor.commit();
                return allSteps;
            }
            else
                return " ";
        }
        @Override
        protected void onPostExecute(String details) {
            System.out.println(details);
            myListAdapter = new
                    SimpleAdapter(vSteps.getContext(),unitListArray,R.layout.list_view,colHEAD,dataCell);

            String[] unitsArray = details.split(",");
            for(int i=0;i<unitsArray.length-1;i+=2){
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("DATE/Time", unitsArray[i]);
                map.put("STEPS", unitsArray[i+1]);
//                addMap(map);
                unitListArray.add(map);

            }
            unitList.setAdapter(myListAdapter);
           // textView_read.setText("All the steps:\n "+ details);
        }
    }
    protected void addMap(HashMap map){
        unitListArray.add(map);

    }
    @Override
    public void onClick(View v) {
        // Gather user input
    }


}