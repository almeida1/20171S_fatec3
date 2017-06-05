package br.sceweb.controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.sceweb.modelo.Convenio;
import br.sceweb.modelo.MySQLConvenioDAO;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.MySQLEmpresaDAO;

/**
 * Servlet implementation class ServletControle
 */
public class ServletControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ServletControle.class);
	String mensagem = "";
	MySQLEmpresaDAO empresaDAO;
	String cnpjParaExclusao = "";// seta o cnpj para exclusao

	/**
	 * Default constructor.
	 */
	public ServletControle() {
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("erro", null);
		executaComando(request, response);
	}

	protected void executaComando(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametro = request.getParameter("acao");
		logger.info("acao do servletcontrole = " + parametro);
		String url = "";
		String resultado = "";
		request.setAttribute("erro", null);
		if (parametro.equals("IncluirEmpresa")) {
			url = "/visao/FormEmpresa.jsp";
			try {
				resultado = cadastrarEmpresa(request.getParameter("txtCNPJ"), request.getParameter("txtNomeDaEmpresa"),
						request.getParameter("txtNomeFantasia"), request.getParameter("txtEndereco"),
						request.getParameter("txtTelefone"));
				logger.info("resultado do cadastra = " + resultado);
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
				logger.info("erro  = " + e.getMessage());

			}
		}
		if (parametro.equals("ConsultarEmpresa")) {
			url = "/visao/FormEmpresa.jsp";
			Empresa empresa = new Empresa();
			String cnpj = request.getParameter("txtCNPJ");
			logger.info("consulta empresa  = " + cnpj);
			try {
				if (!cnpj.isEmpty()) {
					empresa = consulta(cnpj);
					if (empresa != null) {
						logger.info("consulta empresa nome da empresa  = " + empresa.getNomeDaEmpresa());
						request.setAttribute("nomeDaEmpresa", empresa.getNomeDaEmpresa());
						request.setAttribute("cnpj", empresa.getCnpj());
						request.setAttribute("nomeFantasia", empresa.getNomeFantasia());
						request.setAttribute("endereco", empresa.getEndereco());
						request.setAttribute("telefone", empresa.getTelefone());
						request.setAttribute("responsavel", empresa.getResponsavel());
						request.setAttribute("telefoneResponsavel", empresa.getTelefoneResponsavel());
						request.setAttribute("setor", empresa.getSetor());
						request.setAttribute("email", empresa.getEmail());
						request.setAttribute("msg", "");
						url = "/visao/FormEmpresaResultadoDaConsulta.jsp";
					} else {
						request.setAttribute("msg", "cnpj invalido");
					}
				} else {
					request.setAttribute("msg", "cnpj invalido");
				}
			} catch (Exception e) {
				logger.info(e.getMessage() + e.getCause());
			}
			request.getRequestDispatcher(url).forward(request, response);

		}
		if (parametro.equals("IncluirConvenio")) {
			url = "/visao/FormConvenio.jsp";
			try {
				resultado = cadastrarConvenio(request.getParameter("txtCNPJ"), request.getParameter("txtDtInicio"),
						request.getParameter("txtDtTermino"));
				logger.info("resultado do cadastra convenio= " + resultado);
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
				logger.info("erro  = " + e.getMessage());

			}
		}
		if (parametro.equals("ExcluirEmpresa")) {
			url = "/visao/FormEmpresa.jsp";
			try {
				resultado = excluirEmpresa(request.getParameter("txtCNPJ"));
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
				logger.info("erro  = " + e.getMessage());

			}
		}
		if (parametro.equals("ExcluirConvenio")) {
			url = "/visao/FormConvenio.jsp";
			try {
				resultado = excluirConvenio(request.getParameter("txtCNPJ"));
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
			} catch (Exception e) {
				request.setAttribute("msg", resultado);
				request.getRequestDispatcher(url).forward(request, response);
				logger.info("erro  = " + e.getMessage());

			}
		}
	}

	public String cadastrarEmpresa(String cnpj, String nomeDaEmpresa, String nomeFantasia, String endereco,
			String telefone) {
		String msg = "";
		Empresa empresa = new Empresa();
		MySQLEmpresaDAO empresaDAO = new MySQLEmpresaDAO();
		try {
			empresa.setCnpj(cnpj);
			empresa.setNomeDaEmpresa(nomeDaEmpresa);
			empresa.setNomeFantasia(nomeFantasia);
			empresa.setEndereco(endereco);
			empresa.setTelefone(telefone);
			empresaDAO.adiciona(empresa);
			msg = "cadastro realizado com sucesso";
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}

	public Empresa consulta(String cnpj) {
		logger.info("consulta empresa 2 = " + cnpj);
		MySQLEmpresaDAO empresaDAO = new MySQLEmpresaDAO();
		return empresaDAO.consultaEmpresa(cnpj);
	}

	public String excluirEmpresa(String cnpj) {
		String msg = "";
		int codigoRetorno = 0;
		MySQLEmpresaDAO empresaDAO = new MySQLEmpresaDAO();
		try {
			codigoRetorno = empresaDAO.exclui(cnpj);
			if (codigoRetorno == 1)
				msg = "excluido com sucesso";
			else
				msg = "erro na exclusão - não localizado";
		} catch (Exception e) {
			msg = e.getMessage();
		}

		return msg;
	}
	public String excluirConvenio(String cnpj) {
		String msg = "";
		int codigoRetorno = 0;
		MySQLConvenioDAO convenioDAO = new MySQLConvenioDAO();
		try {
			codigoRetorno = convenioDAO.exclui(cnpj);
			if (codigoRetorno == 1)
				msg = "excluido com sucesso";
			else
				msg = "erro na exclusão - não localizado";
		} catch (Exception e) {
			msg = e.getMessage();
		}

		return msg;
	}
	public String cadastrarConvenio(String cnpj, String dataInicio, String dataTermino) {
		String msg = "";
		int codigoRetorno = 0;
		MySQLConvenioDAO convenioDAO = new MySQLConvenioDAO();
		try {
			Convenio convenio = new Convenio(cnpj, dataInicio, dataTermino);
			codigoRetorno = convenioDAO.adiciona(convenio);
			if (codigoRetorno == 0) {
				msg = "erro - cadastro nao realizado";
			} else {
				msg = "cadastro realizado com sucesso";
			}
		} catch (Exception e) {
			msg = e.getMessage();
		}
		return msg;
	}

}
