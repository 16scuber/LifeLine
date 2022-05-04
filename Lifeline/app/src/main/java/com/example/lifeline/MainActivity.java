package com.example.lifeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lifeline.messages.MessagesAdapter;
import com.example.lifeline.messages.MessagesList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<MessagesList> messagesLists = new ArrayList<>();
    private String mobile, name, password;
    private RecyclerView messagesRecyclerView;
    TextView nameView, lastMessageView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://lifeline-42a1d-default-rtdb.firebaseio.com/");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameView = findViewById(R.id.textViewMessageName);
        lastMessageView = findViewById(R.id.textViewMessageLast);

        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
        mobile = getIntent().getStringExtra("mobile");
        name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");

        messagesRecyclerView.setHasFixedSize(true);
        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //get profile pic from database-- SKIP FOR NOW

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                messagesLists.clear();

                for(DataSnapshot dataSnapshot : snapshot.child("users").getChildren()) {
                    final String getMobile = dataSnapshot.getKey();

                    if (!getMobile.equals(mobile)) {
                        final String getName = dataSnapshot.child("name").getValue(String.class);
                        final String getPassword = dataSnapshot.child("password").getValue(String.class);

                        MessagesList messagesList = new MessagesList(getName, getMobile, getPassword, "", 0 );
                        messagesLists.add(messagesList);
                    }
                }

                messagesRecyclerView.setAdapter(new MessagesAdapter(messagesLists, MainActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}