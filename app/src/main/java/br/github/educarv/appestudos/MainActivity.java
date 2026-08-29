    package br.github.educarv.appestudos;

    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    import android.view.Menu;
    import android.view.MenuItem;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.CheckBox;
    import android.widget.EditText;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import android.widget.Spinner;
    import android.widget.Toast;

    public class MainActivity extends AppCompatActivity {

        private EditText editTextNome;
        private EditText editTextInstituicao;
        private Spinner spinnerArea;
        private RadioGroup radioGroupSituacao;
        private CheckBox checkBoxProvaAgendada;
        private boolean modoEdicao = false;
        private int idEdicao = -1;
        private int posicaoEdicao = -1;
        private RadioButton radioButtonPlanejada;
        private RadioButton radioButtonAndamento;
        private RadioButton radioButtonConcluida;
        private static final String PREFS_NAME = "configuracoes";
        private static final String PREF_SUGERIR_AREA = "sugerir_area";
        private static final String PREF_ULTIMA_AREA = "ultima_area";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            configurarBarrasDoSistema();
            inicializarComponentes();
            configurarSpinner();
            verificarModoEdicao();

            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(
                    R.menu.menu_cadastro,
                    menu
            );

            SharedPreferences preferences =
                    getSharedPreferences(
                            PREFS_NAME,
                            Context.MODE_PRIVATE
                    );

            boolean sugerirArea =
                    preferences.getBoolean(
                            PREF_SUGERIR_AREA,
                            false
                    );

            MenuItem itemSugerirArea =
                    menu.findItem(
                            R.id.menuSugerirArea
                    );

            itemSugerirArea.setChecked(
                    sugerirArea
            );

            if (sugerirArea && !modoEdicao) {

                sugerirArea();
            }

            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();

            if (id == R.id.menuSugerirArea) {

                boolean marcado =
                        !item.isChecked();

                item.setChecked(
                        marcado
                );

                SharedPreferences preferences =
                        getSharedPreferences(
                                PREFS_NAME,
                                Context.MODE_PRIVATE
                        );

                SharedPreferences.Editor editor =
                        preferences.edit();

                editor.putBoolean(
                        PREF_SUGERIR_AREA,
                        marcado
                );

                editor.apply();

                if (marcado) {

                    sugerirArea();

                } else {

                    spinnerArea.setSelection(0);
                }

                return true;
            }

            if (id == android.R.id.home) {

                finish();

                return true;
            }

            if (id == R.id.menuSalvar) {

                salvarCertificacao();

                return true;
            }

            if (id == R.id.menuLimpar) {

                limparFormulario();

                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        private void verificarModoEdicao() {

            Intent intent = getIntent();

            modoEdicao =
                    intent.getBooleanExtra(
                            "modoEdicao",
                            false
                    );

            if (modoEdicao) {

                idEdicao =
                        intent.getIntExtra(
                                "id",
                                -1
                        );

                posicaoEdicao =
                        intent.getIntExtra(
                                "posicao",
                                -1
                        );

                String nome =
                        intent.getStringExtra("nome");

                String instituicao =
                        intent.getStringExtra("instituicao");

                String area =
                        intent.getStringExtra("area");

                String situacao =
                        intent.getStringExtra("situacao");

                boolean provaAgendada =
                        intent.getBooleanExtra("provaAgendada",false);

                editTextNome.setText(nome);
                editTextInstituicao.setText(instituicao);

                selecionarArea(area);

                selecionarSituacao(situacao);

                checkBoxProvaAgendada.setChecked(provaAgendada);

                setTitle("Editar Certificação");
            }
        }

        private void selecionarArea(String area) {

            for (int i = 0; i < spinnerArea.getCount(); i++) {

                String item =
                        spinnerArea.getItemAtPosition(i)
                                .toString();

                if (item.equals(area)) {

                    spinnerArea.setSelection(i);

                    break;
                }
            }
        }

        private void selecionarSituacao(String situacao) {

            if (situacao.equals("Planejada")) {

                radioButtonPlanejada.setChecked(true);

            } else if (situacao.equals("Em andamento")) {

                radioButtonAndamento.setChecked(true);

            } else if (situacao.equals("Concluída")) {

                radioButtonConcluida.setChecked(true);
            }
        }

        private void configurarBarrasDoSistema() {
            ViewCompat.setOnApplyWindowInsetsListener(
                    findViewById(R.id.main),
                    (view, windowInsets) -> {

                        Insets barrasSistema = windowInsets.getInsets(
                                WindowInsetsCompat.Type.systemBars()
                        );

                        view.setPadding(
                                barrasSistema.left,
                                barrasSistema.top,
                                barrasSistema.right,
                                barrasSistema.bottom
                        );

                        return windowInsets;
                    }
            );
        }

        private void inicializarComponentes() {
            editTextNome = findViewById(R.id.editTextNome);
            editTextInstituicao = findViewById(R.id.editTextInstituicao);
            spinnerArea = findViewById(R.id.spinnerArea);
            radioGroupSituacao = findViewById(R.id.radioGroupSituacao);
            checkBoxProvaAgendada = findViewById(R.id.checkBoxProvaAgendada);
            radioButtonPlanejada = findViewById(R.id.radioButtonPlanejada);
            radioButtonAndamento = findViewById(R.id.radioButtonAndamento);
            radioButtonConcluida = findViewById(R.id.radioButtonConcluida);
        }

        private void configurarSpinner() {
            String[] areasConhecimento = {
                    "Selecione uma área",
                    "Desenvolvimento de Software",
                    "Banco de Dados",
                    "Computação em Nuvem",
                    "Segurança da Informação",
                    "Gestão de Projetos",
                    "Redes de Computadores",
                    "System Design"
            };

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    areasConhecimento
            );

            adapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item
            );

            spinnerArea.setAdapter(adapter);
        }

        private void limparFormulario() {
            editTextNome.setText("");
            editTextInstituicao.setText("");

            spinnerArea.setSelection(0);

            radioGroupSituacao.clearCheck();

            checkBoxProvaAgendada.setChecked(false);

            editTextNome.requestFocus(); //Foca o campo do nome, redireciona o cursor

            Toast.makeText(
                    this,
                    "Formulário limpo com sucesso.",
                    Toast.LENGTH_SHORT
            ).show();
        }

        private void salvarCertificacao() {
            String nome = editTextNome.getText().toString().trim();
            String instituicao = editTextInstituicao.getText().toString().trim();

            if (nome.isEmpty()) {
                Toast.makeText(
                        this,
                        "Informe o nome da certificação.",
                        Toast.LENGTH_SHORT
                ).show();

                editTextNome.requestFocus();
                return;
            }

            if (instituicao.isEmpty()) {
                Toast.makeText(
                        this,
                        "Informe a instituição responsável.",
                        Toast.LENGTH_SHORT
                ).show();

                editTextInstituicao.requestFocus();
                return;
            }

            if (spinnerArea.getSelectedItemPosition() == 0) {
                Toast.makeText(
                        this,
                        "Selecione uma área de conhecimento.",
                        Toast.LENGTH_SHORT
                ).show();

                spinnerArea.requestFocus();
                return;
            }

            int radioButtonSelecionadoId =
                    radioGroupSituacao.getCheckedRadioButtonId();

            if (radioButtonSelecionadoId == -1) {
                Toast.makeText(
                        this,
                        "Selecione a situação da certificação.",
                        Toast.LENGTH_SHORT
                ).show();

                radioGroupSituacao.requestFocus();
                return;
            }

            RadioButton radioButtonSelecionado =
                    findViewById(radioButtonSelecionadoId);

            String areaSelecionada =
                    spinnerArea.getSelectedItem().toString();

            String situacaoSelecionada =
                    radioButtonSelecionado.getText().toString();

            boolean provaAgendada =
                    checkBoxProvaAgendada.isChecked();

            Intent intentResultado = new Intent();

            intentResultado.putExtra("nome", nome);
            intentResultado.putExtra("instituicao", instituicao);
            intentResultado.putExtra("area", areaSelecionada);
            intentResultado.putExtra("situacao", situacaoSelecionada);
            intentResultado.putExtra("provaAgendada", provaAgendada);
            intentResultado.putExtra("modoEdicao",modoEdicao);
            intentResultado.putExtra("id",idEdicao);
            intentResultado.putExtra("posicao",posicaoEdicao);

            SharedPreferences preferences =
                    getSharedPreferences(
                            PREFS_NAME,
                            Context.MODE_PRIVATE
                    );

            SharedPreferences.Editor editor =
                    preferences.edit();

            editor.putString(
                    PREF_ULTIMA_AREA,
                    areaSelecionada
            );

            editor.apply();

            setResult(RESULT_OK, intentResultado);

            finish();
        }

        private void sugerirArea() {

            SharedPreferences preferences =
                    getSharedPreferences(
                            PREFS_NAME,
                            Context.MODE_PRIVATE
                    );

            String ultimaArea =
                    preferences.getString(
                            PREF_ULTIMA_AREA,
                            null
                    );

            if (ultimaArea == null) {

                selecionarArea(
                        "Desenvolvimento de Software"
                );

            } else {

                selecionarArea(
                        ultimaArea
                );
            }
        }
    }
