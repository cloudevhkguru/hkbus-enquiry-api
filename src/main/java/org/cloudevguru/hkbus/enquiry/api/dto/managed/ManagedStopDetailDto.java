package org.cloudevguru.hkbus.enquiry.api.dto.managed;

public class ManagedStopDetailDto {

	private Integer seq;
	
	private ManagedStopDto stopDto;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public ManagedStopDto getStopDto() {
		return stopDto;
	}

	public void setStopDto(ManagedStopDto stopDto) {
		this.stopDto = stopDto;
	}
		
}
