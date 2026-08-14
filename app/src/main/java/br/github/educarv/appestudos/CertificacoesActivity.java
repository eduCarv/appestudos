package br.github.educarv.appestudos;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CertificacoesActivity extends AppCompatActivity {

    private ListView listViewCertificacoes;

    private ArrayList<Certificacao> certificacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_certificacoes);

        listViewCertificacoes =
                findViewById(R.id.listViewCertificacoes);

        carregarCertificacoes();

        CertificacaoAdapter adapter =
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
}