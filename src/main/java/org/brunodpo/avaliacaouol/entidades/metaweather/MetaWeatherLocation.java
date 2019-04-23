package org.brunodpo.avaliacaouol.entidades.metaweather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Informações relativas à localização
 * @author brunodpo
 * @since 2019-04-20
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class MetaWeatherLocation {
	private String title;
	private String location_type;
	private String latt_long;
	private Integer woeid;
	private Integer distance;
}
