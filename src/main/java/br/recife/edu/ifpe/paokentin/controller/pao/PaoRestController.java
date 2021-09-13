package br.recife.edu.ifpe.paokentin.controller.pao;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.recife.edu.ifpe.paokentin.model.classes.Pao;
import br.recife.edu.ifpe.paokentin.model.repositorios.Fachada;

@RestController
public class PaoRestController {

	@CrossOrigin(origins = "*")
	@PostMapping("/pao")
	public String inserir(@RequestBody Pao pao) {

		try {
			Fachada.getCurrentInstance().inserir(pao);

			return "Pão cadastrado com sucesso.";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Falha ao tentar cadastrar pão.";
		}
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/pao")
	public String alterar(@RequestBody Pao pao) {

		try {
			Fachada.getCurrentInstance().alterar(pao);

			return "Pão alterado com sucesso.";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Falha ao tentar alterar pão.";
		}
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/pao/{codigo}")
	public Pao ler(@PathVariable("codigo") int codigo) {

		try {
			return Fachada.getCurrentInstance().lerPao(codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/pao/{codigo}")
	public String delete(@PathVariable("codigo") int codigo) {

		try {
			Fachada.getCurrentInstance().deletarPao(codigo);

			return "Pão deletado com sucesso.";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "Falha ao tentar deletar pão.";
		}
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/pao")
	public java.util.List<Pao> lerTodos() throws ParseException {

		try {
			return Fachada.getCurrentInstance().lerTudoPao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
