package com.example.moje.obicnebiljeske;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.OutputStreamWriter;

public class pomocna_poruka extends AppCompatActivity {
    String poruka;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evidencija);
        Button spremi = (Button) findViewById(R.id.button);
        Button brisi = (Button) findViewById(R.id.button6);
        Intent intent = getIntent();
        poruka=intent.getStringExtra(pomocna_evidencija.INTENT_MESSAGE_DATA);
        position = intent.getIntExtra(pomocna_evidencija.INTENT_ITEM_POSITION,-1);
        EditText messagedata = (EditText) findViewById(R.id.Edittext);
        messagedata.setText(poruka);

        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                saveButton();
            }
        });
        brisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                brisanje();
            }
        });
    }
    public void saveButton() {
        String promjenjena = ((EditText) findViewById(R.id.Edittext)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(pomocna_evidencija.INTENT_CHANGED_MESSAGE, promjenjena);
        intent.putExtra(pomocna_evidencija.INTENT_ITEM_POSITION, position);
        setResult(pomocna_evidencija.INTENT_RESULT_CODE_2, intent);

        finish();
    }
    public void brisanje()
    {
        Intent intent = new Intent();
        intent.putExtra(pomocna_evidencija.INTENT_ITEM_POSITION, position);
        setResult(pomocna_evidencija.INTENT_RESULT_CODE_3, intent);
        finish();
    }
}
