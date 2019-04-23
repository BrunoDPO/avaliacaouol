package org.brunodpo.avaliacaouol.entidades.metaweather;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Informações de clima consolidado
 * @author brunodpo
 * @since 2019-04-20
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ConsolidatedWeather {
	private Long id;
	private Date applicable_date;
	private String weather_state_name;
	private String weather_state_abbr;
	private String wind_speed;
	private String wind_direction;
	private String wind_direction_compass;
	private Integer min_temp;
	private Integer the_temp;
	private Integer max_temp;
	private Double air_pressure;
	private Double humidity;
	private Double visibility;
	private Integer predictability;
}
