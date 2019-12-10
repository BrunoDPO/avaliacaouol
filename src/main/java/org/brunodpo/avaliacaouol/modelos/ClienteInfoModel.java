package org.brunodpo.avaliacaouol.modelos;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ClienteInfoModel {

	public Long id;
	
	@NonNull
	public String nome;
	
	@NonNull
	public Integer idade;
	
	public String ip;
	
	public Date dataCadastramento;
	
	public Integer temperaturaMinimaCadastro;

	public Integer temperaturaMaximaCadastro;

}
