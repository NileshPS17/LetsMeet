package com.rippleworks.letsmeet;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class HomeFragment extends Fragment {

Button b1;
    String name;
    public HomeFragment() {
        // Required empty public constructor
    }
    public void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle=this.getArguments();
        //name=bundle.getString("name");
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv=(TextView)getView().findViewById(R.id.textview1);
        tv.setText("Hi "+name+",Your list for today!");
        b1=(Button)getView().findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setText("Clicked");
            }
        });


    }
}
