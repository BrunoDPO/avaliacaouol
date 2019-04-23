package org.brunodpo.avaliacaouol.entidades.ipvigilante;

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
public class DataResult {
	private String ipv4;
	private String continent_name;
	private String country_name;
	private String subdivision_1_name;
	private String subdivision_2_name;
	private String city_name;
	private Double latitude;
	private Double longitude;	
}
