package livraria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import livraria.Model.Livro;

import java.util.List;

public interface LivrariaRepository extends JpaRepository<Livro, Long> {
        List<Livro> findByTituloContainingIgnoreCase(String titulo);


}
