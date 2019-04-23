package org.brunodpo.avaliacaouol.entidades.metaweather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Informações principais
 * @author brunodpo
 * @since 2019-04-20
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Parent {
	private String title;
	private String location_type;
	private String latt_long;
	private Integer woeid;
}
