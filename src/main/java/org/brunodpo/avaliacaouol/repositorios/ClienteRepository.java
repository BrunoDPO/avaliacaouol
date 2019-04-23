package org.brunodpo.avaliacaouol.repositorios;

import org.springframework.data.repository.CrudRepository;

import org.brunodpo.avaliacaouol.entidades.Cliente;

/**
 * Repositório básico de Clientes
 * @author brunodpo
 * @since 2019-04-21
 */
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
