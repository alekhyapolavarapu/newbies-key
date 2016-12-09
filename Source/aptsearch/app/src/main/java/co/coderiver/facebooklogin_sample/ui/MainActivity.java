package co.coderiver.facebooklogin_sample.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

import co.coderiver.facebooklogin_sample.MapsActivity;
import co.coderiver.facebooklogin_sample.R;
import co.coderiver.facebooklogin_sample.SignupActivity;
import co.coderiver.facebooklogin_sample.middlescreen;
import co.coderiver.facebooklogin_sample.util.IntentUtil;
import co.coderiver.facebooklogin_sample.util.PrefUtil;
import co.coderiver.facebooklogin_sample.viewinterface.ListActivity;


public class MainActivity extends AppCompatActivity {
public static boolean isValid = false;
    private CallbackManager callbackManager;
    private TextView info;
    private ImageView profileImgView;
    private LoginButton loginButton;


    private PrefUtil prefUtil;
    private IntentUtil intentUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);

        prefUtil = new PrefUtil(this);
        intentUtil = new IntentUtil(this);

        info = (TextView) findViewById(R.id.info);
        profileImgView = (ImageView) findViewById(R.id.profile_img);
        loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                info.setText(message(profile));

                String userId = loginResult.getAccessToken().getUserId();
                String accessToken = loginResult.getAccessToken().getToken();

                // save accessToken to SharedPreference
                prefUtil.saveAccessToken(accessToken);

                String profileImgUrl = "https://graph.facebook.com/" + userId + "/picture?type=large";


                Glide.with(MainActivity.this)
                        .load(profileImgUrl)
                        .into(profileImgView);
                Intent redirect = new Intent(MainActivity.this, middlescreen.class);
                startActivity(redirect);
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
                info.setText("Login attempt failed.");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_show_access_token:
                intentUtil.showAccessToken();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        deleteAccessToken();
        Profile profile = Profile.getCurrentProfile();
        info.setText(message(profile));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private String message(Profile profile) {
        StringBuilder stringBuffer = new StringBuilder();
        if (profile != null) {
            stringBuffer.append("Welcome ").append(profile.getName());
        }
        return stringBuffer.toString();
    }

    private void deleteAccessToken() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                    //User logged out
                    prefUtil.clearToken();
                    clearUserArea();
                }
            }
        };
    }

    private void clearUserArea() {
        info.setText("");
        profileImgView.setImageDrawable(null);
    }
    public void Login(View view){

        EditText username = (EditText) findViewById(R.id.etusername);
         final EditText password = (EditText) findViewById(R.id.etpassword);
        final Intent intent = new Intent(this,middlescreen.class);
        final Intent mainIntent = new Intent(this,MainActivity.class);

        String urlEncodedName = URLEncoder.encode(username.getText().toString());
        if ((username.getText().toString().isEmpty()&& password.getText().toString().isEmpty())){
            Toast.makeText(getApplicationContext(),"Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
        String url = "https://api.mlab.com/api/1/databases/avins/collections/signup?q=%7B%22userName%22%3A%22"+urlEncodedName+"%22%7D&apiKey=BPAbTiM732ZB-sDBMcKrmJeVudsLoIZR";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("Error from the service", e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String respJson = response.body().string();
                Log.i("Json String: ", respJson);
                if (respJson.isEmpty()) {
                    Log.e("response json is empty", respJson);
                } else {
                    try {
                        Log.i("++++++++", "++++++++");
                        JSONArray responseJson = new JSONArray(respJson);
                        if (responseJson.length() == 0){
                            Log.e("response json is empty", respJson);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    createErrorDialog();
                                }
                            });

                        }
                        JSONObject jObj = (JSONObject) responseJson.get(0);
                        String pass = jObj.get("password").toString();
                        String user = jObj.get("userName").toString();
                        if (!password.getText().toString().equalsIgnoreCase(pass))

                        {
                            Log.i("password doesn't match", "-------");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    createErrorDialog();
                                }
                            });

                        } else {
                            Log.i("********", "*******");
                            isValid = true;
                            intent.putExtra("USER", user);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }}

     public void createErrorDialog(){
         AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
         alert.setMessage("Wrong username or password");
         alert.setTitle("Error message");
         alert.setPositiveButton("OK",null);
         alert.setCancelable(true);
         AlertDialog alertDialog = alert.create();
         alertDialog.show();

         alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

             @Override
             public void onClick(DialogInterface dialog, int which) {
                 Intent mIntent = new Intent(MainActivity.this,MainActivity.class);
                 startActivity(mIntent);
             }
         });


     }
    public void doRegister(View view){
        Intent redirect = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(redirect);
    }
    }
