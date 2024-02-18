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

public class CRUD_Activity extends AppCompatActivity {

    EditText title, content;
    TextView back;
    Button insert, delete, search, update;
    DBsqlite dBsqlite;

    Note note;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curd);
        title = findViewById(R.id.title_et);
        content = findViewById(R.id.content_et);
        insert = findViewById(R.id.btn_insert);
        search = findViewById(R.id.btn_search);
        delete = findViewById(R.id.btn_delete);
        update = findViewById(R.id.btn_update);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CRUD_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        dBsqlite = new DBsqlite(CRUD_Activity.this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchnote();

            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_var = title.getText().toString();
                String content_var = content.getText().toString();
                if (title_var.isEmpty()) {
                    Toast.makeText(CRUD_Activity.this, "Please provide Title", Toast.LENGTH_SHORT).show();
                } else if (content_var.isEmpty()) {
                    Toast.makeText(CRUD_Activity.this, "Please provide any note", Toast.LENGTH_SHORT).show();
                } else {

                    if (dBsqlite.Insertdata(title_var, content_var)) {
                        Toast.makeText(CRUD_Activity.this, "Note Added Succesfully", Toast.LENGTH_SHORT).show();


                        clearfields();

                    } else {
                        Toast.makeText(CRUD_Activity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CRUD_Activity.this);
                builder.setTitle("Update");
                builder.setMessage("Are you want to Update Notes ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean successfully = dBsqlite.Updatedata(title.getText().toString(), content.getText().toString());
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
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CRUD_Activity.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you want to delete Notes ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean successfully = dBsqlite.deletedata(title.getText().toString());
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
        });
    }



    public void clearfields() {
        title.setText("");
        content.setText("");
    }
    public void searchnote()
    {
        String search_title = title.getText().toString();
        if (!search_title.isEmpty()) {
            String data = dBsqlite.searchNote(search_title);
            if (data != null) {
                content.setText(data);
            } else {
                Toast.makeText(CRUD_Activity.this, "N0 Note Found", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(CRUD_Activity.this, "Please Enter Title", Toast.LENGTH_SHORT).show();
        }
    }
}