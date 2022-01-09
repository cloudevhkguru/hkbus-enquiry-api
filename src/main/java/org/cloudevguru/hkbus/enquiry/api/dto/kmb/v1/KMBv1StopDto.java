package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1StopDto {

	@JsonProperty("stop")
	private String stop;

	@JsonProperty("name_en")
	private String nameEn;

	@JsonProperty("name_tc")
	private String nameTc;

	@JsonProperty("name_sc")
	private String nameSc;

	@JsonProperty("lat")
	private String latitude;

	@JsonProperty("long")
	private String longtitude;

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameTc() {
		return nameTc;
	}

	public void setNameTc(String nameTc) {
		this.nameTc = nameTc;
	}

	public String getNameSc() {
		return nameSc;
	}

	public void setNameSc(String nameSc) {
		this.nameSc = nameSc;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

}
