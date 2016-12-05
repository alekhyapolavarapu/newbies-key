package co.coderiver.facebooklogin_sample;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.EditText;
        import android.widget.Toast;
        import java.lang.String;



        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

        import co.coderiver.facebooklogin_sample.beans.User;
        import co.coderiver.facebooklogin_sample.ui.MainActivity;
        import com.google.gson.Gson;
        import com.squareup.okhttp.Callback;
        import com.squareup.okhttp.MediaType;
        import com.squareup.okhttp.OkHttpClient;
        import com.squareup.okhttp.Request;
        import com.squareup.okhttp.RequestBody;
        import com.squareup.okhttp.Response;
        import java.io.IOException;

public class SignupActivity extends AppCompatActivity {

    // EditText  Emailid, Username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);





       /* Emailid = (EditText)findViewById(R.id.etemail);

        Username = (EditText)findViewById(R.id.etusername);
        password = (EditText)findViewById(R.id.etpassword);*/

    }

    public void Register(View view) {

        final Intent intent = new Intent(this, MainActivity.class);
        String EmailId = ((EditText) findViewById(R.id.etemail)).getText().toString();

        EditText userName = (EditText) findViewById(R.id.etusername);
        EditText password = (EditText) findViewById(R.id.etpassword);

        //Firebase RegistrationRef = new Firebase("https://findyourtechnician.firebaseio.com/");


        if (!validateEmail(EmailId)) {
            Toast.makeText(getApplicationContext(),"please enter valid email addresss", Toast.LENGTH_SHORT).show();
        } else if ((password.length() < 8) || (password.length() > 15)) {
            password.setError("Password should be between 8 and 15 characters in length");
            password.requestFocus();
        }else if ((userName.getText().toString().isEmpty())) {
            userName.setError("UserName cannot be empty");
            userName.requestFocus();
        }else {

            //final Intent mainIntent = new Intent(this,Main.class);
            // EditText name = (EditText) findViewById(R.id.editText_name);

            // EditText phone = (EditText) findViewById(R.id.editText_phone);
            User user = new User();
            Gson gson = new Gson();
            user.setUserName(userName.getText().toString());
            user.setPassword(password.getText().toString());
            user.setEmailId(EmailId);

            String userJSON = gson.toJson(user);

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, userJSON);
            String requestUrl = "https://api.mlab.com/api/1/databases/avins/collections/signup?apiKey=BPAbTiM732ZB-sDBMcKrmJeVudsLoIZR";
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(requestUrl).post(body).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e("Error from the service", e.getMessage());
                    startActivity(intent);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    final String respJson = response.body().string();
                    Log.i("Json String: ", respJson);
                    if (respJson.isEmpty()) {
                        Log.e("response json is empty", respJson);
                    }

                }
            });
            intent.putExtra("USER", userName.getText().toString());

            Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }

    public void redirectToLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public boolean validateEmail(String Email) {
        String EmailPattern = "^[A-Za-z][A-Za-z0-9]*([._-]?[A-Za-z0-9]+)@[A-Za-z]+.[A-Za-z]{0,3}$";

        Pattern pattern = Pattern.compile(EmailPattern);
        Matcher matcher = pattern.matcher(Email);

        return matcher.matches();
    }
}

   // @Override
   /* public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_registration, menu);
        return true;
    }*/

    //@Override
  /*  public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
