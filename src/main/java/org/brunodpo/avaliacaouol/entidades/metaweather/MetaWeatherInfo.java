package org.brunodpo.avaliacaouol.entidades.metaweather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade principal referente à pesquisa por localização do MetaWeather
 * @author brunodpo
 * @since 2019-04-20
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class MetaWeatherInfo {
	private String title;
	private String location_type;
	private String latt_long;
	private String time;
	private String sun_rise;
	private String sun_set;
	private Parent parent;
	private ConsolidatedWeather[] consolidated_weather;
	private Source[] sources;
}
