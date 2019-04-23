package org.brunodpo.avaliacaouol.entidades.ipvigilante;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade principal de retorno para API do IpVigilante
 * @author brunodpo
 * @since 2019-04-20
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class IpVigilanteInfo {
	private String status;
	private DataResult data;
}
