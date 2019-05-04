package org.brunodpo.avaliacaouol.servicos;

import java.util.HashMap;
import java.util.Map;

import org.brunodpo.avaliacaouol.entidades.ipvigilante.IpVigilanteInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Chamador para a API do IpVigilante
 * @author bruno.oliveira
 * @since 2019-04-20
 */
@Service
public class IpVigilanteService implements InitializingBean {
	
	@Value("${ipvigilante.url}/json")
	private String url;

	@Value("${ipvigilante.url}/json/{ip}")
	private String urlIp;
	
	@Autowired
	RestTemplate restTemplate;

	public IpVigilanteInfo obterInformacoes(String ip) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ip", ip);
		return restTemplate.getForObject(urlIp, IpVigilanteInfo.class, params);
	}
	
	public IpVigilanteInfo obterInformacoesLocais() {
		return restTemplate.getForObject(url, IpVigilanteInfo.class);		
	}

	@Override
	public void afterPropertiesSet() throws Exception { }
}
