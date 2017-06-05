package br.sceweb.modelo;

import java.util.List;

public interface IConvenioDAO {
	public int adiciona(Convenio convenio);
	public int exclui(String cnpj);
	public List<Convenio> consultaConvenio(String cnpj);
	public boolean verificaVigencia(Convenio novoConvenio);
}
