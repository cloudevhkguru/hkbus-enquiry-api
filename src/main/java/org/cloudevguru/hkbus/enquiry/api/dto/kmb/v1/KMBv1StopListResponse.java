package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1StopListResponse {

	@JsonProperty("type")
	private String type;

	@JsonProperty("version")
	private String version;

	@JsonProperty("generated_timestamp")
	private String generatedTimestamp;

	@JsonProperty("data")
	private List<KMBv1StopDto> dtos;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getGeneratedTimestamp() {
		return generatedTimestamp;
	}

	public void setGeneratedTimestamp(String generatedTimestamp) {
		this.generatedTimestamp = generatedTimestamp;
	}

	public List<KMBv1StopDto> getDtos() {
		return dtos;
	}

	public void setDtos(List<KMBv1StopDto> dtos) {
		this.dtos = dtos;
	}

}
