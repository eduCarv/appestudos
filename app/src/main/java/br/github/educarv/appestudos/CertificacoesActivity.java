package br.github.educarv.appestudos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CertificacoesActivity extends AppCompatActivity {

    private ListView listViewCertificacoes;

    private ArrayList<Certificacao> certificacoes;

   // private Button buttonAdicionar;
   // private Button buttonSobre;

    private static final int REQUEST_CADASTRO = 1;
    private CertificacaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_certificacoes);

        listViewCertificacoes =
                findViewById(R.id.listViewCertificacoes);

        //carregarCertificacoes();
        certificacoes = new ArrayList<>();

        adapter =
                new CertificacaoAdapter(
                        this,
                        certificacoes
                );

        listViewCertificacoes.setAdapter(adapter);

        listViewCertificacoes.setOnItemClickListener(
                (parent, view, position, id) -> {

                    Certificacao certificacao =
                            certificacoes.get(position);

                    Toast.makeText(
                            this,
                            "Certificação selecionada: "
                                    + certificacao.getNome(),
                            Toast.LENGTH_SHORT
                    ).show();
                }
        );

        /**
        buttonAdicionar =
                findViewById(R.id.buttonAdicionar);

        buttonAdicionar.setOnClickListener(view -> {

            Intent intent =
                    new Intent(
                            CertificacoesActivity.this,
                            MainActivity.class
                    );

            startActivityForResult(
                    intent,
                    REQUEST_CADASTRO
            );
        });

        buttonSobre =
                findViewById(R.id.buttonSobre);

        buttonSobre.setOnClickListener(view -> {
            //Cria uma açao de Sair da CertificacoesActiviti e abrir a SobreActiviti
            Intent intent =
                    new Intent(
                            CertificacoesActivity.this,
                            SobreActivity.class
                    );

            //executa a açao
            startActivity(intent);
        });
         */
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_certificacoes,menu);
        return true;
    }

    //Cliques do adcionar e do Sohre
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuItemAdicionar) {
            abrirNovoCadastro();
            return true;
        }

        if (id == R.id.menuItemSobre) {
            abrirSobre();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void abrirSobre() {
        Intent intent =
                new Intent(
                        CertificacoesActivity.this,
                        SobreActivity.class
                );

        startActivity(intent);
    }

    private void abrirNovoCadastro() {

        Intent intent =
                new Intent(
                        CertificacoesActivity.this,
                        MainActivity.class
                );

        startActivityForResult(
                intent,
                REQUEST_CADASTRO
        );
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (requestCode == REQUEST_CADASTRO //resultado veio da tela de cadastro que eu abri?
                && resultCode == RESULT_OK //cadastro terminou com sucesso?
                && data != null) { //Existe inten?

            String nome =
                    data.getStringExtra("nome");

            String instituicao =
                    data.getStringExtra("instituicao");

            String area =
                    data.getStringExtra("area");

            String situacao =
                    data.getStringExtra("situacao");

            int novoId =
                    certificacoes.size() + 1;

            Certificacao novaCertificacao =
                    new Certificacao(
                            novoId,
                            nome,
                            instituicao,
                            area,
                            situacao
                    );

            certificacoes.add(novaCertificacao);

            adapter.notifyDataSetChanged(); //Adapter redesenha pois os dados da lista mudaram

            Toast.makeText(
                    this,
                    "Certificação adicionada: " + nome,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

}