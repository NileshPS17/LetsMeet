package com.rippleworks.letsmeet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
//import letsmeetlib.*;
//import com.letsmeet.LetsMeetClient;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    public  int selected;
    RadioButton radioButton;
    boolean correct_name;
    String name,login,branch;
    final Boolean isLoggedIn=false;
    TextView textView;
    EditText e1;
    Button b1;
    private Pattern pattern;

    private static final String USERNAME_PATTERN = "^[a-zA-Z ]{4,}$";
    public static final String USER_NAME="name";
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         b1=(Button)findViewById(R.id.login);
        textView=(TextView)findViewById(R.id.invalid);
         e1=(EditText)findViewById(R.id.et_name);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup1);
        selected=radioGroup.getCheckedRadioButtonId();
        radioButton=(RadioButton)findViewById(selected);
       // branch=radioButton.getText().toString();
        name=e1.getText().toString();
        final SharedPreferences preferences=getSharedPreferences("my_prefs2",0);
        final SharedPreferences.Editor editor=preferences.edit();
        //LetsMeetClient client;



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(radioGroup.getCheckedRadioButtonId()==-1 ){
                    textView.setText("Select a Branch");
                }
              /* else if(!validate(name)){
                    e1.setError("Invalid Name");
                }*/
                else{
                    editor.putBoolean("loggedin",true);
                    editor.commit();
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    intent.putExtra(USER_NAME,name);
                   // intent.putExtra("branch",branch);
                    Login.this.startActivity(intent);
                    Login.this.finish();
                }
            }
        });

    }
    public boolean validate(final String username){

        return Pattern.matches(USERNAME_PATTERN,username);

    }
}
