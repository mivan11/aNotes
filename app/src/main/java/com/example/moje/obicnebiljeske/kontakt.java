package com.example.moje.obicnebiljeske;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class kontakt extends AppCompatActivity {
    String poruka;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.akontakt);
        TextView tekst = (TextView) findViewById(R.id.textView6);
        tekst.setText("Dobrodošli u aplikaciju aNotes\nAplikacija aNotes je studentski projekt koji je izrađen na temelju već sličnih aplikacija. " +
                "\nZa sve dodatne informacije u vezi aplikacije možete se javiti putem e-mail ikone u kojoj su već konfigurirane postavke za slanje e-maila."
 +"\n\nVerzija 1.0 @2017"
        );
        Button email=(Button) findViewById(R.id.eml);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"mailzaprintanje1911@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Naslov poruke");
                i.putExtra(Intent.EXTRA_TEXT, "Napišite poruku");
                try {
                    startActivity(Intent.createChooser(i, "Pošalji e-mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "Nemate instaliranog nijednog email klijenta...", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}


