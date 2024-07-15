package com.example.notetakingappone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.notetakingappone.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RecyclerView recyclerView;
    FloatingActionButton create_txt;
    ArrayList<Note>notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView=binding.recyclerView1;
        create_txt=binding.create;

        notes = new ArrayList<>();
        DBsqlite dBsqlite=new DBsqlite(MainActivity.this);
        notes = dBsqlite.getdata();
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