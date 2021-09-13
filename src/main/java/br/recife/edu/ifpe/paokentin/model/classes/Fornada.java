package br.recife.edu.ifpe.paokentin.model.classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormat;

import br.recife.edu.ifpe.paokentin.model.repositorios.ConnectionManager;

public class Fornada {
	
	private int codigo;
	private String quantidade;
	private String dataHoraInicio;
	private String statusFornada;
	private Pao pao;
	
	public Fornada() {
		this.dataHoraInicio = this.currentDate();
		this.statusFornada = "Em Preparo";
	}
	
//	public String setData() {
//		return this.currentDate();
//		
//		return currentTime;
//	}
	
	private String currentDate() {
		Date data = new Date();
		data.getTime();
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String currentTime = sdf.format(data);
		
		return currentTime;
	}
	
	public String verifyStatus(String dtInicio, Pao p) {
		String status = "Em Preparo";
		
		int anoDtInicio = Integer.parseInt(dtInicio.substring(0, 4));
		int mesDtInicio = Integer.parseInt(dtInicio.substring(5, 7));
		int diaDtInicio = Integer.parseInt(dtInicio.substring(8, 10));
		int horaDtInicio = Integer.parseInt(dtInicio.substring(11, 13));
		int minutoDtInicio = Integer.parseInt(dtInicio.substring(14, 16));
		int segundoDtInicio = Integer.parseInt(dtInicio.substring(17, 19));
		
		String currentDt = currentDate();
		
		int anoDtAtual = Integer.parseInt(currentDt.substring(0, 4));
		int mesDtAtual = Integer.parseInt(currentDt.substring(5, 7));
		int diaDtAtual = Integer.parseInt(currentDt.substring(8, 10));
		int horaDtAtual = Integer.parseInt(currentDt.substring(11, 13));
		int minutoDtAtual = Integer.parseInt(currentDt.substring(14, 16));
		int segundoDtAtual = Integer.parseInt(currentDt.substring(17, 19));				
		
//		Subtrair data atual com a data de inicio
		DateTime start = new DateTime(anoDtInicio, mesDtInicio, diaDtInicio, horaDtInicio, minutoDtInicio, segundoDtInicio);
		DateTime end = new DateTime(anoDtAtual, mesDtAtual, diaDtAtual, horaDtAtual, minutoDtAtual, segundoDtAtual);
		Period period = new Period(start, end);
		
		String diff = PeriodFormat.getDefault().print(period);
		Minutes minutosDiff = Minutes.minutesBetween(new DateTime(start), new DateTime(end));

		int tempoPreparo = p.getTempoPreparo();
		
		if (tempoPreparo < minutosDiff.getMinutes()) {
			status = "Finalizado";
			this.statusFornada = status;
		} else {
			int tempoRestante = tempoPreparo - minutosDiff.getMinutes();
			status = "Em preparo - Faltam: " + tempoRestante + " minutos.";
			this.statusFornada = status;
		}
		
		return status;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(String dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public String getStatusFornada() {
		return statusFornada;
	}

	public void setStatusFornada(String dataInicio, Pao p) throws SQLException {		 
		this.statusFornada = verifyStatus(dataInicio, p);
	
		String sql = "update fornada set statusFornada=? where codigo=?";

		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

		pstm.setString(1, getStatusFornada());
		pstm.setInt(2, getCodigo());

		pstm.execute();
	}

	public Pao getPao() {
		return pao;
	}

	public void setPao(Pao pao) {
		this.pao = pao;
	}
}
