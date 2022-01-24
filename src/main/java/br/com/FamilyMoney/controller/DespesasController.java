package br.com.FamilyMoney.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.FamilyMoney.controller.errors.ErroDescricaoRepetida;
import br.com.FamilyMoney.controller.errors.Tipo;
import br.com.FamilyMoney.model.DespesasDao;
import br.com.FamilyMoney.model.dto.DespesasDto;
import br.com.FamilyMoney.model.form.AtualizaDespesaForm;
import br.com.FamilyMoney.model.form.DespesasForm;
import br.com.FamilyMoney.repository.DespesasRepository;

@RestController
@RequestMapping("/despesas")
public class DespesasController {
	
	@Autowired
	private DespesasRepository despesasRepository;
	
	@PostMapping
	public ResponseEntity<?> cadastroDespesa(@RequestBody @Valid DespesasForm despesasForm, UriComponentsBuilder
			uriBuilder) {
		//Verificação do cumprimento da regra de negócio estabelecida
		List<DespesasDao> despesaDaoList = despesasRepository.findByDescricao(despesasForm.getDescricao());
		for( DespesasDao despesaDao : despesaDaoList) {
			if(despesaDao.getData().getMonth() == despesasForm.getData().getMonth()
					&& despesaDao.getData().getYear() == despesasForm.getData().getYear()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDescricaoRepetida(Tipo.DESPESA));
			}
		}
		
		DespesasDao despesaDao = new DespesasDao(despesasForm);
		despesasRepository.save(despesaDao);
		
		URI uri =  uriBuilder.path("/despesa/{id}").buildAndExpand(despesaDao.getId()).toUri();
		return ResponseEntity.created(uri).body(new DespesasDto(despesaDao));
	}
	@GetMapping
	public List<DespesasDto> consultaDespesa() {
		List<DespesasDao> listaDeDespesasDao = despesasRepository.findAll();
		return DespesasDto.converte(listaDeDespesasDao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DespesasDto> consultaDespesaDetalhada(@PathVariable Long id) {
		Optional<DespesasDao> despesaDao = despesasRepository.findById(id);
		if (despesaDao.isPresent()) {
			return ResponseEntity.ok(new DespesasDto(despesaDao.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizaDespesa(@PathVariable Long id, 
			@RequestBody @Valid AtualizaDespesaForm atualizaForm){
		
		//Verificação do cumprimento da regra de negócio estabelecida
		List<DespesasDao> despesaDaoList = despesasRepository.findByDescricao(atualizaForm.getDescricao());
		for( DespesasDao despesaDao : despesaDaoList) {
			if(despesaDao.getData().getMonth() == atualizaForm.getData().getMonth()
					&& despesaDao.getData().getYear() == atualizaForm.getData().getYear()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDescricaoRepetida(Tipo.DESPESA));
			}
		}

		Optional<DespesasDao> despesaDao = despesasRepository.findById(id);
		
		if(despesaDao.isPresent()) {
			DespesasDao despesaDaoAtualizada = atualizaForm.atualiza(id, despesasRepository);
			return ResponseEntity.ok(new DespesasDto(despesaDaoAtualizada));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletaDespesa(@PathVariable Long id){
		Optional<DespesasDao> despesaParaDeletar = despesasRepository.findById(id);
		
		if (despesaParaDeletar.isPresent()) {
			despesasRepository.delete(despesaParaDeletar.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
