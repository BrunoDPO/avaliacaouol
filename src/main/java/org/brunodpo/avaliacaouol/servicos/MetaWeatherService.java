package org.brunodpo.avaliacaouol.servicos;

import java.util.HashMap;
import java.util.Map;

import org.brunodpo.avaliacaouol.entidades.metaweather.MetaWeatherInfo;
import org.brunodpo.avaliacaouol.entidades.metaweather.MetaWeatherLocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Chamador para a API do MetaWeather
 * @author bruno.oliveira
 * @since 2019-04-20
 */
@Service
public class MetaWeatherService implements InitializingBean {

	@Value("${metaweather.url}/api/location/search/?lattlong={lattlong}")
	private String urlQueryLocation;

	@Value("${metaweather.url}/api/location/{woeid}")
	private String urlForecast;
	
	@Autowired
	RestTemplate restTemplate;

	public MetaWeatherLocation obterLocalizacaoMaisProxima(Double latitude, Double longitude) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("lattlong", String.format("%f,%f", latitude, longitude));
		MetaWeatherLocation[] locations = restTemplate.getForObject(urlQueryLocation, MetaWeatherLocation[].class, params);
		return locations[0];
	}
	
	public MetaWeatherInfo obterClima(Integer woeid) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("woeid", woeid.toString());
		return restTemplate.getForObject(urlForecast, MetaWeatherInfo.class, params);
	}

	@Override
	public void afterPropertiesSet() throws Exception { }

}
