package com.example.agenda_listapersonagens_ac2_190893.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda_listapersonagens_ac2_190893.R;
import com.example.agenda_listapersonagens_ac2_190893.dao.PersonagemDAO;
import com.example.agenda_listapersonagens_ac2_190893.model.Personagem;
//import com.example.agenda_listapersonagens_ac2_190893.ui.activities.ConstantesActivities.CHAVE;

public class FormularioPersonagemActivity extends AppCompatActivity {
    private static final String CHAVE = "PERSONAGENS";
    //EDIT TEXT
    private EditText campoNome;
    private EditText campoNascimento;
    private EditText campoAltura;

    //STRINGS
    private String nome;
    private String nascimento;
    private String altura;

    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        inicializacaoCampos();
        configuraBotaoSalvar();
        carregaPersonagem();

        /*//referência ao dao
        PersonagemDAO dao = new PersonagemDAO();

        //define o título da tela
        setTitle("FORMULÁRIO DE PERSONAGENS");

        EditText campoNome = findViewById(R.id.editText_Text_Name);
        EditText campoAltura = findViewById(R.id.editText_Altura);
        EditText campoNascimento = findViewById(R.id.editText_Nascimento);

        //variável botaoSalvar do tipo Button
        Button botaoSalvar = findViewById(R.id.button_Salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                //armazena todas as informações do GETSETTINGS que estão no método construtor
                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);

                dao.salvar(personagemSalvo);

                startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));

                //da um toast nas infos do construtor atravbés do getNome, getAltura e getNascimento
                *//*Toast.makeText( FormularioPersonagemActivity.this,  personagemSalvo.getNome() + " - " +
                    personagemSalvo.getAltura() + " - " + personagemSalvo.getNascimento(),
                    Toast.LENGTH_SHORT).show();*//*

                *//*new Personagem(nome, nascimento, altura);*//*


                //Toast.makeText(FormularioPersonagemActivity.this, "Salvando...", Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    private void carregaPersonagem() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE)) {
            setTitle("EDITAR PERSONAGENS");
            personagem = (Personagem) dados.getSerializableExtra(CHAVE);
            preencheCampos(personagem);
        } else {
            setTitle("NOVO PERSONAGEM");
            personagem = new Personagem();
        }
    }

    private void configuraBotaoSalvar() {
        //busca botão no xml e atribui ao código pelo ID
        Button btSalvar = findViewById(R.id.button_Salvar);
        btSalvar.setOnClickListener
         (new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       finalizaForm();
                  }
             }
        );
    }

    private void preencheCampos(Personagem personagem) {

        campoNome.setText(personagem.getNome());
        campoNascimento.setText(personagem.getNascimento());
        campoAltura.setText(personagem.getAltura());
    }

    private void finalizaForm() {
        preencherPersonagem();
        if (personagem.idValido())
        {
            dao.editar(personagem);
            finish();
        }
        else
        {
            dao.salvar(personagem);
        }
            finish();
    }

    private void preencherPersonagem() {
        //envia os valores dos editstexts para as strings
        nome = campoNome.getText().toString();
        personagem.setNome(nome);
        altura = campoAltura.getText().toString();
        personagem.setAltura(altura);
        nascimento = campoNascimento.getText().toString();
        personagem.setNascimento(nascimento);

    }

    private void inicializacaoCampos() {
        //atribui os campos do xml ao edittext pelo ID
        campoNome = findViewById(R.id.editText_Text_Name);
        campoAltura = findViewById(R.id.editText_Altura);
        campoNascimento = findViewById(R.id.editText_Nascimento);

    }
}