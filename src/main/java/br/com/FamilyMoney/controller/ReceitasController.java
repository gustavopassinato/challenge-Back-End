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
import br.com.FamilyMoney.model.ReceitasDao;
import br.com.FamilyMoney.model.dto.ReceitasDto;
import br.com.FamilyMoney.model.form.AtualizaReceitasForm;
import br.com.FamilyMoney.model.form.ReceitasForm;
import br.com.FamilyMoney.repository.ReceitasRepository;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {
	
	@Autowired
	private ReceitasRepository receitasRepository;
	
	@PostMapping
	public ResponseEntity<?> cadastroReceita(@RequestBody @Valid ReceitasForm receitaForm, UriComponentsBuilder
			uriBuilder) {
		//Verificação do cumprimento da regra de negócio estabelecida
		List<ReceitasDao> ReceitasDaoList = receitasRepository.findByDescricao(receitaForm.getDescricao());
		for( ReceitasDao receitaDao : ReceitasDaoList) {
			if(receitaDao.getData().getMonth() == receitaForm.getData().getMonth()
					&& receitaDao.getData().getYear() == receitaForm.getData().getYear()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDescricaoRepetida(Tipo.RECEITA));
			}
		}
		
		ReceitasDao receitaDao = new ReceitasDao(receitaForm);
		receitasRepository.save(receitaDao);
		
		URI uri =  uriBuilder.path("/receitas/{id}").buildAndExpand(receitaDao.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReceitasDto(receitaDao));
	}
	
	@GetMapping
	public List<ReceitasDto> listagemReceitas(){
		List<ReceitasDao> receitasDaoList = receitasRepository.findAll();
		
		return ReceitasDto.converte(receitasDaoList);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReceitasDto> listagemReceitaDetalhada(@PathVariable Long id) {
		Optional<ReceitasDao> optionalReceita = receitasRepository.findById(id);
		
		if (optionalReceita.isPresent()) {
			return ResponseEntity.ok(new ReceitasDto(optionalReceita.get()));
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizacaoReceita(@PathVariable Long id,
			@RequestBody @Valid AtualizaReceitasForm atualizaReceitaForm){
		
		//Verificação do cumprimento da regra de negócio estabelecida
		List<ReceitasDao> ReceitasDaoList = receitasRepository.findByDescricao(atualizaReceitaForm.getDescricao());
		for( ReceitasDao receitaDao : ReceitasDaoList) {
			if(receitaDao.getData().getMonth() == atualizaReceitaForm.getData().getMonth()
					&& receitaDao.getData().getYear() == atualizaReceitaForm.getData().getYear()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDescricaoRepetida(Tipo.RECEITA));
			}
		}
		
		Optional<ReceitasDao> receitaParaAtualizar = receitasRepository.findById(id);
		
		if (receitaParaAtualizar.isPresent()) {
			ReceitasDao receitaAtualizada = atualizaReceitaForm.atualiza(id, receitasRepository);
			return ResponseEntity.ok(new ReceitasDto(receitaAtualizada));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarReceita(@PathVariable Long id){
		Optional<ReceitasDao> receitaParaDeletar = receitasRepository.findById(id);
		
		if(receitaParaDeletar.isPresent()) {
			receitasRepository.delete(receitaParaDeletar.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
