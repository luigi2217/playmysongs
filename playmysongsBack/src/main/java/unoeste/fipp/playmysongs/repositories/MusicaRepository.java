package unoeste.fipp.playmysongs.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import unoeste.fipp.playmysongs.entities.Musica;

import java.util.List;

@Repository
public interface MusicaRepository extends MongoRepository<Musica, String> {

    // busca a palavra-chave em nome OU estilo OU artista (contendo parte, ignorando maiusculas)
    List<Musica> findByNomeContainingIgnoreCaseOrEstiloContainingIgnoreCaseOrArtistaContainingIgnoreCase(
            String nome, String estilo, String artista);
}
