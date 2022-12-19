package queimando.iftm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.PostMapping;

import queimando.iftm.model.Cadastrar;
import queimando.iftm.repository.CadastrarRepository;



@Controller
public class CadastrarController {

    @Autowired
    CadastrarRepository repository;

    @GetMapping("cad_promo")
    public String formCadastrPromo(Model model) {
        model.addAttribute("promocao", new Cadastrar());
        return "form_cadastro";
}

@PostMapping("cad_promo")
    public String gravaNovaPromo(Cadastrar cadastrar) {
      
        return "redirect:/cadastrarpro";
}

  

}