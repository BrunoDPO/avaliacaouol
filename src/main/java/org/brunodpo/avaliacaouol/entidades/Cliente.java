package org.brunodpo.avaliacaouol.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Entidade que representa um Cliente
 * @author bruno.oliveira
 * @since 2019-04-20
 */
@RequiredArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	private String nome;
	
	@NonNull
	private Integer idade;
	
	@NonNull
	private String ip;
	
	private Date Cadastramento = new Date();
	
	@NonNull
	private Integer temperaturaMinimaCadastro;
	
	@NonNull
	private Integer temperaturaMaximaCadastro;
}
