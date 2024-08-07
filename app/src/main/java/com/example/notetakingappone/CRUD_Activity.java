package com.example.notetakingappone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notetakingappone.databinding.ActivityCurdBinding;
import com.example.notetakingappone.databinding.ActivityMainBinding;

public class CRUD_Activity extends AppCompatActivity {
    private ActivityCurdBinding binding;

    DBsqlite dBsqlite;

    Note note;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCurdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dBsqlite = new DBsqlite(CRUD_Activity.this);
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchNote();
            }
        });
        binding.btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateData();
            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 deleteData();
            }
        });
    }

    private void deleteData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CRUD_Activity.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you want to delete Notes ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean successfully = dBsqlite.deletedata(binding.titleEt.getText().toString());
                if (successfully) {
                    Toast.makeText(CRUD_Activity.this, "Notes delete Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CRUD_Activity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    private void updateData() {

        AlertDialog.Builder builder = new AlertDialog.Builder(CRUD_Activity.this);
        builder.setTitle("Update");
        builder.setMessage("Are you want to Update Notes ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean successfully = dBsqlite.Updatedata(binding.titleEt.getText().toString(), binding.contentEt.getText().toString());
                if (successfully) {
                    Toast.makeText(CRUD_Activity.this, "Notes Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CRUD_Activity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();

    }


    private void insertData() {
        String title_var = binding.titleEt.getText().toString();
        String content_var = binding.contentEt.getText().toString();
        if (title_var.isEmpty()) {
            Toast.makeText(CRUD_Activity.this, "Please provide Title", Toast.LENGTH_SHORT).show();
        } else if (content_var.isEmpty()) {
            Toast.makeText(CRUD_Activity.this, "Please provide any note", Toast.LENGTH_SHORT).show();
        } else {

            if (dBsqlite.Insertdata(title_var, content_var)) {
                Toast.makeText(CRUD_Activity.this, "Note Added Successfully", Toast.LENGTH_SHORT).show();


                clearFields();

            } else {
                Toast.makeText(CRUD_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }


        }
    }


    public void clearFields() {
        binding.titleEt.setText("");
        binding.contentEt.setText("");
    }
    public void searchNote()
    {
        String search_title = binding.titleEt.getText().toString();
        if (!search_title.isEmpty()) {
            String data = dBsqlite.searchNote(search_title);
            if (data != null) {
                binding.contentEt.setText(data);
            } else {
                Toast.makeText(CRUD_Activity.this, "N0 Note Found", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(CRUD_Activity.this, "Please Enter Title", Toast.LENGTH_SHORT).show();
        }
    }
}