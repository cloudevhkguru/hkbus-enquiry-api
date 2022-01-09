package org.cloudevguru.hkbus.enquiry.api.dto.managed;

import java.util.List;

public class ManagedRouteDetailDto extends ManagedRouteDto {

	private List<ManagedStopDetailDto> stopDetailDtos;

	public List<ManagedStopDetailDto> getStopDetailDtos() {
		return stopDetailDtos;
	}

	public void setStopDetailDtos(List<ManagedStopDetailDto> stopDetailDtos) {
		this.stopDetailDtos = stopDetailDtos;
	}

}
