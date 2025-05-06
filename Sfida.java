package it.unirc.nba.model;

public class Sfida {
    private int id;
    private String titolo;
    private String descrizione;

    public Sfida() {}

    public Sfida(int id, String titolo, String descrizione) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    @Override
    public String toString() {
        return "Sfida [id=" + id + ", titolo=" + titolo + ", descrizione=" + descrizione + "]";
    }
}
