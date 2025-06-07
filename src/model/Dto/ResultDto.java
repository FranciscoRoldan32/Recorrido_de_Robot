package model.Dto;

import java.util.List;

public class ResultDto {

	public boolean pathFound;
	public long timeWithoutPrune;
	public long timeWithPrune;
	public int callsWithoutPrune;
	public int callsWithPrune;
	public List<int[]> validPath;

	public ResultDto(boolean pathFound, long timeWithoutPrune, long timeWithPrune, int callsWithoutPrune,
			int callsWithPrune, List<int[]> validPath) {
		this.pathFound = pathFound;
		this.timeWithoutPrune = timeWithoutPrune;
		this.timeWithPrune = timeWithPrune;
		this.callsWithoutPrune = callsWithoutPrune;
		this.callsWithPrune = callsWithPrune;
		this.validPath = validPath;
	}

}
