package queimando.iftm.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import queimando.iftm.firebase.FirebaseCloudStorage;
import queimando.iftm.model.Produto;
import queimando.iftm.repository.ProdutoRepository;

@Controller
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    FirebaseCloudStorage gStorage;

    @GetMapping("cadastrarpro")
    public String formCadastrPromo(Model model) {
        model.addAttribute("promocao", new Produto());
        return "form_cadastro";
    }

    @PostMapping("cadastrarpro")
    public String gravaNovaPromo(Produto produto, @RequestParam MultipartFile arquivo) throws Exception {
        System.out.println("----------------------------------------");
        System.out.println(arquivo.getOriginalFilename());
        System.out.println(arquivo.getInputStream().toString());
        String nomeArquivo = arquivo.getOriginalFilename().replace(".", new StringBuffer(Math.abs(Instant.now().hashCode()) + "."));
        System.out.println(nomeArquivo);
        gStorage.upload(arquivo.getInputStream(), nomeArquivo);
        produto.setFoto(nomeArquivo);
        repository.save(produto);
        return "redirect:/home";
    }

    

}