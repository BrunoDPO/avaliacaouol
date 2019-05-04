package org.brunodpo.avaliacaouol.controladores;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.brunodpo.avaliacaouol.entidades.Cliente;
import org.brunodpo.avaliacaouol.entidades.ipvigilante.IpVigilanteInfo;
import org.brunodpo.avaliacaouol.entidades.metaweather.MetaWeatherInfo;
import org.brunodpo.avaliacaouol.entidades.metaweather.MetaWeatherLocation;
import org.brunodpo.avaliacaouol.repositorios.ClienteRepository;
import org.brunodpo.avaliacaouol.servicos.IpVigilanteService;
import org.brunodpo.avaliacaouol.servicos.MetaWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	 * @param nome Nome do cliente
	 * @param idade Idade do cliente
	 * @return Entidade do cliente
	 */
	@RequestMapping(path = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<Cliente> incluir(
			@RequestParam(value="nome", required=true) String nome,
			@RequestParam(value="idade", required=true) Integer idade) {
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
			return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// Obtém o local mais próximo com informação de clima
		MetaWeatherLocation local = metaWeatherService.obterLocalizacaoMaisProxima(ipVigilante.getData().getLatitude(), ipVigilante.getData().getLongitude());
		// Obtém as informações de clima desse local
		MetaWeatherInfo clima = metaWeatherService.obterClima(local.getWoeid());

		// Cria o cliente baseado nas informações recebitas e coletadas nas APIs
		Cliente cliente = new Cliente(nome, idade, ipVigilante.getData().getIpv4(), clima.getConsolidated_weather()[0].getMin_temp(), clima.getConsolidated_weather()[0].getMax_temp());
		clienteRepository.save(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
	}
	
	/**
	 * Obtém um cliente por seu ID
	 * @param id ID do cliente
	 * @return Entidade do cliente
	 */
	@RequestMapping(path = "/cliente", method = RequestMethod.GET)
	public ResponseEntity<Cliente> obter(
			@RequestParam(value="id", required=true) Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent())
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
	}
	
	/**
	 * Atualiza as informações de um cliente
	 * @param id ID do cliente
	 * @param nome Nome do cliente
	 * @param idade Idade do cliente
	 * @return Entidade atualizada do cliente
	 */
	@RequestMapping(path = "/cliente", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> atualizar(
			@RequestParam(value="id", required=true) Long id,
			@RequestParam(value="nome", required=true) String nome,
			@RequestParam(value="idade", required=true) Integer idade) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent())
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		cliente.get().setNome(nome);
		cliente.get().setIdade(idade);
		clienteRepository.save(cliente.get());
		return new ResponseEntity<Cliente>(HttpStatus.OK); 
	}
	
	/**
	 * Apaga um cliente por seu ID
	 * @param id ID do cliente
	 * @return Vazio
	 */
	@RequestMapping(path = "/cliente", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> apagar(
			@RequestParam(value="id", required=true) Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (!cliente.isPresent())
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		clienteRepository.deleteById(id);
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}
}
