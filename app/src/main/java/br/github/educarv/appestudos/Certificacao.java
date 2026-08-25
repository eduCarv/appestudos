package br.github.educarv.appestudos;

public class Certificacao {

    private int id;
    private String nome;
    private String instituicao;
    private String area;
    private String situacao;
    private boolean provaAgendada;

    public Certificacao(int id, String nome, String instituicao,
                        String area, String situacao, boolean provaAgendada) {

        this.id = id;
        this.nome = nome;
        this.instituicao = instituicao;
        this.area = area;
        this.situacao = situacao;
        this.provaAgendada = provaAgendada;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public String getArea() {
        return area;
    }

    public boolean isProvaAgendada() {
        return provaAgendada;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setProvaAgendada(boolean provaAgendada) {
        this.provaAgendada = provaAgendada;
    }
}
