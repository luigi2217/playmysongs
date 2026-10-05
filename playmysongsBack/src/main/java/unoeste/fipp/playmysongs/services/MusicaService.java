package unoeste.fipp.playmysongs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import unoeste.fipp.playmysongs.entities.Estilo;
import unoeste.fipp.playmysongs.entities.Musica;
import unoeste.fipp.playmysongs.repositories.MusicaRepository;

import java.io.File;
import java.text.Normalizer;
import java.util.List;

@Service
public class MusicaService {
    @Autowired
    private MusicaRepository musicaRepository;

    private final String UPLOAD_FOLDER = "src/main/resources/static/uploads/";

    public List<Musica> buscar(String chave) {
        return musicaRepository
                .findByNomeContainingIgnoreCaseOrEstiloContainingIgnoreCaseOrArtistaContainingIgnoreCase(chave, chave, chave);
    }

    public List<Estilo> getEstilos() {
        return List.of(
                new Estilo("Rock", "Genero musical que se originou na decada de 1950. Caracterizado pelo uso de guitarra eletrica, baixo e bateria."),
                new Estilo("Pop", "Genero musical que se caracteriza por suas melodias cativantes e producoes acessiveis. Popular desde os anos 1950."),
                new Estilo("Jazz", "Estilo musical que surgiu no inicio do seculo XX, caracterizado pela improvisacao e pelo uso de instrumentos de sopro."),
                new Estilo("Hip Hop", "Genero que comecou na decada de 1970, conhecido por seu ritmo sincopado e letras rap. Envolve elementos de DJing e grafite."),
                new Estilo("Eletronica", "Genero que envolve a criacao de musica usando sintetizadores e tecnologia digital. Inclui subgeneros como techno e house."),
                new Estilo("Classica", "Genero que abrange uma longa tradicao musical europeia, com obras compostas por grandes compositores como Bach, Mozart e Beethoven."),
                new Estilo("Reggae", "Genero musical originario da Jamaica, conhecido por seu ritmo distinto e letras que frequentemente abordam questoes sociais e politicas."),
                new Estilo("Country", "Estilo musical que se desenvolveu nos Estados Unidos, baseado na musica folk e blues, com letras que frequentemente falam sobre a vida rural."),
                new Estilo("Blues", "Genero musical que se originou no sul dos Estados Unidos no final do seculo 19, baseado na expressao emocional e na forma de 12 compassos."),
                new Estilo("Metal", "Estilo de rock caracterizado por guitarras eletricas distorcidas, bateria pesada e vocais intensos. Emergiu no fim dos anos 1960 e inicio dos 1970."),
                new Estilo("Funk", "Genero que combina elementos de soul, jazz e rhythm and blues, caracterizado por suas linhas de baixo groovy e batidas ritmicas."),
                new Estilo("Samba", "Estilo de musica e danca brasileira que e sinonimo de cultura carioca, com raizes africanas."),
                new Estilo("Tango", "Genero musical e danca que se originou na Argentina, caracterizado por suas melodias romanticas e ritmos marcantes."),
                new Estilo("Punk", "Estilo de musica que surgiu na decada de 1970, famoso pelo seu som agressivo, letras politizadas e estetica DIY."),
                new Estilo("Alternativa", "Genero que surgiu na decada de 1980 como uma alternativa a musica popular convencional. Abrange uma variedade de estilos e influencias."),
                new Estilo("Sertanejo", "Estilo musical originario do Brasil, com cancoes sobre amor, vida rural e relacionamentos, muito popular no Centro-Oeste e Sudeste.")
        );
    }

    public Musica salvar(MultipartFile file, String nome, String estilo, String artista) {
        try {
            String original = file.getOriginalFilename();
            if (original == null || !original.contains("."))
                return null;
            String ext = original.substring(original.lastIndexOf(".") + 1).toLowerCase();
            if (!ext.equals("mp3") && !ext.equals("ogg"))
                return null;

            String arquivo = limpar(nome) + "_" + limpar(estilo) + "_" + limpar(artista) + "." + ext;

            File uploadFolder = new File(UPLOAD_FOLDER);
            if (!uploadFolder.exists())
                uploadFolder.mkdirs();
            file.transferTo(new File(uploadFolder.getAbsolutePath() + File.separator + arquivo));

            Musica musica = new Musica(null, nome, estilo, artista, arquivo);
            return musicaRepository.save(musica);
        } catch (Exception e) {
            return null;
        }
    }

    private String limpar(String texto) {
        String semAcento = Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return semAcento.toLowerCase().replaceAll("[^a-z0-9]", "");
    }
}
