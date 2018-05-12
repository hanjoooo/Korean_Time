package com.example.khanj.koreantime;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("siren");
    int x=0;
    SoundPool pool;
    int ddok;
    int hello;
    Button alarm;
    Button Siren;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        //ddok에 사운드를 로드함
        ddok = pool.load(this, R.raw.sese, 1);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                x = dataSnapshot.getValue(int.class);
                if(x==1){
                    hello=pool.play(ddok, 1, 1, 0, -1, 1);
                }
                else if(x==2){
                    hello=pool.play(ddok, 0.5f, 0.5f, 0, -1, 1);
                }
                else if(x==3){
                    hello=pool.play(ddok, 1, 1, 0, -1, 2);
                }
                else{
                    pool.stop(hello);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                ;
            }
        };
        myRef.addValueEventListener(postListener);

        Intent intent = new Intent(getApplicationContext(),MeetingActivity.class);
        startActivity(intent);
        }


}
