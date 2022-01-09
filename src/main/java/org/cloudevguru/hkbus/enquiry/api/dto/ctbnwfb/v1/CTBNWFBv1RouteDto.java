package org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CTBNWFBv1RouteDto {

	@JsonProperty("co")
	private String company;

	@JsonProperty("route")
	private String route;
	
	@JsonProperty("dir")
	private String bound;

	@JsonProperty("orig_en")
	private String originEn;

	@JsonProperty("orig_tc")
	private String originTc;

	@JsonProperty("orig_sc")
	private String originSc;

	@JsonProperty("dest_en")
	private String destinationEn;

	@JsonProperty("dest_tc")
	private String destinationTc;

	@JsonProperty("dest_sc")
	private String destinationSc;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public String getOriginEn() {
		return originEn;
	}

	public void setOriginEn(String originEn) {
		this.originEn = originEn;
	}

	public String getOriginTc() {
		return originTc;
	}

	public void setOriginTc(String originTc) {
		this.originTc = originTc;
	}

	public String getOriginSc() {
		return originSc;
	}

	public void setOriginSc(String originSc) {
		this.originSc = originSc;
	}

	public String getDestinationEn() {
		return destinationEn;
	}

	public void setDestinationEn(String destinationEn) {
		this.destinationEn = destinationEn;
	}

	public String getDestinationTc() {
		return destinationTc;
	}

	public void setDestinationTc(String destinationTc) {
		this.destinationTc = destinationTc;
	}

	public String getDestinationSc() {
		return destinationSc;
	}

	public void setDestinationSc(String destinationSc) {
		this.destinationSc = destinationSc;
	}

}
