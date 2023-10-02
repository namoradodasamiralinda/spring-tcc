package br.itb.projeto.padaria3_fk.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.itb.projeto.padaria3_fk.model.entity.Plano;

@Repository
public interface ProdutoRepository extends JpaRepository<Plano, Long> {

	// Query Method: PERMITE FAZER A CONSULTA DE ACORDO COM AS DEFINIÇÕES DO SPRING DATA JPA
	// ref: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
	// na seção 5.1.3
	List<Plano> findByStatusProduto(String statusProduto);

	// JPQL: A CONSULTA É FEITA NO OBJETO REFERENTE À TABELA
	@Query("SELECT p FROM Produto p WHERE p.statusProduto = 'ATIVO'")
	List<Plano> listarProdutosAtivos();

	@Query("SELECT p FROM Produto p WHERE p.nome like %?1%")
	List<Plano> listarProdutosFiltro(@Param("nome") String nome);

	// O MESMO QUE O DE CIMA MAS UTILIZANDO Query Method
	List<Plano> findByNomeContaining(String nome);

	// nativeQuery: PERMITE FAZER O COMANDO TAL QUAL NO SQL
	@Query(value = "SELECT TOP 3 * FROM Produto p WHERE p.categoria = 'Algo' ORDER BY p.id DESC", nativeQuery = true)
	List<Plano> listar3ProdutosPelaCategoria();

	// nativeQuery: PERMITE FAZER O COMANDO TAL QUAL NO SQL
	// COM PASSAGEM DE PARÂMETRO COM CARACTERE "?"
	@Query(value = "SELECT TOP (?1) * FROM Produto p WHERE p.categoria = 'Algo' ORDER BY p.id DESC", nativeQuery = true)
	List<Plano> listarMaisProdutosPelaCategoria(int qtdeProdutos);

	// nativeQuery: PERMITE FAZER O COMANDO TAL QUAL NO SQL
	// COM PASSAGEM DE PARÂMETRO NOMEADO
	// passar o parâmetro depois dos ":" e declarar com a anotação @Param na lista
	// de argumentos do método
	@Query(value = "SELECT TOP (:qtdeProdutos) * FROM Produto p WHERE p.categoria = 'Algo' ORDER BY p.id DESC", nativeQuery = true)
	List<Plano> listarMaisProdutosPelaCategoria2(@Param("qtdeProdutos") int qtdeProdutos);
	
	// OU
	@Query(value = "SELECT * FROM Produto p WHERE p.categoria = :categoria ORDER BY p.id DESC", nativeQuery = true)
	List<Plano> listarProdutosPelaCategoria(@Param("categoria") String categoria);

}
