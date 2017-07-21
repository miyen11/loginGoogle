package com.cvamedios.clipco_login;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {


    public static final int SING_IN_CODE = 777;
    public static final int FACEBBOK_IN_CODE =666;
    public EditText name, email,user,password;
    public Button loginInApp;
    public SignInButton btnGoogle;
    private GoogleApiClient googleApiClient;
    public LoginButton loginButton;
    public Button button2;
    public CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();


        callbackManager = CallbackManager.Factory.create();

        loginButton =(LoginButton)findViewById(R.id.loginButtonFacebook);
        btnGoogle = (SignInButton)findViewById(R.id.buttonLoginGogle);
        name = (EditText)findViewById(R.id.editTextNombreCompleto);
        email =(EditText)findViewById(R.id.editTextEmail);
        user = (EditText)findViewById(R.id.editTextUser);
        password =(EditText)findViewById(R.id.editTextPassword);
        btnGoogle.setOnClickListener(this);

        loginInApp = (Button)findViewById(R.id.buttonRegistarr);
        loginInApp.setOnClickListener(this);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScream();
            }

            @Override
            public void onCancel() {
                Toast.makeText(login.this, "no se ha podido iniciar session", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(login.this, "error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
        if (requestCode == SING_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonRegistarr:
                Registrar();
                break;
            case R.id.buttonLoginGogle:
                LoginGoogle();
                break;


        }
    }

    private void Registrar() {
        usuarios  us = new usuarios();
        us.setName(name.toString());
        us.setEmail(email.toString());
        us.setUser(user.toString());
        us.setPassword(password.toString());
        //falta importar librerias de json y crear el opbjeto json
        //una ves creados el objeto json lo mandamos al servidor como una petición y vamos a recibir un token de session que tenemos que guardar


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void LoginGoogle() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,SING_IN_CODE);
    }



    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){
            goMainScream();
        }else {
            Toast.makeText(this, "no se ha podido iniciar session", Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScream() {
        Intent intent = new Intent(this, principal.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
