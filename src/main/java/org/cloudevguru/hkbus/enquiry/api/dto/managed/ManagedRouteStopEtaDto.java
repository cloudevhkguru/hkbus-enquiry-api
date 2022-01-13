package org.cloudevguru.hkbus.enquiry.api.dto.managed;

import java.math.BigDecimal;
import java.util.Date;

import org.cloudevguru.hkbus.enquiry.api.constants.BusEnquiryConstant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagedRouteStopEtaDto {

	private String destinationEn;

	private String destinationTc;

	private String destinationSc;
	
	@JsonProperty("eta_seq")
	private BigDecimal etaSeq;
	
	@JsonProperty("eta")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BusEnquiryConstant.KMB_ETA_FORMAT)
	private Date eta;
	
	private Integer minutes;
	
	private String remarkEn;

	private String remarkTc;

	private String remarkSc;

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

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
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
