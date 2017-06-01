package br.sceweb.modelo;

//Abstract class DAO Factory
public abstract class DAOFactory {

	// List of DAO types supported by the factory
	public static final int CLOUDSCAPE = 1;
	public static final int ORACLE = 2;
	public static final int SYBASE = 3;
	public static final int MYSQL = 4;

	// There will be a method for each DAO that can be
	// created. The concrete factories will have to
	// implement these methods.
	public abstract ConvenioDAO getConvenioDAO();

	public abstract EmpresaDAO getEmpresaDAO();

	public static DAOFactory getDAOFactory(int whichFactory) {

		switch (whichFactory) {
		case CLOUDSCAPE:
			// return new CloudscapeDAOFactory();
		case ORACLE:
			// return new OracleDAOFactory();
		case MYSQL:
			return new MySQLDAOFactory();

		default:
			return null;
		}
	}
}