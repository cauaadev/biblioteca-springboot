package livraria.Controller;

import livraria.Model.Livro;
import livraria.Services.LivrariaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class LivrariaController {

    @Autowired
    LivrariaServices livrariaServices;

    @GetMapping("/search/{param}")
    public ResponseEntity<?> search(@PathVariable String param) {
        try {
            try {
                Long id = Long.parseLong(param); // conversão correta
                Livro livro = this.livrariaServices.getLivro(id);
                return new ResponseEntity<Livro>(livro, HttpStatus.OK);
            } catch (NumberFormatException e) {
                List<Livro> livros = this.livrariaServices.getLivrosPorTitulo(param);
                if (livros.isEmpty()) {
                    return new ResponseEntity<>("Nenhum livro encontrado com esse título.", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>(livros, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao buscar livro.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody List<Livro> livros) {
        try {
            livros.forEach(livro -> livrariaServices.save(livro));
            return new ResponseEntity<>("Todos os livros foram salvos com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar os livros.", HttpStatus.BAD_REQUEST);
        }
    }






    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String mensagem = null;
        try {
            mensagem = this.livrariaServices.deleteLivro(id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }


    }

}
