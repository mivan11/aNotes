package com.example.moje.obicnebiljeske;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BTdnevnik extends AppCompatActivity {

    String poruka;
    int position;
    ListView lista;
    ArrayList<String> list = new ArrayList();
    ArrayAdapter<String> nizadapter;
    //private Spinner spinner;
    //private static final String[]paths = {"item 1", "item 2", "item 3"};
  //  Spinner spinner;
    //final String[] paths = {"item 1", "item 2", "item 3"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnevnik);

        lista = (ListView) findViewById(R.id.listView1);
        nizadapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        lista.setAdapter(nizadapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), pomocna_poruka.class);
                intent.putExtra(pomocna_evidencija.INTENT_MESSAGE_DATA, list.get(position).toString());
                intent.putExtra(pomocna_evidencija.INTENT_ITEM_POSITION, position);
                startActivityForResult(intent, pomocna_evidencija.INTENT_REQUEST_CODE_2);
            }
        });

        try {                                                                           //  ucitavanje svih zadataka u listu
            Scanner sc = new Scanner(getApplicationContext().openFileInput("dnevnik.txt"));
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                nizadapter.add(data);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Button dodaj = (Button) findViewById(R.id.button2);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ntent = new Intent();
                ntent.setClass(getApplicationContext(), BTevidencija.class);
                startActivityForResult(ntent, pomocna_evidencija.INTENT_REQUEST_CODE);
            }
        });
        Button omeni = (Button) findViewById(R.id.novak);
        omeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent english = new Intent(BTdnevnik.this, kontakt.class);
                startActivity(english);
            }
        });






/*        Spinner staticSpinner = (Spinner) findViewById(R.id.dynamic_spinner );
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.mogucnosti, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
        final Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);
        final String[] items = new String[] {".\n.\n.", "Kontakt", "O aplikaciji" };
        final String[] items2 = new String[] { "" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
       final  ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items2);
        dynamicSpinner.setAdapter(adapter);
                      dynamicSpinner.setOnItemSelectedListener(new OnItemSelectedListener()  {
                            @Override
          public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                                if (position == 0) {
                                }
                    //switch (position) {
                        //case 0:
                                if (position == 1) {
                                                       //Log.v("item", (String) parent.getItemAtPosition(position));
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("message/rfc822");
                            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"prcija_vage@gmail.com"});
                            i.putExtra(Intent.EXTRA_SUBJECT, "Naslov poruke");
                            i.putExtra(Intent.EXTRA_TEXT, "Napišite Vaš problem");
                           // try {
                                startActivity(Intent.createChooser(i, "Pošalji e-mail..."));
                            //} catch (android.content.ActivityNotFoundException ex) {
                              //  Toast.makeText(getApplicationContext(), "Nemate instaliranog nijednog email klijenta...", Toast.LENGTH_SHORT).show();
position =0;
                            //}
                            // Whatever you want to happen when the second item gets selected
                            //break;
                        //case 1:
                            }
                            if (position == 2) {
                                Intent english = new Intent(BTdnevnik.this, BTevidencija.class);
                                startActivity(english);
           //                 Log.v("item", (String) parent.getItemAtPosition(position));
                            // Whatever you want to happen when the thrid item gets selected
                            }
                }
                @Override
                public void onNothingSelected (AdapterView < ? > parent){
                    // TODO Auto-generated method stub
                    //staticSpinner.setAdapter(adapter2 );
position =0;
                }
        });*/




    }













    @Override                                                                            // dodavanje zadatka, i ispravljanje prosloga i spremanje
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode==pomocna_evidencija.INTENT_REQUEST_CODE)                            // unos podatka u listu
        {
            poruka = data.getStringExtra(pomocna_evidencija.INTENT_MESSAGE_FIELD);
            list.add(poruka);
            nizadapter.notifyDataSetChanged();
        }
        else if(resultCode == pomocna_evidencija.INTENT_REQUEST_CODE_2) {                // izmjena pojedinog podatka iz liste
            poruka = data.getStringExtra(pomocna_evidencija.INTENT_CHANGED_MESSAGE);
            position = data.getIntExtra(pomocna_evidencija.INTENT_ITEM_POSITION,-1);
            list.remove(position);
            list.add(position,poruka);
            nizadapter.notifyDataSetChanged();
        }
        else if(resultCode==3)
        {
            position = data.getIntExtra(pomocna_evidencija.INTENT_ITEM_POSITION,-1);
            list.remove(position);
            nizadapter.notifyDataSetChanged();
        }
        try {                                                                              // spremanje podataka
            PrintWriter pw = new PrintWriter(getApplicationContext().openFileOutput("dnevnik.txt", Activity.MODE_PRIVATE ));
//String novi="";

            for (String str : list) {

                //pw.append(str);
                //pw.append(str);
                //pw.append(" ");
                //novi = str;
pw.println(str);



            }

//pw.print(novi);

            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

