package org.brunodpo.avaliacaouol.controladores;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.brunodpo.avaliacaouol.entidades.Cliente;
import org.brunodpo.avaliacaouol.entidades.ipvigilante.IpVigilanteInfo;
import org.brunodpo.avaliacaouol.entidades.metaweather.MetaWeatherInfo;
import org.brunodpo.avaliacaouol.entidades.metaweather.MetaWeatherLocation;
import org.brunodpo.avaliacaouol.modelos.ClienteInfoModel;
import org.brunodpo.avaliacaouol.modelos.ClienteModel;
import org.brunodpo.avaliacaouol.repositorios.ClienteRepository;
import org.brunodpo.avaliacaouol.servicos.IpVigilanteService;
import org.brunodpo.avaliacaouol.servicos.MetaWeatherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para as chamadas referentes a um cliente
 * @author bruno.oliveira
 * @since 2019-04-20
 */
@RestController
public class ClienteController {
	/**
	 * Objeto referente às requisições
	 */
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * Objeto referente ao mapeamento de entidades
	 */
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * Repositório de cliente
	 */
	@Autowired
	private ClienteRepository clienteRepository;
	
	/**
	 * Referência ao serviço do IpVigilante
	 */
	@Autowired
	private IpVigilanteService ipVigilanteService;
	
	/**
	 * Referência ao serviço do MetaWeather
	 */
	@Autowired
	private MetaWeatherService metaWeatherService;
	
	/**
	 * Cria um cliente
	 * @param novo Novo cliente
	 * @return Entidade do cliente
	 */
	@RequestMapping(path = "/api/cliente", method = RequestMethod.POST)
	public ResponseEntity<ClienteInfoModel> incluir(
			@RequestBody(required = true) ClienteModel novo) {
		// Obtém a localização pelo IP da requisição
		String ipChamador = request.getRemoteAddr();
		
		// Verifica se o chamador e o servidor estão no mesmo local
		IpVigilanteInfo ipVigilante = null;
		if (ipChamador.equals("127.0.0.1")) {
			ipVigilante = ipVigilanteService.obterInformacoesLocais();
		} else {
			ipVigilante = ipVigilanteService.obterInformacoes(ipChamador);
		}
		if (ipVigilante == null) {
			return new ResponseEntity<ClienteInfoModel>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// Obtém o local mais próximo com informação de clima
		MetaWeatherLocation local = metaWeatherService.obterLocalizacaoMaisProxima(ipVigilante.getData().getLatitude(), ipVigilante.getData().getLongitude());
		// Obtém as informações de clima desse local
		MetaWeatherInfo clima = metaWeatherService.obterClima(local.getWoeid());

		// Cria o cliente baseado nas informações recebitas e coletadas nas APIs
		Cliente cliente = new Cliente(novo.getNome(), novo.getIdade(), ipVigilante.getData().getIpv4(), clima.getConsolidated_weather()[0].getMin_temp(), clima.getConsolidated_weather()[0].getMax_temp());
		clienteRepository.save(cliente);
		ClienteInfoModel cli = modelMapper.map(cliente, ClienteInfoModel.class);
		return new ResponseEntity<ClienteInfoModel>(cli, HttpStatus.OK);
	}
	
	/**
	 * Obtém um cliente por seu ID
	 * @param id ID do cliente
	 * @return Entidade do cliente
	 */
	@RequestMapping(path = "/api/cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<ClienteInfoModel> obter(
			@PathVariable(value = "id") Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent())
			return new ResponseEntity<ClienteInfoModel>(HttpStatus.NOT_FOUND);
		ClienteInfoModel cli = modelMapper.map(cliente.get(), ClienteInfoModel.class);
		return new ResponseEntity<ClienteInfoModel>(cli, HttpStatus.OK);
	}
	
	/**
	 * Atualiza as informações de um cliente
	 * @param id ID do cliente
	 * @param alterado Cliente alterado
	 * @return Entidade atualizada do cliente
	 */
	@RequestMapping(path = "/api/cliente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ClienteInfoModel> atualizar(
			@PathVariable(value = "id") Long id,
			@RequestBody(required = true) ClienteModel alterado) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent())
			return new ResponseEntity<ClienteInfoModel>(HttpStatus.NOT_FOUND);
		Cliente alterar = cliente.get();
		alterar.setNome(alterado.nome);
		alterar.setIdade(alterado.idade);
		clienteRepository.save(alterar);
		ClienteInfoModel cli = modelMapper.map(alterar, ClienteInfoModel.class);
		return new ResponseEntity<ClienteInfoModel>(cli, HttpStatus.OK); 
	}
	
	/**
	 * Apaga um cliente por seu ID
	 * @param id ID do cliente
	 * @return Vazio
	 */
	@RequestMapping(path = "/api/cliente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ClienteInfoModel> apagar(
			@PathVariable(value = "id") Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent())
			return new ResponseEntity<ClienteInfoModel>(HttpStatus.NOT_FOUND);
		clienteRepository.deleteById(id);
		return new ResponseEntity<ClienteInfoModel>(HttpStatus.OK);
	}
}
