package com.example.khanj.koreantime;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;

public class LockActivity extends Activity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef1 = database.getReference("lock");
    int x=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        ValueEventListener postListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                x = dataSnapshot.getValue(int.class);
                if(x==0){
                    finish();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                ;
            }
        };
        myRef1.addValueEventListener(postListener1);
    }

    @Override
    public void onBackPressed(){
       // super.onBackPressed();
    }
    @Override
    protected void onUserLeaveHint() {
        //super.onUserLeaveHint();
    }



}