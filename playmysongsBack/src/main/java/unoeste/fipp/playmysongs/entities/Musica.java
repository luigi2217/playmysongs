package unoeste.fipp.playmysongs.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "musicas")
public class Musica {
    @Id
    private ObjectId _id;
    private String nome;
    private String estilo;
    private String artista;
    private String arquivo;

    public Musica() {
    }

    public Musica(ObjectId _id, String nome, String estilo, String artista, String arquivo) {
        this._id = _id;
        this.nome = nome;
        this.estilo = estilo;
        this.artista = artista;
        this.arquivo = arquivo;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
