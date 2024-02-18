package com.example.notetakingappone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView create_txt;
    ArrayList<Note>notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView1);
        create_txt=findViewById(R.id.create);
        notes = new ArrayList<>();
        DBsqlite dBsqlite=new DBsqlite(MainActivity.this);
        ArrayList<Note> notes = dBsqlite.getdata();
        RvAdaptor adaptor = new RvAdaptor(MainActivity.this, notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adaptor);




        create_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                    Intent intent = new Intent(MainActivity.this, CRUD_Activity.class);
                    startActivity(intent);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });



    }


}