package edu.ieu.InstrumentosMusicalesRest.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ieu.InstrumentosMusicalesRest.entities.InstrumentoMusical;
import edu.ieu.InstrumentosMusicalesRest.repositorios.InstrumentoMusicalRepository;


@Service
public class InstrumentSeviceImp implements instrumentService {

	@Autowired
	private InstrumentoMusicalRepository insDao;

	
	@Override
	public InstrumentoMusical findById(Integer id) {
		return insDao.findById(id)
				.orElse(null);
	
	}

	@Override
	public InstrumentoMusical findByNombre(String nombre) {
		return insDao.findByNombre(nombre);
	
	}

	@Override
	public List<InstrumentoMusical> findAll() {
		List<InstrumentoMusical> lista = new ArrayList<>();
		insDao.findAll()
			.forEach( lista::add );
		return lista;
	}

	@Override
	public boolean isInstrumentExist(InstrumentoMusical ins) {
		return insDao.existsById(ins.getId() );
	}

	//guardar
	@Override @Transactional
	public void saveInstrument(InstrumentoMusical cel) {
		insDao.save(cel);
		
	}

	@Override @Transactional
	public void updateInstrument(InstrumentoMusical ins) {
		InstrumentoMusical insdb = insDao.findById(ins.getId() ).orElse(null);
		if(insdb != null) {
			insdb.setNombre( ins.getNombre() );//
			insdb.setTipo( ins.getTipo() );//
			insdb.setPrecio( ins.getPrecio() );		//	
			insdb.setEn_existencia( ins.getEn_existencia() );//
		
			
			insDao.save(insdb);
		}
	}

	@Override @Transactional
	public void deleteInstrumentById(Integer id) {
		insDao.deleteById(  id );	
	}
}