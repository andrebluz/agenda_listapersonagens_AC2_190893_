package com.example.agenda_listapersonagens_ac2_190893.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda_listapersonagens_ac2_190893.R;
import com.example.agenda_listapersonagens_ac2_190893.dao.PersonagemDAO;
import com.example.agenda_listapersonagens_ac2_190893.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaPersonagemActivity extends AppCompatActivity {
    private static final String CHAVE = "PERSONAGENS";

    private final PersonagemDAO dao = new PersonagemDAO();
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        //List<String> personagem = new ArrayList<>(Arrays.asList("Alex", "Ken", "Ryu"));

        //define o título da tela
        setTitle("LISTA DE PERSONAGENS");

        ConfiguraFabNovoPersonagem();
        configuraLista();

        //botão para ir até a list screen
        /*FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });

        //referencia ao dao
        PersonagemDAO dao = new PersonagemDAO();

        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1, dao.todos()));*/

        //inserção manual das infos para a lista que foi substituida pelo array
        /*TextView primeiroPersonagem = findViewById(R.id.textView);
        TextView segundoPersonagem = findViewById(R.id.textView2);
        TextView terceiroPersonagem = findViewById(R.id.textView3);
        primeiroPersonagem.setText(personagem.get(0));
        segundoPersonagem.setText(personagem.get(1));
        terceiroPersonagem.setText(personagem.get(2));*/
    }

    private void ConfiguraFabNovoPersonagem(){
        FloatingActionButton BtNovoPersonagem = findViewById(R.id.fab_add);
        BtNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AbreFormulario();
            }
        });
    }

    /*private void remover(Personagem escolhido)
    {
        dao.remover(escolhido);
        adapter.remove(escolhido);
    }*/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Personagem escolhido = adapter.getItem(menuInfo.position);

        adapter.remove(escolhido);
        return super.onContextItemSelected(item);
    }

    private void AbreFormulario(){
        //formulario para adição de dados
        startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
    }

    private void configuraLista()
    {
        ListView listaPersonagens = findViewById(R.id.activity_main_lista_personagem);
        listaPersonagens(listaPersonagens);
        ConfiguraItemPorClique(listaPersonagens);
        registerForContextMenu(listaPersonagens);
    }

    private void ConfiguraItemPorClique(ListView listaPersonagens){
        listaPersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
               Personagem escolhido = (Personagem) adapterView.getItemAtPosition(posicao);

               abreFormularioEditar(escolhido);

              }
           }
        );
    }

    private void listaPersonagens(ListView listaPersonagens) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        //preenche a lista no layout com o array
        listaPersonagens.setAdapter(adapter);
    }

    private void abreFormularioEditar(Personagem escolhido){
        Intent vaiParaOfotmulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        vaiParaOfotmulario.putExtra(CHAVE, escolhido);
        startActivity(vaiParaOfotmulario);
    }
}
