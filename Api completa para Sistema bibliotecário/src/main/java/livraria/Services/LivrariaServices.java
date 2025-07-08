package livraria.Services;

import livraria.Model.Livro;
import livraria.Repository.LivrariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivrariaServices {

    @Autowired
    LivrariaRepository livrariaRepository;

    public String save(Livro livro){
        livrariaRepository.save(livro);
        return "Livro adicionado com sucesso!";
    }

    public Livro getLivro(Long id){
        Optional<Livro> livro = livrariaRepository.findById(id);
        return livro.get();
    }
    public List<Livro> getLivrosPorTitulo(String titulo){
        return livrariaRepository.findByTituloContainingIgnoreCase(titulo);
    }
    public String deleteLivro(Long id){
        if(livrariaRepository.existsById(id)){
            livrariaRepository.deleteById(id);
            return "Livro deletado com sucesso!";
        }
        else
            return "Livro com id: " + id + " não encontrado!";
    }


}
