package com.example.javafirstapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Toast;

import java.util.ArrayList;

public class Afficher extends AppCompatActivity implements TextWatcher, AdapterView.OnItemClickListener , DialogInterface.OnClickListener {

    EditText recherche ;
    ListView liste_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher);
        recherche = (EditText) findViewById(R.id.recherch);
        liste_view = (ListView) findViewById(R.id.liste_view);
        recherche.addTextChangedListener(this);
        ArrayList<COntact> data = Acceuill.data;
        Toast.makeText(this,"taille tot= "+Acceuill.data.size(),Toast.LENGTH_LONG).show();
        //ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Acceuill.data);
        MonAdapter monad = new MonAdapter(this,Acceuill.data);
        liste_view.setAdapter(monad);
        liste_view.setOnItemClickListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //recherche dan array list data s.contain ab
        //ajouter adaptateur pour affiche array list

    }

    int indice;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        indice=position;
        Toast.makeText(this, ""+id,Toast.LENGTH_LONG).show();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Editing");
        alert.setMessage("Voulez vous supprimer cet element ");
        alert.setPositiveButton("Confirmer",this);
        alert.setNegativeButton("Annuler",this);
        alert.setNeutralButton("Modifier",this);
        alert.show();


    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which ==AlertDialog.BUTTON_POSITIVE){

            Acceuill.data.remove(indice);
            liste_view.invalidateViews(); // reload view
        }
        if(which ==dialog.BUTTON_NEGATIVE){



        }
        if(which ==AlertDialog.BUTTON_NEUTRAL){
            Intent i = new Intent(Afficher.this , UpdateUser.class);
            i.putExtra("nom_user",Acceuill.data.get(indice).getNom().toString());
            i.putExtra("prenom_user",Acceuill.data.get(indice).getPrenom().toString());
            i.putExtra("tel_user",Acceuill.data.get(indice).getNum_tel().toString());
            i.putExtra("indice", indice);
            startActivity(i);

        }

    }
}
