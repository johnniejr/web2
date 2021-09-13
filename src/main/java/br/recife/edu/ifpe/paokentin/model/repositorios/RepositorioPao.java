package br.recife.edu.ifpe.paokentin.model.repositorios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.recife.edu.ifpe.paokentin.model.classes.Pao;

public class RepositorioPao implements Repositorio<Pao, Integer>{
	
	RepositorioPao() {

	}

	@Override
	public void inserir(Pao cl) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "insert into pao(nome,descricao,tempoPreparo) values (?,?,?)";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, cl.getNome());
		pstm.setString(2, cl.getDescricao());
		pstm.setInt(3, cl.getTempoPreparo());
		
		pstm.execute();
		
	}

	@Override
	public void alterar(Pao cl) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "update pao set nome=?, descricao=?, tempoPreparo=? where codigo=?";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, cl.getNome());
		pstm.setString(2, cl.getDescricao());
		pstm.setInt(3, cl.getTempoPreparo());
		
		pstm.setInt(4, cl.getCodigo());
		
		pstm.execute();
	}

	@Override
	public Pao ler(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "select * from pao where codigo = ?";;
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setInt(1, k);
		
		ResultSet result = pstm.executeQuery();
		
		Pao p = null;
		
		if(result.next()) {
			
			p = new Pao();
			
			p.setCodigo(k);
			p.setNome(result.getString("nome"));
			p.setDescricao(result.getString("descricao"));
			p.setTempoPreparo(result.getInt("tempoPreparo"));
			
		}
		
		return p;
	}

	@Override
	public void deletar(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "delete from pao where codigo = ?";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setInt(1, k);
		
		pstm.execute();
	}

	@Override
	public List<Pao> lerTudo() throws SQLException {
		// TODO Auto-generated method stub
		
		String sql = "select * from pao";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		ResultSet result = pstm.executeQuery();
		
		List<Pao> paes = new ArrayList<Pao>();
		
		while(result.next()) {
			
			Pao tPao = new Pao();

			tPao.setCodigo(result.getInt("codigo"));
			tPao.setNome(result.getString("nome"));
			tPao.setDescricao(result.getString("descricao"));
			tPao.setTempoPreparo(result.getInt("tempoPreparo"));

			paes.add(tPao);
		}
		
		return paes;
	}

}
