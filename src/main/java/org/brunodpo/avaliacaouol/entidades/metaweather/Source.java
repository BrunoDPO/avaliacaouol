package org.brunodpo.avaliacaouol.entidades.metaweather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Informações relativas à fonte da informação do clima
 * @author brunodpo
 * @since 2019-04-20
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Source {
	private String title;
	private String url;
}
