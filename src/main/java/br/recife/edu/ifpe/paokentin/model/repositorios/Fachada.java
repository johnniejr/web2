package br.recife.edu.ifpe.paokentin.model.repositorios;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import br.recife.edu.ifpe.paokentin.model.classes.Pao;
import br.recife.edu.ifpe.paokentin.model.classes.Fornada;

public class Fachada {
	
	private static Fachada myself = null;
	
	private Repositorio<Pao, Integer> rPao = null;
	private Repositorio<Fornada, Integer> rFornada = null;
	
	
	private Fachada() {
		
		this.rPao = new RepositorioPao();
		this.rFornada = new RepositorioFornada();
		
	}
	
	public static Fachada getCurrentInstance() {
		
		if(myself == null)
			myself = new Fachada();
		
		return myself;
		
	}
	
	public void inserir(Pao p) throws SQLException {
		this.rPao.inserir(p);
	}
	
	public void inserir(Fornada f) throws SQLException {
		this.rFornada.inserir(f);
	}
	
	public void alterar(Pao p) throws SQLException {
		this.rPao.alterar(p);
	}
	
	public void alterar(Fornada f) throws SQLException {
		this.rFornada.alterar(f);
	}
	
	public Pao lerPao(int codigo) throws SQLException {
		return this.rPao.ler(codigo);
	}
	
//	public Fornada lerFornada(int codigo) throws SQLException {
//		return this.rFornada.ler(codigo);
//	}
	
	public void deletarPao(int codigo) throws SQLException {
		this.rPao.deletar(codigo);
	}
	
	public void deletarFornada(int codigo) throws SQLException {
		this.rFornada.deletar(codigo);
	}
	
	public List<Pao> lerTudoPao() throws SQLException, ParseException{
		return this.rPao.lerTudo();
	}
	
	public List<Fornada> lerTudoFornada() throws SQLException, ParseException{
		return this.rFornada.lerTudo();
	}
	
	public List<Fornada> lerTipoPaoFornada(int codigo) throws SQLException{
		return ((RepositorioFornada)this.rFornada).lerTipoPaoFornada(codigo);
	}
}
