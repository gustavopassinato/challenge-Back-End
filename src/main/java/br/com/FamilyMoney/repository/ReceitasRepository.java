package br.com.FamilyMoney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.FamilyMoney.model.ReceitasDao;

public interface ReceitasRepository extends JpaRepository<ReceitasDao, Long>{

	List<ReceitasDao> findByDescricao(String descricao);

}
