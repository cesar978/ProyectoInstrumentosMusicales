package edu.ieu.InstrumentosMusicalesRest.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.ieu.InstrumentosMusicalesRest.entities.InstrumentoMusical;

public interface InstrumentoMusicalRepository extends CrudRepository<InstrumentoMusical, Integer>{

	@Query("SELECT ins FROM InstrumentoMusical ins WHERE ins.nombre = ?1  ")

	public InstrumentoMusical findByNombre(String nombre);
}