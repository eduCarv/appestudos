package br.github.educarv.appestudos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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


    private void carregarCertificacoes() {

        certificacoes = new ArrayList<>();

        String[] ids =
                getResources().getStringArray(
                        R.array.certificacao_ids
                );

        String[] nomes =
                getResources().getStringArray(
                        R.array.certificacao_nomes
                );

        String[] instituicoes =
                getResources().getStringArray(
                        R.array.certificacao_instituicoes
                );

        String[] areas =
                getResources().getStringArray(
                        R.array.certificacao_areas
                );

        String[] situacoes =
                getResources().getStringArray(
                        R.array.certificacao_situacoes
                );

        for (int i = 0; i < nomes.length; i++) {

            Certificacao certificacao =
                    new Certificacao(
                            Integer.parseInt(ids[i]),
                            nomes[i],
                            instituicoes[i],
                            areas[i],
                            situacoes[i]
                    );

            certificacoes.add(certificacao);
        }
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