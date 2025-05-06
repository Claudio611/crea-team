package it.unirc.nba.model;

public class Team {
    private int id;
    private int idUtente;
    private int idSfida;
    private String nome;
    private String feedbackGenerato;
    private boolean vincoliRispettati;
    private boolean confermato;
    private int punteggio;

    // Costruttore vuoto
    public Team() {}

    // Costruttore che accetta solo l'id
    public Team(int id) {
        this.id = id;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUtente() { return idUtente; }
    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

    public int getIdSfida() { return idSfida; }
    public void setIdSfida(int idSfida) { this.idSfida = idSfida; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getFeedbackGenerato() { return feedbackGenerato; }
    public void setFeedbackGenerato(String feedbackGenerato) { this.feedbackGenerato = feedbackGenerato; }

    public boolean isVincoliRispettati() { return vincoliRispettati; }
    public void setVincoliRispettati(boolean vincoliRispettati) { this.vincoliRispettati = vincoliRispettati; }

    public boolean isConfermato() { return confermato; }
    public void setConfermato(boolean confermato) { this.confermato = confermato; }

    public int getPunteggio() { return punteggio; }
    public void setPunteggio(int punteggio) { this.punteggio = punteggio; }

    @Override
    public String toString() {
        return "Team [id=" + id + ", idUtente=" + idUtente + ", idSfida=" + idSfida + ", nome=" + nome
                + ", feedbackGenerato=" + feedbackGenerato + ", vincoliRispettati=" + vincoliRispettati
                + ", confermato=" + confermato + ", punteggio=" + punteggio + "]";
    }
}