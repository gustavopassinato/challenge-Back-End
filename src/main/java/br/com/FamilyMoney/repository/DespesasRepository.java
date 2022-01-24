package br.com.FamilyMoney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.FamilyMoney.model.DespesasDao;

@Repository
public interface DespesasRepository extends JpaRepository<DespesasDao, Long>{

	List<DespesasDao> findByDescricao(String descricao);

}
