package com.rippleworks.letsmeet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//test

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderboardFragment extends Fragment {

     String[] leaderboard={"Hemand","Nilesh","Arun"};
    public LeaderboardFragment() {
        // Required empty public constructor
    }
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_leaderboard, container, false);
        return view;
    }

@Override
public void onActivityCreated(Bundle savedInstanceState){
    super.onActivityCreated(savedInstanceState);
    ListView listView=(ListView)getActivity().findViewById(R.id.listview);
    ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,leaderboard);
    listView.setAdapter(adapter);

}
}
