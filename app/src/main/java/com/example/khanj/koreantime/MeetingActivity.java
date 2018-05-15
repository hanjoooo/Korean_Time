package com.example.khanj.koreantime;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MeetingActivity extends AppCompatActivity {

    private ArrayList<HashMap<String,String>> Data = new ArrayList<HashMap<String, String>>();
    private HashMap<String,String> InputData1 = new HashMap<>();
    private HashMap<String,String> InputData2 = new HashMap<>();
    private HashMap<String,String> InputData3 = new HashMap<>();
    private HashMap<String,String> InputData4 = new HashMap<>();
    private HashMap<String,String> InputData5 = new HashMap<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        listView =(ListView)findViewById(R.id.List_view);
        InputData1.put("title","창업아이템개발 스터디");
        InputData1.put("time","D-0D 0H 15M");
        Data.add(InputData1);

        InputData2.put("title","군대 동기 모임");
        InputData2.put("time","D-2D 3H 35M");
        Data.add(InputData2);

        InputData3.put("title","월드 디제이 페스티벌 모임");
        InputData3.put("time","D-4D 1H 43M");
        Data.add(InputData3);

        InputData4.put("title","종합설계 모임");
        InputData4.put("time","D-7D 17H 45M");
        Data.add(InputData4);

        InputData5.put("title","치킨 모임");
        InputData5.put("time","D-8D 4H 31M");
        Data.add(InputData5);
        //simpleAdapter 생성

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,Data,android.R.layout.simple_list_item_2,new String[]{"title","time"},new int[]{android.R.id.text1,android.R.id.text2});

        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);

            }
        });




    }
}
