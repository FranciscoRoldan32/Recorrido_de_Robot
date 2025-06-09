package model.Dto;

import java.util.List;
import java.util.Collections;

public class ResultDto {

	public boolean pathFound;
	public long timeWithoutPrune;
	public long timeWithPrune;
	public int callsWithoutPrune;
	public int callsWithPrune;
	public List<int[]> validPath;
	
	// Nuevos campos para el Algoritmo Genético
	public boolean gaPathFound;
	public long gaTime;
	public List<int[]> gaValidPath;

	public ResultDto(boolean pathFound, long timeWithoutPrune, long timeWithPrune, int callsWithoutPrune,
			int callsWithPrune, List<int[]> validPath) {
		this.pathFound = pathFound;
		this.timeWithoutPrune = timeWithoutPrune;
		this.timeWithPrune = timeWithPrune;
		this.callsWithoutPrune = callsWithoutPrune;
		this.callsWithPrune = callsWithPrune;
		this.validPath = validPath;
		
		// Inicializar campos del AG a valores por defecto
		this.gaPathFound = false;
		this.gaTime = 0;
		this.gaValidPath = Collections.emptyList(); // Asegurar que no sea null
	}
	
	// Constructor adicional para resultados del algoritmo genético
    public ResultDto(boolean gaPathFound, long gaTime, List<int[]> gaValidPath) {
        this.pathFound = false; // Por defecto para AG
        this.timeWithoutPrune = 0;
        this.timeWithPrune = 0;
        this.callsWithoutPrune = 0;
        this.callsWithPrune = 0;
        this.validPath = Collections.emptyList(); // Por defecto para AG
        
        this.gaPathFound = gaPathFound;
        this.gaTime = gaTime;
        this.gaValidPath = gaValidPath;
    }
    
    // Constructor combinado para resultados de ambos algoritmos
    public ResultDto(boolean pathFound, long timeWithoutPrune, long timeWithPrune, int callsWithoutPrune,
                     int callsWithPrune, List<int[]> validPath, boolean gaPathFound, long gaTime, List<int[]> gaValidPath) {
        this.pathFound = pathFound;
        this.timeWithoutPrune = timeWithoutPrune;
        this.timeWithPrune = timeWithPrune;
        this.callsWithoutPrune = callsWithoutPrune;
        this.callsWithPrune = callsWithPrune;
        this.validPath = validPath;
        this.gaPathFound = gaPathFound;
        this.gaTime = gaTime;
        this.gaValidPath = gaValidPath;
    }

} 