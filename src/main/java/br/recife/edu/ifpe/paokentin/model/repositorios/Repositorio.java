package br.recife.edu.ifpe.paokentin.model.repositorios;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import br.recife.edu.ifpe.paokentin.model.classes.Fornada;

public interface Repositorio <Cl,Key>{

	public void inserir(Cl cl) throws SQLException;
	public void alterar(Cl cl) throws SQLException;
	public Cl ler(Key k) throws SQLException;
	public void deletar(Key k) throws SQLException;
	public List<Cl> lerTudo() throws SQLException, ParseException;
}
