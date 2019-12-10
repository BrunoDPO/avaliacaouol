package org.brunodpo.avaliacaouol.modelos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ClienteModel {

	@NonNull
	public String nome;
	
	@NonNull
	public Integer idade;

}
