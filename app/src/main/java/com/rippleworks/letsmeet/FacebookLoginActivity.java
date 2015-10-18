package com.rippleworks.letsmeet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class FacebookLoginActivity extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView tv;
    LoginManager loginManager;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook_login);

        tv = (TextView)findViewById(R.id.textView_fb);
        loginManager = LoginManager.getInstance();
        imageView = (ImageView)findViewById(R.id.imageView);



        callbackManager = CallbackManager.Factory.create();

        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(FacebookLoginActivity.this, loginResult.getAccessToken().getUserId() + "", Toast.LENGTH_LONG).show();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        tv.setText(object.toString());
                        URL url=null;
                        try{
                            url  = new URL(object.getJSONObject("picture").getJSONObject("data").getString("url"));
                        }
                        catch(MalformedURLException e){}
                        catch(JSONException e){}

                        new ImageLoader().execute(url);
                    }
                });
                Bundle params = new Bundle();
                params.putString("fields", "id,name,link,email,picture.width(300)");
                request.setParameters(params);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Toast.makeText(FacebookLoginActivity.this, "Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {

            }
        });



        loginManager.logInWithReadPermissions(this,  Arrays.asList(new String[]{"public_profile", "email", "user_friends", "user_status"}));


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    class ImageLoader extends AsyncTask<URL, Void, Bitmap>
    {
        ProgressDialog dialog = new ProgressDialog(FacebookLoginActivity.this);

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            dialog.cancel();
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setTitle("Loading..");
            dialog.show();
        }

        @Override
        protected Bitmap doInBackground(URL... params) {
            try {
                URL url = params[0];
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }
            catch(Exception e){
                Toast.makeText(getApplicationContext(), "Asyc Task : "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
            return null;
        }
    }
}
