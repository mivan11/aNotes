package com.example.moje.obicnebiljeske;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStreamWriter;


public class BTevidencija extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evidencija);
        Button spremi = (Button) findViewById(R.id.button);
        Button izbrisi = (Button) findViewById(R.id.button6);


        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                saveButton(v);
            }
        });
        izbrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                brisanje(v);
            }
        });



    }


    public void saveButton(View v) {
        String editText = ((EditText) findViewById(R.id.Edittext)).getText().toString().replaceAll("\\n", " ");

        if(editText.equals("")) {
        }

        else{
            Intent intent = new Intent();
            intent.putExtra(pomocna_evidencija.INTENT_MESSAGE_FIELD,editText);
            setResult(pomocna_evidencija.INTENT_RESULT_CODE, intent);
            finish();
        }
    }

    public void brisanje(View v) {}













}










