package com.example.searchviewcomlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> SITES = new ArrayList<>();
    ArrayList<String> SITES_copia;
    ArrayAdapter<String> meuArrayAdapter;

    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView1);
        searchView = findViewById(R.id.searchView1);

        SITES.add("Site: Ead IFMS.com");
        SITES.add("Site: GitHub.com");
        SITES.add("Site: StackOverflow.com");

        //duplicando para não alterar o original na busca
        SITES_copia = new ArrayList<>(SITES);


        meuArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, SITES);

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
                //forma 1: utilizar filter pronto
                    //MainActivity1.this.meuArrayAdapter.getFilter().filter(s);

                //forma 2: fazer a busca manualmente
                    fazerBusca(s);
                    meuArrayAdapter.notifyDataSetChanged();

                return false;
            }
        });

    }

    private void fazerBusca(String s) {
        //limpando array que monta a lista ao buscar algum termo na searchView
        SITES.clear();

        //digitou algo e apagou = trazer todos. SITES_copia contém todos
        if (s.isEmpty()) {
            SITES.addAll(SITES_copia);
        } else {
            //algum texto digitado na busca
            //converte para letra minúscula para não haver distinção
            s = s.toLowerCase();
            //percorre o array com os dados originais e busca
            for (String item : SITES_copia) {
                //caso, nos dados originais, exista o termo procurado, popule o array vazio com o item
                if (item.toLowerCase().contains(s)) {
                    SITES.add(item);
                }
            }
        }
    }
}