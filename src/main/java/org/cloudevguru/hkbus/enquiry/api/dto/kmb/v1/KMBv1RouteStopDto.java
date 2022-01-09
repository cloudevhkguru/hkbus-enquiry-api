package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1RouteStopDto {

	@JsonProperty("route")
	private String route;

	@JsonProperty("bound")
	private String bound;

	@JsonProperty("service_type")
	private String serviceType;

	@JsonProperty("seq")
	private String seq;

	@JsonProperty("stop")
	private String stop;

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

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
