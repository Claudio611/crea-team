package it.unirc.nba.model;

public class Giocatore {
    private int id;
    private String nome;
    private String ruolo;
    private int altezza;
    private int annoEsordio;
    private String nazionalita;
    private String stile;
    private int valutazioneGenerale;
    private int punteggio; // Nuovo attributo

    public Giocatore() {}

    public Giocatore(int id, String nome, String ruolo, int altezza, int annoEsordio, String nazionalita, String stile, int valutazioneGenerale, int punteggio) {
        this.id = id;
        this.nome = nome;
        this.ruolo = ruolo;
        this.altezza = altezza;
        this.annoEsordio = annoEsordio;
        this.nazionalita = nazionalita;
        this.stile = stile;
        this.valutazioneGenerale = valutazioneGenerale;
        this.punteggio = punteggio;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRuolo() { return ruolo; }
    public void setRuolo(String ruolo) { this.ruolo = ruolo; }

    public int getAltezza() { return altezza; }
    public void setAltezza(int altezza) { this.altezza = altezza; }

    public int getAnnoEsordio() { return annoEsordio; }
    public void setAnnoEsordio(int annoEsordio) { this.annoEsordio = annoEsordio; }

    public String getNazionalita() { return nazionalita; }
    public void setNazionalita(String nazionalita) { this.nazionalita = nazionalita; }

    public String getStile() { return stile; }
    public void setStile(String stile) { this.stile = stile; }

    public int getValutazioneGenerale() { return valutazioneGenerale; }
    public void setValutazioneGenerale(int valutazioneGenerale) { this.valutazioneGenerale = valutazioneGenerale; }

    public int getPunteggio() { return punteggio; } // Getter per il punteggio
    public void setPunteggio(int punteggio) { this.punteggio = punteggio; } // Setter per il punteggio

    @Override
    public String toString() {
        return "Giocatore [id=" + id + ", nome=" + nome + ", ruolo=" + ruolo + ", altezza=" + altezza + ", annoEsordio=" + annoEsordio + ", nazionalita=" + nazionalita + ", stile=" + stile + ", valutazioneGenerale=" + valutazioneGenerale + ", punteggio=" + punteggio + "]";
    }
}