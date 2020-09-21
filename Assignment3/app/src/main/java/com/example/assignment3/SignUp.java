package com.example.assignment3;

import android.content.Intent;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class SignUp extends AppCompatActivity {
    private EditText userFirstName;
    private EditText userSurename;
    private EditText userEmail;
    private EditText userUsername;
    private EditText userPassword;
    private EditText userPasswordConfirm;
    private DatePicker userDOB;
    private EditText userHieght;
    private EditText userWeight;
    private EditText userAddress;
    private EditText userPostcode;
    private Spinner userlevelOfActivity;
    private EditText userStepPerMile;
    private Button signUpbtn;
    private RadioGroup userGender;
    private RadioButton male;
    private RadioButton female;
    private int nUserid;
    private String firstName;
    private String userName;
    private String sureName;
    private String password;
    private String confPassword;
    private Double hieght;
    private Double weight;
    private String address;
    private Integer postCode;
    private String email;
    private Integer stepPerMile;
    private TextView dobErrorText;
    private String dobString;
    private String gender;
    private Integer levelOfActivity;
    private boolean check = false;
    private View focus = null;
    private UserRegister regestier = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpbtn = (Button) findViewById(R.id.btSignup);
        signUpbtn.setFocusableInTouchMode(true);
        userlevelOfActivity = (Spinner) findViewById(R.id.spActivtyLever);
        userStepPerMile = (EditText) findViewById(R.id.spStepPerMile);
        userUsername = (EditText) findViewById(R.id.edUserName);
        userPassword = (EditText) findViewById(R.id.edPassword);
        userPasswordConfirm = (EditText) findViewById(R.id.edCondPassword);
        userFirstName = (EditText) findViewById(R.id.edFirstName);
        userSurename = (EditText) findViewById(R.id.edSureName);
        userEmail = (EditText) findViewById(R.id.edEmail);
        userHieght = (EditText) findViewById(R.id.edUserHieght);
        userWeight = (EditText) findViewById(R.id.edUserWeight);
        userAddress = (EditText) findViewById(R.id.edAdress);
        userPostcode = (EditText) findViewById(R.id.edPostCode);
        userGender = (RadioGroup) findViewById(R.id.RadioGender);
        male = (RadioButton) findViewById(R.id.RadioMale);
        female = (RadioButton) findViewById(R.id.RadioFemale);
        userDOB = (DatePicker) findViewById(R.id.DoB);
        dobErrorText = (TextView) findViewById(R.id.tvDoB);

        generateUserId useId = new generateUserId();
        useId.execute((Void) null);
        Button signUpbtn = (Button) findViewById(R.id.btSignup);
        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
            }
        });

    }

    private void insertUser() {
        if (regestier  != null) {
            return;
        }

            final String pattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
            userName = userUsername.getText().toString().trim();
            firstName = userFirstName.getText().toString().trim();
            sureName = userSurename.getText().toString().trim();
            password = userPassword.getText().toString().trim();
            confPassword = userPasswordConfirm.getText().toString().trim();
            String heightString = userHieght.getText().toString();
            Double heightInt = Double.parseDouble(heightString);
            String weightString = userWeight.getText().toString();
            Double weightInt = Double.parseDouble(weightString);
            String postString = userPostcode.getText().toString();
            Integer postInt = Integer.parseInt(postString);
            String stepsString = userStepPerMile.getText().toString();
            Integer stepsInt = Integer.parseInt(stepsString);
            String levelString = userlevelOfActivity.getSelectedItem().toString();
            Integer levelInt = Integer.parseInt(levelString);
            address = userAddress.getText().toString().trim();
            email = userEmail.getText().toString().trim();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dobString = simpleDateFormat.format(new Date());
            int genderId = userGender.getCheckedRadioButtonId();
            if (genderId != -1) {
                RadioButton checked = findViewById(genderId);
                gender = checked.getText().toString();
            }
            if (isEmpty(userName)) {
                userUsername.setError("User name can not be empty");
                focus = userUsername;
                focus.requestFocus();
                check = true;
            } else {
                CheckUserName checkUsername = new CheckUserName(userName);
                checkUsername.execute();
            }
            if (isEmpty(firstName)) {
                userFirstName.setError("First Name can not be empty");
                focus = userFirstName;
                focus.requestFocus();
                check = true;
            }
            if (isEmpty(sureName)) {
                userSurename.setError("Last Name can not be empty");
                focus = userSurename;
                focus.requestFocus();
                check = true;
            }
            if (isEmpty(password)) {
                userPassword.setError("Password can not be empty");
                focus = userPassword;
                focus.requestFocus();
                check = true;
            } else if (!password.equals(confPassword)) {
                userPassword.setError("Passwords does not match");
                userPasswordConfirm.setError("Passwords does not match");
                focus = userPassword;
                focus = userPasswordConfirm;
                focus.requestFocus();
                check = true;
            }
            if (isEmpty(confPassword)) {
                userPasswordConfirm.setError("Password confirmation can not be empty");
                focus = userPasswordConfirm;
                focus.requestFocus();
                check = true;
            }
            if (userHieght.getText().toString().trim().equals("")) {
                userHieght.setError("Hieght can not be empty");
                focus = userHieght;
                focus.requestFocus();
                check = true;
            }
            if (userWeight.getText().toString().trim().equals("")) {
                userWeight.setError("Weight can not be empty");
                focus = userWeight;
                focus.requestFocus();
                check = true;
            }
            if (userPostcode.getText().toString().trim().equals("")) {
                userPostcode.setError("Post code can not be empty");
                focus = userPostcode;
                focus.requestFocus();
                check = true;
            }
            if (isEmpty(address)) {
                userAddress.setError("Address can not be empty");
                focus = userAddress;
                focus.requestFocus();
                check = true;
            }
            if (isEmpty(dobString)) {
                dobErrorText.setError("Last Name can not be empty");
                focus = userDOB;
                focus.requestFocus();
                check = true;
            }
            if (userEmail.getText().toString().trim().equals("")) {
                userEmail.setError("Email can not be empty");
                focus = userEmail;
                focus.requestFocus();
                check = true;

            } else {
                checkEmail checkEmail = new checkEmail(email);
                checkEmail.execute();
            }
            if (!isEmpty(userEmail.getText().toString())) {
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(email);
                if (!m.find()) {
                    userEmail.setError("Email format is incorrect");
                    focus = userEmail;
                    focus.requestFocus();
                    check = true;
                }
            }
            if (userStepPerMile.getText().toString().trim().equals("")) {
                userStepPerMile.setError("Step per Mile can not be empty");
                focus = userStepPerMile;
                focus.requestFocus();
                check = true;
            }
            else {
                try {
                    SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    Date date = simpleDateFormat.parse(dobString);
                    AppUser user = new AppUser(nUserid, firstName, sureName, email, date, gender, heightInt
                            , weightInt, address, postInt, levelInt, stepsInt);
                    String userString = stringJsonUserParser(user);
                    String credentialString = stringJsonCredentialParser(user);
                    regestier = new UserRegister();
                    regestier.execute(userString,credentialString);
                }
                catch (ParseException e){}
            }
        }

    public class checkEmail extends AsyncTask<Void, Void, Boolean>{
        private String checkEmail;
        checkEmail(String email){
            checkEmail = email;
        }

        @Override
        protected Boolean doInBackground(Void... params){
            return RestClient.checkEmail(checkEmail);
        }
        @Override
        protected void onPostExecute(Boolean exist){
            if (exist) {
                userEmail.setError("Exist email address");
                check = true;
                focus = userEmail;
            }
            else check=false;
        }
    }
    public class CheckUserName extends AsyncTask<Void, Void, Boolean>{
        private String checkUserName;
        CheckUserName(String userName){
            checkUserName = userName;
        }

        @Override
        protected Boolean doInBackground(Void... params){
            return RestClient.checkUserName(checkUserName);
        }
        @Override
        protected void onPostExecute(Boolean exist){
            if (exist) {
                userUsername.setError("UserName exist");
                check = true;
                focus = userUsername;
            }
            else
                check=false;
        }
    }

    public class generateUserId extends AsyncTask<Void, Void, Integer> {

        generateUserId() {
        }

        @Override
        protected Integer doInBackground(Void... params) {

            return RestClient.createUserId();
        }

        @Override
        protected void onPostExecute(Integer userID) {
            regestier = null;
            if (userID != null)
                nUserid = userID + 1;
        }
    }

    private String stringJsonUserParser(Object user) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
        String jsonUser = gson.toJson(user);
        return jsonUser;
    }

    private String stringJsonCredentialParser(AppUser user) {
        SimpleDateFormat spdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = new Date();
        credential credential = new credential(user.getUserId(), userName, password, date);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
        String jsonCred= gson.toJson(credential);
        return jsonCred;
    }

    public class UserRegister extends AsyncTask<String, Void, String>    {
//        private String  postUserData;
//        private String  postCredentialData;
//
//        UserRegister(String user, String credential) {
//            postUserData = user;
//            postCredentialData = credential;
//        }

        @Override
        protected String doInBackground(String... params) {
             RestClient.postUserData(params[0]);
             RestClient.postCredentialData(params[1]);
           return "User has been registered, please log in";
        }

        @Override
        protected void onPostExecute(String response){
            // It was working inserting to the database 4 days ago, i commented this cause i wanted to keep in this page and try to know what's wrong
            
           // Intent intent = new Intent(SignUp.this, Login.class);
            //startActivity(intent);
        }

        public void showText(String string) {
            Toast.makeText(SignUp.this, string, Toast.LENGTH_SHORT).show();
        }

    }




    }