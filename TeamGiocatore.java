package it.unirc.nba.model;

public class TeamGiocatore {
    private int idTeam;
    private int idGiocatore;
    private String ruoloAssegnato; // Nuovo attributo aggiunto

    public TeamGiocatore() {}

    public TeamGiocatore(int idTeam, int idGiocatore, String ruoloAssegnato) {
        this.idTeam = idTeam;
        this.idGiocatore = idGiocatore;
        this.ruoloAssegnato = ruoloAssegnato; // Inizializzazione del nuovo attributo
    }

    public int getIdTeam() { return idTeam; }
    public void setIdTeam(int idTeam) { this.idTeam = idTeam; }

    public int getIdGiocatore() { return idGiocatore; }
    public void setIdGiocatore(int idGiocatore) { this.idGiocatore = idGiocatore; }

    public String getRuoloAssegnato() { return ruoloAssegnato; } // Getter per ruoloAssegnato
    public void setRuoloAssegnato(String ruoloAssegnato) { this.ruoloAssegnato = ruoloAssegnato; } // Setter per ruoloAssegnato

    @Override
    public String toString() {
        return "TeamGiocatore [idTeam=" + idTeam + ", idGiocatore=" + idGiocatore + ", ruoloAssegnato=" + ruoloAssegnato + "]";
    }
}