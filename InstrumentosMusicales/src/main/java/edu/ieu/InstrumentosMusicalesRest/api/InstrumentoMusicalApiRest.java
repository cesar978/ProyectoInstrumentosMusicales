package edu.ieu.InstrumentosMusicalesRest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ieu.InstrumentosMusicalesRest.entities.InstrumentoMusical;
import edu.ieu.InstrumentosMusicalesRest.servicios.instrumentService;

@RestController
@RequestMapping("/api/Instrumentos")
public class InstrumentoMusicalApiRest {


	@Autowired
	private instrumentService service; 
	
	@GetMapping
	public ResponseEntity<List<InstrumentoMusical>> listAll(){
		List<InstrumentoMusical> listaInstrumentos = service.findAll();
		if(listaInstrumentos.isEmpty()) {
			return new ResponseEntity<List<InstrumentoMusical>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InstrumentoMusical>>(listaInstrumentos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InstrumentoMusical> getInsytument(@PathVariable("id") int id) {
        System.out.println("Fetching Instrumento with id " + id);
        InstrumentoMusical ins = service.findById(id);
        if (ins == null) {
            System.out.println("Instrument with id " + id + " not found");
            return new ResponseEntity<InstrumentoMusical>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<InstrumentoMusical>(ins, HttpStatus.OK);
    }
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createInstrument(@RequestBody InstrumentoMusical ins){
		System.out.println("Creating Instrumento " + ins.getNombre());

		service.saveInstrument(ins);
		 return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<InstrumentoMusical> updateInstrument(@PathVariable("id") int id,
			@RequestBody InstrumentoMusical instrumento){
		 System.out.println("Updating Instrument " + id);
		 InstrumentoMusical insbd = service.findById(id);
		 if(insbd == null) {
			 return new ResponseEntity<InstrumentoMusical>(HttpStatus.NOT_FOUND);
		 }
		 insbd.setNombre( instrumento.getNombre() );
		 insbd.setTipo( instrumento.getTipo() );
		 insbd.setPrecio( instrumento.getPrecio() );
		 insbd.setEn_existencia( instrumento.getEn_existencia() );
		
		 service.updateInstrument(insbd);
		 return new ResponseEntity<InstrumentoMusical>(insbd, HttpStatus.OK );
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteInstrument(@PathVariable("id") int id){
		System.out.println("Fetching & Deleting Instrument with id " + id);
		InstrumentoMusical Instrument = service.findById(id);
		if(Instrument == null) {
			 System.out.println("Unable to delete. Instrument with id " + id + " not found");
			 return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); // 404
		}
		service.deleteInstrumentById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // 204 http
	}
}