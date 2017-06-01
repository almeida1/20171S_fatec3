package br.sceweb.modelo;

//Cloudscape concrete DAO Factory implementation
import java.sql.*;

public class MySQLDAOFactory extends DAOFactory {
	public static final String DRIVER = "COM.cloudscape.core.RmiJdbcDriver";
	public static final String DBURL = "jdbc:cloudscape:rmi://localhost:1099/CoreJ2EEDB";

	// method to create Cloudscape connections
	public static Connection createConnection() {
		Connection c = null;
		return c;
	}

	public ConvenioDAO getConvenioDAO() {
		// CloudscapeCustomerDAO implements CustomerDAO
		return new ConvenioDAO();
	}

	public EmpresaDAO getEmpresaDAO() {
		// CloudscapeAccountDAO implements AccountDAO
		return new EmpresaDAO();
	}

}
