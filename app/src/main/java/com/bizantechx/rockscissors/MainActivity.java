package com.bizantechx.rockscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance(); //we created the database
    DatabaseReference reference = db.getReference(); //we created the root reference
    DatabaseReference game = reference.child("game"); //we created the child reference
    TextView textview ;
    Button rock , paper , scissor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //reference.child("name").setValue("cool");
        textview = (TextView) findViewById(R.id.text);
        rock =(Button) findViewById(R.id.Rock);
        paper =(Button) findViewById(R.id.Paper);
        scissor =(Button) findViewById(R.id.Scissors);
        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setValue("Rock");
            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setValue("Paper");
            }
        });
        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.setValue("Scissor");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        game.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text= dataSnapshot.getValue().toString();//we are extracting the value on datachange by user.
                textview.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
               // Toast.makeText(this,"ERROR 401",Toast.LENGTH_SHORT).show();
                Log.i("error","401");
            }
        });
    }
}
