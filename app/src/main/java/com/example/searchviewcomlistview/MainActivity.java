package com.example.searchviewcomlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<Texto> arrayListTexto = new ArrayList<>();
    ArrayList<Texto> arrayListTextoCopia;

    ArrayAdapter<Texto> meuArrayAdapter;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView1);
        searchView = findViewById(R.id.searchView1);

        Texto texto1 = new Texto("texto 1 askhdfakjsd");
        Texto texto2 = new Texto("texto 2 askhdfakjsd");
        Texto texto3 = new Texto("texto 3 askhdfakjsd");

        arrayListTexto.add(texto1);
        arrayListTexto.add(texto2);
        arrayListTexto.add(texto3);

        //duplicando para não alterar o original na busca
        arrayListTextoCopia = new ArrayList<>(arrayListTexto);

        meuArrayAdapter = new ArrayAdapter<Texto>(this,
                android.R.layout.simple_list_item_1, arrayListTexto);

        listView.setAdapter(meuArrayAdapter);

        //searchView sempre aberto
        searchView.setIconified(false);

        //fechar teclado
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            //ao alterar o texto - cada caractere digitado
            @Override
            public boolean onQueryTextChange(String s) {
                //forma 1: utilizar filter pronto (não busca letras dentro de palavras)
                    //MainActivity1.this.meuArrayAdapter.getFilter().filter(s);

                //forma 2: fazer a busca manualmente (busca letras dentro de palavras)
                    fazerBuscaObj(s);
                    meuArrayAdapter.notifyDataSetChanged();

                return false;
            }
        });

    }

    private void fazerBuscaObj(String s) {
        //limpando array que monta a lista ao buscar algum termo na searchView
        arrayListTexto.clear();
        //digitou algo e apagou = trazer todos. SITES_copia contém todos
        if (s.isEmpty()) {
            arrayListTexto.addAll(arrayListTextoCopia);
        } else {
            //algum texto digitado na busca
            //converte para letra minúscula para não haver distinção
            s = s.toLowerCase();
            //percorre o array com os dados originais e busca
            for (Texto item : arrayListTextoCopia) {
                //caso, nos dados originais, exista o termo procurado, popule o array vazio com o item
                if (item.getTexto().toLowerCase().contains(s)) {
                    arrayListTexto.add(item);
                }
            }
        }
    }
}