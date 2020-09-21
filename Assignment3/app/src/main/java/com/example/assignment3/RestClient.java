package com.example.assignment3;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class RestClient {
    private static final String BASE_URL =
            "http://10.0.2.2:42982/CaloriTracker3/webresources/";
    private static final String MAP_URL =
            "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String FOOD_URL = "https://api.nal.usda.gov/ndb/search/?format=json&q=";
    private static final String API_KEY = "AIzaSyDkMspaRCFN4mmy0a4Vg5TFex24BtoGEkA";
    private static final String SEARCH_ID_cx = "017915861890676235793:5kisfr77c7s";
    protected static boolean checkEmail(String givenEmail) {
        final String methodEmailPath = "restws.appuser/findByEmail/";
        URL emailUrl;
        HttpURLConnection connection = null;
        String textResult;
        boolean emailExist = true;
        try {
            emailUrl = new URL(BASE_URL + methodEmailPath + givenEmail);
            connection = (HttpURLConnection) emailUrl.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner inStreamUserID = new Scanner(connection.getInputStream());
            textResult = "";
            while (inStreamUserID.hasNextLine()) {
                textResult += inStreamUserID.nextLine();
            }
            JSONArray allResult = new JSONArray(textResult);
            if (allResult.length() == 0)
                emailExist = false;
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (JSONException e3) {
            e3.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return emailExist;
    }

    protected static void postUserData(String jsonUser) {
        final String methodPath = "restws.appuser/";
        URL userUrl;
        HttpURLConnection conn = null;
        try {
            userUrl = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection) userUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(jsonUser.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(jsonUser);
            out.close();
        //    Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            conn.disconnect();
        }

    }


    protected static void postCredentialData(String credential) {
        final String methodPath = "restws.credential/";
        URL credentialUrl;
        HttpURLConnection conn = null;
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").create();
            String jsonCred = gson.toJson(credential);
            credentialUrl = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection) credentialUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setFixedLengthStreamingMode(credential.getBytes("UTF-8").length);
            conn.setRequestProperty("Content-Type", "application/json");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(jsonCred);
            out.close();
//            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    protected static int createUserId() {
        final String methodPath = "restws.appuser/";
        URL userUrl;
        HttpURLConnection conn = null;
        String textResult;
        int useridArray[];
        int userId = -1;
        try {
            userUrl = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection) userUrl.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Scanner inStreamUserID = new Scanner(conn.getInputStream());
            textResult = "";
            while (inStreamUserID.hasNextLine()) {
                textResult += inStreamUserID.nextLine();
            }
            JSONArray allResult = new JSONArray(textResult);
            useridArray = new int[allResult.length()];
            for (int i = 0; i < allResult.length(); i++) {
                JSONObject user = allResult.getJSONObject(i);
                useridArray[i] = user.getInt("userid");
            }
            for (int id : useridArray) {
                if (id >= userId)
                    userId = id;
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (JSONException e3) {
            e3.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return userId;
    }

    protected static boolean checkUserName(String userName) {
        final String methodUsernamePath = "restws.credential/findByUsername/";
        URL emailUrl;
        HttpURLConnection connection = null;
        String textResult;
        boolean usernameExist = true;
        try {
            emailUrl = new URL(BASE_URL + methodUsernamePath + userName);
            connection = (HttpURLConnection) emailUrl.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner inStreamUserID = new Scanner(connection.getInputStream());
            textResult = "";
            while (inStreamUserID.hasNextLine()) {
                textResult += inStreamUserID.nextLine();
            }
            JSONArray allResult = new JSONArray(textResult);
            if (allResult.length() == 0)
                usernameExist = false;
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (JSONException e3) {
            e3.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return usernameExist;
    }

    public static String finUser(String userName, String password) {
        String userInfo = "";
        String userUrl = BASE_URL + "restws.credential/findByUsername/" + userName;
        String userStr = getResult(userUrl);
        if (!userStr.equals("[]")) {
            String pwUrl = BASE_URL + "restws.credential/findByPasswordhash/" + password;
            String pw = getResult(pwUrl);
            if (!pw.equals("[]")) {
                userInfo = userStr;
            } else
                userInfo = "";
        } else
            userInfo = "";
        return userInfo;
    }

    public static String getResult(String URL) {
        URL url = null;
        HttpURLConnection conn = null;
        String str = "";
        //Making HTTP request
        try {
            url = new URL(URL);
            //open the connection
            conn = (HttpURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                str += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return str;
    }

    public static String getUserDetails(Integer userid, Date date) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        final String methodPath = "restws.consumption/findByIdDateFoodConsum/" + userid + "/" + sdf.format(date);
        String textResult = "";
        URL userUrl;
        HttpURLConnection conn = null;
        try {
            userUrl = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection) userUrl.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "text/plain");
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String updateCaloriGoal(Integer userid, Date date) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        final String methodPath = "restws.report/findCalorieGoal/" + userid + "/" + sdf.format(date);
        String textResult = "";
        URL userUrl;
        HttpURLConnection conn = null;
        try {

            userUrl = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection) userUrl.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "text/plain");
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String getrestMethods(Integer userid) {

        final String methodPath = "restws.appuser/findByIdTotalalorisBurndeRest/" + userid;
        String textResult = "";
        URL userUrl;
        HttpURLConnection conn = null;
        try {

            userUrl = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection) userUrl.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "text/plain");
            conn.setRequestProperty("Accept", "text/plain");
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }
    public static String getFoodList(String foodCat) {
        final String methodPath = "restws.food/findByFcategory/" + foodCat;
        String textResult = "";
        URL userUrl;
        HttpURLConnection conn = null;
        try {
            userUrl = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection) userUrl.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

public static String findFoodApi(String foodName)
{
    final String API_KEY ="&sort=n&max=25&offset=0&api_key=2RBUoImx5TkjgwO4LbB96Wh0ngyls2SiQ62sCEK3";
   // final String API_URL ="https://api.nal.usda.gov/ndb/search/?format=json&q=";
   // foodName = foodName.toString().replace(" ", "+");
    URL url;
    HttpURLConnection connection = null;
    String textResult = "";
    String ndbno="";
    try{
        url = new URL( FOOD_URL + foodName + API_KEY);
        connection = (HttpURLConnection)url.openConnection();
        Log.d("debug", url.toString());
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        Scanner inStream = new Scanner(connection.getInputStream());
        while (inStream.hasNextLine()) {
            Log.d("debug", inStream.toString());
         textResult += inStream.nextLine();
        }
        JSONObject result = new JSONObject(textResult);
        JSONObject list  = result.getJSONObject("list");
        JSONArray item = list.getJSONArray("item");
        JSONObject ndn = item.getJSONObject(0);
         ndbno = ndn.getString("ndbno");
    }catch (Exception e){
        e.printStackTrace();
    }finally{
        connection.disconnect();
    }
        return ndbno;
}

    public static String findFoodDetails(String ndnbon)
    {
        String API_KEY = "2RBUoImx5TkjgwO4LbB96Wh0ngyls2SiQ62sCEK3";
        String apiURl = "https://api.nal.usda.gov/ndb/V2/reports?ndbno=";
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        try{
            url = new URL(apiURl+
                    ndnbon +"&type=f&format=json&api_key="+ API_KEY);
            Log.d("debug",url.toString());
            connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }
        return textResult;
    }

        public static String findFoodInfo(String keyword, String[] params, String[] values) {
        keyword = keyword.replace(" ", "+");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        String query_parameter="";
        String snippet = null;
        String searchurl=null;
        if (params!=null && values!=null){
            for (int i =0; i < params.length; i ++){
                query_parameter += "&";
                query_parameter += params[i];
                query_parameter += "=";
                query_parameter += values[i];
            }
        }
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?key="+
                    API_KEY+ "&cx="+ SEARCH_ID_cx + "&q="+ keyword + query_parameter
       );
            connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }
        return textResult;
    }

    public static String findFoodImage(String keyword, String[] params, String[] values) {
        keyword = keyword.replace(" ", "+");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        String query_parameter="";
        String snippet = null;
        String image=null;
        if (params!=null && values!=null){
            for (int i =0; i < params.length; i ++){
                query_parameter += "&";
                query_parameter += params[i];
                query_parameter += "=";
                query_parameter += values[i];
            }
        }
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?key="+
                    API_KEY+ "&cx="+ SEARCH_ID_cx + "&q="+ keyword + query_parameter);
            connection = (HttpURLConnection)url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
            try{
                JSONObject jsonObject = new JSONObject(textResult);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                if(jsonArray != null && jsonArray.length() > 0) {
                    snippet =jsonArray.getJSONObject(0).getString("snippet");
                    JSONObject jsonObject1 =jsonArray.getJSONObject(0).getJSONObject("pagemap");
                    JSONArray jsonArray1 = jsonObject1.getJSONArray("metatags");
                    image = jsonArray1.getJSONObject(0).getString("og:image");
                }
            }catch (Exception e){
                e.printStackTrace();
                snippet = "NO INFO FOUND";
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }
        return image;
    }
    public static String getUserReport(Integer userid, Date startDate, Date endDate) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        final String methodPath = "restws.report/findByUserIdBetweenDate/" + userid + "/" + sdf.format(startDate)+ "/" + sdf.format(endDate);
        String textResult = "";
        URL userUrl;
        HttpURLConnection conn = null;
        try {
            userUrl = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection) userUrl.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String getDataforPie(Integer userid, Date date) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        final String methodPath = "restws.report/findRemainingCaloriByUserIdandDate/" + userid + "/" + sdf.format(date);
        String textResult = "";
        URL userUrl;
        HttpURLConnection conn = null;
        try {
            userUrl = new URL(BASE_URL + methodPath);
            conn = (HttpURLConnection) userUrl.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (NullPointerException e5) {
            e5.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }
    }