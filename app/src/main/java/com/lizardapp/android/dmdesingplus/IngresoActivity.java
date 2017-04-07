package com.lizardapp.android.dmdesingplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.security.Principal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarException;

public class IngresoActivity extends AppCompatActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView info;
    String emailID;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    AccessToken accessToken;
    Button botoningreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();

                Profile profile = Profile.getCurrentProfile();
                final GraphRequest request = GraphRequest.newMeRequest(
                        accessToken, new GraphRequest.GraphJSONObjectCallback() {
                            @Override

                            public void onCompleted(JSONObject object, GraphResponse response) {

                                Log.d("json",object.toString());

                                try {
                                    emailID = object.getString("email");

                                    Log.d("mail",emailID);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.d("mail","error en el mail");
                                }
                            }
                        });
                request.executeAsync();
                nextActivity(profile);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        setContentView(R.layout.activity_ingreso);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        info = (TextView)findViewById(R.id.textView) ;

        List<String> permissions = new ArrayList<>();
        permissions.add("public_profile, email");
       // permissions.add("email");

        loginButton.setReadPermissions("email");



       // LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        loginButton.registerCallback(callbackManager, callback);

        botoningreso = (Button) findViewById(R.id.button2);

        botoningreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TareaInsertarDatosUser tarea = null;
                    tarea = new TareaInsertarDatosUser();

                tarea.execute();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent main = new Intent(IngresoActivity.this, Main2Activity.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("usurname", profile.getLastName());
            info.setText(profile.getName());
            main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());


            main.putExtra("mail",emailID);





            new DownloadImage((ImageView)findViewById(R.id.imageView)).execute(profile.getProfilePictureUri(200,200).toString());
            startActivity(main);
        }
    }
}
