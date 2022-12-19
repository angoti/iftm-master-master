package queimando.iftm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cadastrar {
    private int id;

    private String nomep;

    private float preco;

    private String foto;

    private String endereco;
}