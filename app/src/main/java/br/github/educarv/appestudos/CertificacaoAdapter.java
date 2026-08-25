package br.github.educarv.appestudos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CertificacaoAdapter extends ArrayAdapter<Certificacao> {

    private Context context;
    private ArrayList<Certificacao> certificacoes;

    public CertificacaoAdapter(
            Context context,
            ArrayList<Certificacao> certificacoes) {

        super(context, 0, certificacoes);

        this.context = context;
        this.certificacoes = certificacoes;
    }

    @NonNull
    @Override
    public View getView(
            int position,
            View convertView,
            @NonNull ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(context)
                    .inflate(
                            R.layout.item_certificacao,
                            parent,
                            false
                    );
        }

        TextView textViewNome =
                convertView.findViewById(R.id.textViewNome);

        TextView textViewInstituicao =
                convertView.findViewById(R.id.textViewInstituicao);

        TextView textViewArea =
                convertView.findViewById(R.id.textViewArea);

        TextView textViewSituacao =
                convertView.findViewById(R.id.textViewSituacao);

        TextView textViewProvaAgendada =
                convertView.findViewById(R.id.textViewProvaAgendada);

        Certificacao certificacao =
                certificacoes.get(position);

        textViewNome.setText(certificacao.getNome());

        textViewInstituicao.setText(
                "Instituição: " + certificacao.getInstituicao()
        );

        textViewArea.setText(
                "Área: " + certificacao.getArea()
        );

        textViewSituacao.setText(
                "Situação: " + certificacao.getSituacao()
        );

        String prova =
                certificacao.isProvaAgendada()
                        ? "Sim"
                        : "Não";

        textViewProvaAgendada.setText(
                "Prova agendada: " + prova
        );


        return convertView;
    }
}