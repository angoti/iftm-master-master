package queimando.iftm.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import queimando.iftm.model.Produto;
import queimando.iftm.repository.ProdutoRepository;

@Controller
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @GetMapping("cadastrarpro")
    public String formCadastrPromo(Model model) {
        model.addAttribute("promocao", new Produto());
        return "form_cadastro";
    }

    @PostMapping("cadastrarpro")
    public String gravaNovaPromo(Produto produto, @RequestParam MultipartFile arquivo) throws IOException, URISyntaxException {
        Path root = Paths.get("src/main/resources/static/image-upload");
        String nomeArquivo = arquivo.getOriginalFilename();
        Files.copy(arquivo.getInputStream(), root.resolve(nomeArquivo));
        produto.setFoto(nomeArquivo);
        repository.save(produto);
        return "redirect:/home";
    }

}