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
                getContext().getString(
                        R.string.label_instituicao,
                        certificacao.getInstituicao()
                )
        );

        textViewArea.setText(
                getContext().getString(
                        R.string.label_area,
                        certificacao.getArea()
                )
        );

        textViewSituacao.setText(
                getContext().getString(
                        R.string.label_situacao,
                        certificacao.getSituacao()
                )
        );

        String prova =
                certificacao.isProvaAgendada()
                        ? getContext().getString(R.string.sim)
                        : getContext().getString(R.string.nao);

        textViewProvaAgendada.setText(
                getContext().getString(
                        R.string.label_prova,
                        prova
                )
        );


        return convertView;
    }
}