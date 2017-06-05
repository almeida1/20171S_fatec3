package br.sceweb.modelo;

//Cloudscape concrete DAO Factory implementation
import java.sql.*;

import br.sceweb.servico.FabricaDeConexoes;

public class MySQLDAOFactory extends DAOFactory {
	

	// obtem uma conexao para MySQL
	public static Connection createConnection() {
		return new FabricaDeConexoes().getConnection();
	}

	public MySQLConvenioDAO getConvenioDAO() {
		
		return new MySQLConvenioDAO();
	}

	public MySQLEmpresaDAO getEmpresaDAO() {
		
		return new MySQLEmpresaDAO();
	}

}
