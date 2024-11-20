package com.example.searchviewcomlistview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView

    private lateinit var adapter: ArrayAdapter<String>

    private lateinit var editTextInf: EditText
    private lateinit var buttonAddInf: Button
    private lateinit var searchView_: SearchView

    private var itemList = ArrayList<String>() // Lista vazia de string

    private var itemListCopia = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView1)

        editTextInf = findViewById(R.id.editTextInfInput)
        buttonAddInf = findViewById(R.id.buttonAddInfo)

        searchView_ = findViewById(R.id.searchView)

        // .. começa expandido
        searchView_.isIconified = false

        itemList = arrayListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

        // copiar um array para outro (para a forma dois mais abaixo)
        itemListCopia.addAll(itemList)

        // código para adicionar item no arraylist
        // itemList.add("item 1") ou itemList.add(1, "item 1") para adicionar na posição

        //para remover elemento
        //lista.remove("Java") remove primeira ocorrência ou     lista.removeAt(0) pra remover na posição

        // Crie um adapter e vincule-o ao ListView
        // para alterar imagens dos itens, necessário adapter customizado... todo
        adapter = ArrayAdapter(this, R.layout.item_lista, R.id.textViewLista, itemList)

        listView.adapter = adapter

        buttonAddInf.setOnClickListener {
            if(editTextInf.text.trim().equals(""))
                editTextInf.error = "Preencha o campo"
            else{
                itemList.add(editTextInf.text.toString())
                adapter.notifyDataSetChanged() // Atualiza a ListView
            }
        }

        // exemplo 1: remoção sem confirmação
        listView.setOnItemClickListener { _, _, i, l ->
            //Log.e("clique", "clique: " + i + ". " + itemList[0])


            AlertDialog.Builder(this)
                .setTitle("Alerta")
                .setPositiveButton("OK") { dialog, which ->
                    itemList.removeAt(i) // Remove o item da lista
                    adapter.notifyDataSetChanged() // Atualiza a ListView
                }
                .setNeutralButton("Cancelar") { dialog, which -> }
                .show()
            // _ ignora os padrâmetros não utilizados na função lambda
        }

        // busca com searchView (apenas início dos termos procuradosf)
        searchView_.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // forma 1 - não detecta texto dentro
                // adapter.filter.filter(p0)

                // forma 2
                if (p0 != null) {
                    fazerBuscaObj(p0)
                    adapter.notifyDataSetChanged() // Atualiza a ListView
                }
                return false;
            }

        })
    }

    fun fazerBuscaObj(newText: String) {
        //limpando array que monta a lista ao buscar algum termo na searchView
        itemList.clear()
        //digitou algo e apagou = trazer todos. SITES_copia contém todos
        if (newText.isEmpty()) {
            itemList.addAll(itemListCopia)
        } else {
            //algum texto digitado na busca
            //converte para letra minúscula para não haver distinção
            var newTextMin = newText.lowercase()
            //percorre o array com os dados originais e busca
            for (item in itemListCopia) {
                //caso, nos dados originais, exista o termo procurado, popule o array vazio com o item
                if (item.lowercase().contains(newTextMin)) {
                    itemList.add(item)
                }
            }
        }
    }

}