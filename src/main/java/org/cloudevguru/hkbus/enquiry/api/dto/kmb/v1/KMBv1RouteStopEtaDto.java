package org.cloudevguru.hkbus.enquiry.api.dto.kmb.v1;

import java.math.BigDecimal;
import java.util.Date;

import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KMBv1RouteStopEtaDto {
	
	@JsonProperty("route")
    private String route;
	
	@JsonProperty("dir")
	private String bound;
	
	@JsonProperty("service_type")
	private String serviceType;
	
	@JsonProperty("seq")
	private String seq;

	@JsonProperty("dest_en")
	private String destinationEn;

	@JsonProperty("dest_tc")
	private String destinationTc;

	@JsonProperty("dest_sc")
	private String destinationSc;
	
	@JsonProperty("eta_seq")
	private BigDecimal etaSeq;
	
	@JsonProperty("eta")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BusEnquiryConstant.KMB_ETA_FORMAT)
	private Date eta;
	
	@JsonProperty("rmk_en")
	private String remarkEn;

	@JsonProperty("rmk_tc")
	private String remarkTc;

	@JsonProperty("rmk_sc")
	private String remarkSc;

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

	public BigDecimal getEtaSeq() {
		return etaSeq;
	}

	public void setEtaSeq(BigDecimal etaSeq) {
		this.etaSeq = etaSeq;
	}

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public String getRemarkEn() {
		return remarkEn;
	}

	public void setRemarkEn(String remarkEn) {
		this.remarkEn = remarkEn;
	}

	public String getRemarkTc() {
		return remarkTc;
	}

	public void setRemarkTc(String remarkTc) {
		this.remarkTc = remarkTc;
	}

	public String getRemarkSc() {
		return remarkSc;
	}

	public void setRemarkSc(String remarkSc) {
		this.remarkSc = remarkSc;
	}
	
}
