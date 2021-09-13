package br.recife.edu.ifpe.paokentin.controller.fornada;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.recife.edu.ifpe.paokentin.model.classes.Fornada;
import br.recife.edu.ifpe.paokentin.model.classes.Pao;
import br.recife.edu.ifpe.paokentin.model.repositorios.Fachada;

@RestController
public class FornadaRestController {

	@CrossOrigin(origins = "*")
	@PostMapping("/fornada/{codPao}")
	public ResponseEntity<?> inserir(@RequestBody Fornada f, @PathVariable("codPao") int codigoPao) {
		
		Pao pao;
		try {
			pao = Fachada.getCurrentInstance().lerPao(codigoPao);
			
			f.setPao(pao);
			
			Fachada.getCurrentInstance().inserir(f);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
//	@GetMapping("/fornada/{codFornada}")
//	public ResponseEntity<Fornada> ler(@PathVariable("codFornada") int codigo) {
//		
//		try {
//			Fornada f = Fachada.getCurrentInstance().lerFornada(codigo);
//			
//			return new ResponseEntity<Fornada>(f, HttpStatus.OK);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha ao tentar buscar a fornada.");
//		}	
//	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/fornada/{codFornada}")
	public void delete(@PathVariable("codFornada") int codigo) {
		
		try {
			Fachada.getCurrentInstance().deletarFornada(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha ao tentar deletar a fornada.");
		}
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/fornada")
	public ResponseEntity<List<Fornada>> lerTudo() throws ParseException {
		
		try {
			return new ResponseEntity<List<Fornada>>(Fachada.getCurrentInstance().lerTudoFornada(), HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha ao tentar buscar as fornadas.");
		}	
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/fornada/{codTipoPao}")
	public ResponseEntity<List<Fornada>> lerTipoPao(@PathVariable("codTipoPao") int codigo) throws ParseException {
		
		try {
			return new ResponseEntity<List<Fornada>>(Fachada.getCurrentInstance().lerTipoPaoFornada(codigo), HttpStatus.OK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha ao tentar buscar as fornadas.");
		}	
	}
}
