package br.sceweb.modelo;

public interface IEmpresaDAO {
	public int adiciona(Empresa empresa);
	public int exclui (String cnpj);
	public Empresa consultaEmpresa(String cnpj);
}
