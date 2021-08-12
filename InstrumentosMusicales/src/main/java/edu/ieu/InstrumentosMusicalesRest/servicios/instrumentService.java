package edu.ieu.InstrumentosMusicalesRest.servicios;

import java.util.List;

import edu.ieu.InstrumentosMusicalesRest.entities.InstrumentoMusical;

public interface instrumentService {

	InstrumentoMusical findById(Integer id);
	InstrumentoMusical findByNombre(String nombre);
    List<InstrumentoMusical> findAll(); 
    boolean isInstrumentExist(InstrumentoMusical ins);
 
    
    void saveInstrument(InstrumentoMusical ins);
  
    
    void updateInstrument(InstrumentoMusical ins);
   
    
    void deleteInstrumentById(Integer id);
}