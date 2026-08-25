    package br.github.educarv.appestudos;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.ContextMenu;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ListView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import java.util.ArrayList;

    public class CertificacoesActivity extends AppCompatActivity {

        private ListView listViewCertificacoes;

        private ArrayList<Certificacao> certificacoes;
        private int posicaoSelecionada = -1;

        private static final int REQUEST_CADASTRO = 1;
        private CertificacaoAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_certificacoes);

            listViewCertificacoes =
                    findViewById(R.id.listViewCertificacoes);
            registerForContextMenu(listViewCertificacoes);

            certificacoes = new ArrayList<>();

            adapter =
                    new CertificacaoAdapter(
                            this,
                            certificacoes
                    );

            listViewCertificacoes.setAdapter(adapter);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu,View view,ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu,view,menuInfo);
            getMenuInflater().inflate(R.menu.menu_certificacoes_item_selecionado,menu);
            menu.setHeaderTitle("Opções da certificação");
        }

        @Override
        public boolean onContextItemSelected(MenuItem item) {

            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo)
                            item.getMenuInfo();

            posicaoSelecionada = info.position;

            int id = item.getItemId();

            if (id == R.id.menuItemEditar) {
                editarCertificacao();
                return true;
            }

            if (id == R.id.menuItemExcluir) {
                excluirCertificacao();
                return true;
            }

            return super.onContextItemSelected(item);
        }

        private void editarCertificacao() {

            if (posicaoSelecionada >= 0
                    && posicaoSelecionada < certificacoes.size()) {

                Certificacao certificacao =
                        certificacoes.get(posicaoSelecionada);

                Intent intent =
                        new Intent(
                                CertificacoesActivity.this,
                                MainActivity.class
                        );

                intent.putExtra(
                        "modoEdicao",
                        true
                );

                intent.putExtra(
                        "id",
                        certificacao.getId()
                );

                intent.putExtra(
                        "nome",
                        certificacao.getNome()
                );

                intent.putExtra(
                        "instituicao",
                        certificacao.getInstituicao()
                );

                intent.putExtra(
                        "area",
                        certificacao.getArea()
                );

                intent.putExtra(
                        "situacao",
                        certificacao.getSituacao()
                );

                intent.putExtra(
                        "provaAgendada",
                        certificacao.isProvaAgendada()
                );

                intent.putExtra(
                        "posicao",
                        posicaoSelecionada
                );

                startActivityForResult(
                        intent,
                        REQUEST_CADASTRO
                );
            }
        }

        private void excluirCertificacao() {

            if (posicaoSelecionada >= 0
                    && posicaoSelecionada < certificacoes.size()) {

                Certificacao certificacao =
                        certificacoes.get(posicaoSelecionada);

                String nome =
                        certificacao.getNome();

                certificacoes.remove(posicaoSelecionada);

                adapter.notifyDataSetChanged();

                Toast.makeText(
                        this,
                        "Certificação excluída: " + nome,
                        Toast.LENGTH_SHORT
                ).show();
            }
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

                boolean provaAgendada =
                        data.getBooleanExtra("provaAgendada",false);

                boolean modoEdicao =
                        data.getBooleanExtra(
                                "modoEdicao",
                                false
                        );

                if (modoEdicao) {

                    int posicao =
                            data.getIntExtra(
                                    "posicao",
                                    -1
                            );

                    if (posicao >= 0
                            && posicao < certificacoes.size()) {

                        Certificacao certificacao =
                                certificacoes.get(posicao);

                        certificacao.setNome(nome);
                        certificacao.setInstituicao(instituicao);
                        certificacao.setArea(area);
                        certificacao.setSituacao(situacao);
                        certificacao.setProvaAgendada(provaAgendada);

                        adapter.notifyDataSetChanged();

                        Toast.makeText(
                                this,
                                "Certificação alterada com sucesso.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                } else {

                    int novoId =
                            certificacoes.size() + 1;

                    Certificacao novaCertificacao =
                            new Certificacao(
                                    novoId,
                                    nome,
                                    instituicao,
                                    area,
                                    situacao,
                                    provaAgendada
                            );

                    certificacoes.add(
                            novaCertificacao
                    );

                    adapter.notifyDataSetChanged();

                    Toast.makeText(
                            this,
                            "Certificação adicionada: " + nome,
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        }
    }