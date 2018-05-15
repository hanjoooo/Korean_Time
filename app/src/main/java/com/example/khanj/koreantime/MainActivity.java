package com.example.khanj.koreantime;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DevicePolicyManager deviceMgr;
    ComponentName comp;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("siren");
    DatabaseReference myRef1 = database.getReference("lock");
    int x=0;

    SoundPool pool;
    int ddok;
    int hello;
    Button Control;
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
        if (BuildConfig.DEBUG) {
            Log.d("ScreenLock", "MainActivity oncreate.");
        }
        deviceMgr = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        comp = new ComponentName(this, ScreenLockDeviceAdminReceiver.class);
        if (!deviceMgr.isAdminActive(comp)) {
            Log.d("ScreenLock", "Main :admin is false");
            Intent intent = new Intent(
                    DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, comp);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    getString(R.string.devicePolicyManagerMessage));

            startActivityForResult(intent, 0);
        } else {
            pool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            //ddok에 사운드를 로드함
            ddok = pool.load(this, R.raw.sese, 1);
            ValueEventListener postListener1 = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    x = dataSnapshot.getValue(int.class);
                    if(x==1){
                        Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                        startService(intent);
                        Log.d("ScreenLock", "Main : admin is true");
                        deviceMgr.lockNow();
                        finish();
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                        stopService(intent);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    ;
                }
            };
            myRef1.addValueEventListener(postListener1);
        }

        Intent intent = new Intent(getApplicationContext(),MeetingActivity.class);
        startActivity(intent);

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            deviceMgr.lockNow();
        } else {
            Toast.makeText(this, R.string.failActivation, Toast.LENGTH_LONG)
                    .show();
        }
        finish();
    }
}
