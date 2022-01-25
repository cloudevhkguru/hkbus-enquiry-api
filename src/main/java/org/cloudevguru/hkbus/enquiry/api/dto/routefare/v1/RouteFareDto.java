package org.cloudevguru.hkbus.enquiry.api.dto.routefare.v1;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteFareDto implements Cloneable {
	
	@JsonProperty("routeId")
	private String routeId;

	@JsonProperty("companyCode")
	private String company;

	@JsonProperty("district")
	private String district;

	@JsonProperty("routeNameC")
	private String routeNameTc;

	@JsonProperty("routeNameS")
	private String routeNameSc;

	@JsonProperty("routeNameE")
	private String routeNameEn;

	@JsonProperty("routeType")
	private String routeType;

	@JsonProperty("serviceMode")
	private String serviceMode;

	@JsonProperty("journeyTime")
	private Integer journeyTime;

	@JsonProperty("locStartNameC")
	private String originTc;

	@JsonProperty("locStartNameS")
	private String originSc;

	@JsonProperty("locStartNameE")
	private String originEn;

	@JsonProperty("locEndNameC")
	private String destinationTc;

	@JsonProperty("locEndNameS")
	private String destinationSc;

	@JsonProperty("locEndNameE")
	private String destinationEn;

	@JsonProperty("hyperlinkC")
	private String hyperlinkTc;

	@JsonProperty("hyperlinkS")
	private String hyperlinkSc;

	@JsonProperty("hyperlinkE")
	private String hyperlinkEn;

	@JsonProperty("fullFare")
	private BigDecimal fullFare;

	@JsonProperty("routeRemarkC")
	private String routeRemarkTc;

	@JsonProperty("routeRemarkS")
	private String routeRemarkSc;

	@JsonProperty("routeRemarkE")
	private String routeRemarkEn;

	@JsonProperty("lastUpdateDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date lastUpdateDate;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getRouteNameTc() {
		return routeNameTc;
	}

	public void setRouteNameTc(String routeNameTc) {
		this.routeNameTc = routeNameTc;
	}

	public String getRouteNameSc() {
		return routeNameSc;
	}

	public void setRouteNameSc(String routeNameSc) {
		this.routeNameSc = routeNameSc;
	}

	public String getRouteNameEn() {
		return routeNameEn;
	}

	public void setRouteNameEn(String routeNameEn) {
		this.routeNameEn = routeNameEn;
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public String getServiceMode() {
		return serviceMode;
	}

	public void setServiceMode(String serviceMode) {
		this.serviceMode = serviceMode;
	}

	public Integer getJourneyTime() {
		return journeyTime;
	}

	public void setJourneyTime(Integer journeyTime) {
		this.journeyTime = journeyTime;
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

	public String getOriginEn() {
		return originEn;
	}

	public void setOriginEn(String originEn) {
		this.originEn = originEn;
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

	public String getDestinationEn() {
		return destinationEn;
	}

	public void setDestinationEn(String destinationEn) {
		this.destinationEn = destinationEn;
	}

	public String getHyperlinkTc() {
		return hyperlinkTc;
	}

	public void setHyperlinkTc(String hyperlinkTc) {
		this.hyperlinkTc = hyperlinkTc;
	}

	public String getHyperlinkSc() {
		return hyperlinkSc;
	}

	public void setHyperlinkSc(String hyperlinkSc) {
		this.hyperlinkSc = hyperlinkSc;
	}

	public String getHyperlinkEn() {
		return hyperlinkEn;
	}

	public void setHyperlinkEn(String hyperlinkEn) {
		this.hyperlinkEn = hyperlinkEn;
	}

	public BigDecimal getFullFare() {
		return fullFare;
	}

	public void setFullFare(BigDecimal fullFare) {
		this.fullFare = fullFare;
	}

	public String getRouteRemarkTc() {
		return routeRemarkTc;
	}

	public void setRouteRemarkTc(String routeRemarkTc) {
		this.routeRemarkTc = routeRemarkTc;
	}

	public String getRouteRemarkSc() {
		return routeRemarkSc;
	}

	public void setRouteRemarkSc(String routeRemarkSc) {
		this.routeRemarkSc = routeRemarkSc;
	}

	public String getRouteRemarkEn() {
		return routeRemarkEn;
	}

	public void setRouteRemarkEn(String routeRemarkEn) {
		this.routeRemarkEn = routeRemarkEn;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}
