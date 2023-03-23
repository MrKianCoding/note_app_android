package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    EditText editTextTitle, editTextBody;
    Button buttonAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextBody = findViewById(R.id.editTextBody);
        buttonAddNote = findViewById(R.id.buttonAddNote);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy");

        buttonAddNote.setOnClickListener(v -> {
            if (editTextTitle.getText().toString().trim().isEmpty() ||
                    editTextBody.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please enter your title and text", Toast.LENGTH_SHORT).show();
            } else {
                DatabaseHelper db = new DatabaseHelper(this);
                String date = simpleDateFormat.format(new Date());
                NoteModel noteModel = new NoteModel(editTextTitle.getText().toString(),
                        editTextBody.getText().toString(),date);
                db.addNote(noteModel);
                Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this,MainActivity.class));
                finish();
            }
        });
    }
}