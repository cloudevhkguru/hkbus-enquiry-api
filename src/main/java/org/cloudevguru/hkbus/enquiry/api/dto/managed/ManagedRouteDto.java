package org.cloudevguru.hkbus.enquiry.api.dto.managed;

import java.math.BigDecimal;

public class ManagedRouteDto {

	private String company;

	private String route;

	private String bound;

	private String serviceType;

	private String originEn;

	private String originTc;

	private String originSc;

	private String destinationEn;

	private String destinationTc;

	private String destinationSc;
	
	private Boolean isCircularRoute;
	
	private BigDecimal fullFare;

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

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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

	public Boolean getIsCircularRoute() {
		return isCircularRoute;
	}

	public void setIsCircularRoute(Boolean isCircularRoute) {
		this.isCircularRoute = isCircularRoute;
	}

	public BigDecimal getFullFare() {
		return fullFare;
	}

	public void setFullFare(BigDecimal fullFare) {
		this.fullFare = fullFare;
	}
	
}
