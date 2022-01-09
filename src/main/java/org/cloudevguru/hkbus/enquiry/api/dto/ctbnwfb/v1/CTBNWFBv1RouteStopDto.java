package org.cloudevguru.hkbus.enquiry.api.dto.ctbnwfb.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CTBNWFBv1RouteStopDto {

	@JsonProperty("co")
	private String company;
	
	@JsonProperty("route")
	private String route;

	@JsonProperty("dir")
	private String bound;

	@JsonProperty("seq")
	private String seq;

	@JsonProperty("stop")
	private String stop;

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

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

}
