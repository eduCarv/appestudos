package br.github.educarv.appestudos;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
    private Button buttonLimpar;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configurarBarrasDoSistema();
        inicializarComponentes();
        configurarSpinner();
        configurarBotoes();
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
        buttonLimpar = findViewById(R.id.buttonLimpar);
        buttonSalvar = findViewById(R.id.buttonSalvar);
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

    private void configurarBotoes() {
        buttonLimpar.setOnClickListener(view -> limparFormulario());
        buttonSalvar.setOnClickListener(view -> salvarCertificacao());
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

        String provaAgendada;

        if (checkBoxProvaAgendada.isChecked()) {
            provaAgendada = "Sim";
        } else {
            provaAgendada = "Não";
        }

        String mensagem =
                "Certificação válida: "
                        + nome
                        + " | Área: "
                        + areaSelecionada
                        + " | Situação: "
                        + situacaoSelecionada
                        + " | Prova: "
                        + provaAgendada;

        Toast.makeText(
                this,
                mensagem,
                Toast.LENGTH_LONG
        ).show();
    }
}
