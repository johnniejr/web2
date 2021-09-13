package br.recife.edu.ifpe.paokentin.model.repositorios;

//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.recife.edu.ifpe.paokentin.model.classes.Pao;
import br.recife.edu.ifpe.paokentin.model.classes.Fornada;

public class RepositorioFornada implements Repositorio<Fornada, Integer> {

	RepositorioFornada() {

	}

	@Override
	public void inserir(Fornada cl) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "insert into fornada(quantidade, dataHoraInicio, statusFornada, codPao) values (?,?,?,?)";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setString(1, cl.getQuantidade());
		pstm.setString(2, cl.getDataHoraInicio());
		pstm.setString(3, cl.getStatusFornada());
		pstm.setInt(4, cl.getPao().getCodigo());

		pstm.execute();

	}

	@Override
	public void alterar(Fornada cl) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "update fornada set statusFornada=? where codigo=?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setString(1, cl.getStatusFornada());

		pstm.setInt(2, cl.getCodigo());

		pstm.execute();

	}

	@Override
	public Fornada ler(Integer k) throws SQLException {
		// TODO Auto-generated method stub
//
//		String sql = "select * from fornada where codigo=?";
//
//		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
//
//		pstm.setInt(1, k);
//
//		ResultSet result = pstm.executeQuery();
//
		Fornada l = null;
//
//		if (result.next()) {
//
////			Date formt = result.getDate("dataHoraInicio");
//			Date formt = new Date();
////			java.text.SimpleDateFormat sdf = 
////				     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////			
////			String dtFormat = sdf.format(formt);
//			
//			l = new Fornada();
//
//			l.setCodigo(k);
//			l.setQuantidade(result.getString("quantidade"));
////			l.setDataHoraInicio(formt);
////			l.setStatusFornada(result.getString("statusFornada"));
////			l.setStatusFornada(result.getDate("dataHoraInicio"));
//			
//			Integer codigo = result.getInt("codPao");
//
//			Pao c = Fachada.getCurrentInstance().lerPao(codigo);
//
//			l.setPao(c);
//
//		}
//
		return l;
//
	}

	@Override
	public void deletar(Integer k) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "delete from fornada where codigo=?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setInt(1, k);
		
		pstm.execute();
	}

	@Override
	public List<Fornada> lerTudo() throws SQLException, ParseException {
		// TODO Auto-generated method stub

		String sql = "select * from fornada";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		ResultSet result = pstm.executeQuery();

		List<Fornada> fornadas = new ArrayList<Fornada>();

		while (result.next()) {

			Fornada l = new Fornada();
			Integer codigo = result.getInt("codPao");
			Pao c = Fachada.getCurrentInstance().lerPao(codigo);

			l.setCodigo(result.getInt("codigo"));
			l.setQuantidade(result.getString("quantidade"));
			l.setDataHoraInicio(result.getString("dataHoraInicio"));
			l.setStatusFornada(result.getString("dataHoraInicio"), c);

			l.setPao(c);

			fornadas.add(l);
		}

		return fornadas;
	}
	
	public List<Fornada> lerTipoPaoFornada(Integer k) throws SQLException {

		String sql = "select * from fornada where codPao=?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setInt(1, k);

		ResultSet result = pstm.executeQuery();

		List<Fornada> fornadas = new ArrayList<Fornada>();

		while (result.next()) {

			Fornada l = new Fornada();
			Integer codigo = result.getInt("codPao");
			Pao c = Fachada.getCurrentInstance().lerPao(codigo);

			l.setCodigo(result.getInt("codigo"));
			l.setQuantidade(result.getString("quantidade"));
			l.setDataHoraInicio(result.getString("dataHoraInicio"));
			l.setStatusFornada(result.getString("dataHoraInicio"), c);

			l.setPao(c);

			if (result.isLast()) {
				fornadas.add(l);	
			}
		}

		return fornadas;
	}
}
