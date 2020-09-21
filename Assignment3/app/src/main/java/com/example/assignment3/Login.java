package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;

public class Login extends AppCompatActivity  {


    private EditText userName;
    private EditText passWord;
    private Button loginButton;
    private TextView signupLink;
    private AppUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.loginButton);
        userName = (EditText) findViewById(R.id.usernameText);
        passWord = (EditText) findViewById(R.id.passwordText);
        signupLink = (TextView) findViewById(R.id.registerText);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (!userName.getText().toString().trim().equals("")
                        && !passWord.getText().toString().trim().equals("")) {
                    UserAsyncTask getAlluser = new UserAsyncTask();
                    String username = userName.getText().toString();
                    String passd = passWord.getText().toString();
                    if (!username.isEmpty() && !passd.isEmpty()) {
                        getAlluser.execute(username,passd);
                    }
                }
                else {
                    showText("User Name/Passoword can not be empty.");
                }
            }});
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public class UserAsyncTask extends AsyncTask<String, Void, String>
    {
        SharedPreferences sharedpreferences;
        @Override
        protected String doInBackground(String... params) {
            return RestClient.finUser(params[0],params[1]);
        }
        @Override
        protected void onPostExecute(String result){
            if (result.equals("")){
                showText("User Name/Passoword is not valid.");
            } else {
                try
                {
                showText("Welcome, " + userName.getText().toString());
                //extract all requirted values from JSON using JSONObject example
                //JSONObject jObject = new JSONObject(result);
                JSONArray jsonArray = new JSONArray(result);
                String address = jsonArray.getJSONObject(0).getJSONObject("userid").getString("address");
                String name = jsonArray.getJSONObject(0).getJSONObject("userid").getString("name");
                Integer userid = jsonArray.getJSONObject(0).getJSONObject("userid").getInt("userid");
                Integer postcode = jsonArray.getJSONObject(0).getJSONObject("userid").getInt("postcode");
                sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("address", address);
                editor.putString("name", name);
                editor.putInt("userid", userid);
                editor.putInt("postcode", postcode);
                editor.commit();
                Intent intent = new Intent(Login.this, HomeActivity.class);
                Bundle b = new Bundle();
                b.putString("username", userName.getText().toString());
                intent.putExtras(b);
                startActivity(intent);
               intent.putExtras(b);
            }
                catch (JSONException e)
                {
                }
            }
        }
    }
    public void showText(String string) {
        Toast.makeText(Login.this, string, Toast.LENGTH_SHORT).show();
    }
}