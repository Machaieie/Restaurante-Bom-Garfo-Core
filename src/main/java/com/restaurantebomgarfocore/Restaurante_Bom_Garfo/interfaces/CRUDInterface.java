package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.interfaces;

import java.util.List;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.ResourceNotFoundException;

/**
 * Interface genérica para operações CRUD (Create, Read, Update, Delete).
 * 
 * @param <T> Tipo da entidade.
 * @param <ID> Tipo do identificador da entidade.
 */
public interface CRUDInterface<T, ID> {

    /**
     * Salva a entidade fornecida.
     *
     * @param entity Entidade a ser salva.
     * @return A entidade salva.
     * @throws IllegalArgumentException se a entidade for inválida.
     * @throws ResourceNotFoundException quando a entidade procurada não é encontrada
     */
    T save(T entity) throws IllegalArgumentException;

    /**
     * Encontra uma entidade pelo seu identificador.
     *
     * @param id Identificador da entidade.
     * @return A entidade encontrada, ou {@code null} se não for encontrada.
     * @throws IllegalArgumentException se o identificador for inválido.
     */
    T findById(ID id) throws ResourceNotFoundException;

    /**
     * Retorna todas as entidades.
     *
     * @return Uma lista de todas as entidades.
     */
    List<T> findAll();

    /**
     * Remove a entidade com o identificador fornecido.
     *
     * @param id Identificador da entidade a ser removida.
     * @throws IllegalArgumentException se o identificador for inválido.
     */
    void deleteById(ID id) throws ResourceNotFoundException;
}
