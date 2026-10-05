package unoeste.fipp.playmysongs.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.playmysongs.entities.Erro;
import unoeste.fipp.playmysongs.entities.Musica;
import unoeste.fipp.playmysongs.services.MusicaService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "apis")
public class MusicasRestController {
    @Autowired
    private MusicaService musicaService;

    // music-upload (POST): recebe os dados + o arquivo e retorna a musica salva ou um Erro
    @PostMapping(value = "music-upload")
    public ResponseEntity<Object> upload(@RequestParam("arquivo") MultipartFile file,
                                         @RequestParam("nome") String nome,
                                         @RequestParam("estilo") String estilo,
                                         @RequestParam("artista") String artista) {
        Musica musica = musicaService.salvar(file, nome, estilo, artista);
        if (musica == null)
            return ResponseEntity.badRequest().body(new Erro("Erro ao enviar a musica. Confira o arquivo (mp3/ogg) e os dados."));
        return ResponseEntity.ok(musica);
    }

    // find-musics (GET): recebe a chave de busca e retorna a lista de musicas ou um Erro
    @GetMapping(value = "find-musics")
    public ResponseEntity<Object> buscar(@RequestParam("chave") String chave) {
        List<Musica> lista = musicaService.buscar(chave);
        if (lista.isEmpty())
            return ResponseEntity.badRequest().body(new Erro("Nenhuma musica encontrada!"));
        return ResponseEntity.ok(lista);
    }

    // get-music-styles (GET): retorna a lista de estilos musicais
    @GetMapping(value = "get-music-styles")
    public ResponseEntity<Object> estilos() {
        return ResponseEntity.ok(musicaService.getEstilos());
    }
}
