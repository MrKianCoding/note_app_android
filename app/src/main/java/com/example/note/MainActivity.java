package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton buttonAdd;
    RecyclerView recyclerView;
    List<NoteModel> noteModelList;
    NoteAdapter noteAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        buttonAdd = findViewById(R.id.buttonAdd);


        buttonAdd.setOnClickListener(v->{
            startActivity(new Intent(this,AddNoteActivity.class));
        });

        DatabaseHelper db = new DatabaseHelper(this);
        noteModelList = db.getAllNotes();
        noteAdapter = new NoteAdapter(noteModelList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(noteAdapter);

    }

}