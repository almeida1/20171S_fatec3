package br.sceweb.modelo;

import org.apache.log4j.Logger;

public class EmpresaService {
	
	public void cadastrarEmpresa(Empresa empresa){
		Logger logger = Logger.getLogger(EmpresaService.class);
		try{
			DAOFactory fabricaDAO = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			IEmpresaDAO empresaDAO = fabricaDAO.getEmpresaDAO();
			empresaDAO.adiciona(empresa);
			logger.info("empresa service metodo cadastra executado");
		}catch (Exception e){
            e.printStackTrace();
		}
	}

}
