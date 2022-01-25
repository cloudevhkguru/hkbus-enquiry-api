package org.cloudevguru.hkbus.enquiry.api.constants;

public class BusEnquiryConstant {
	
	public static final String KMB_ETA_FORMAT ="yyyy-MM-dd'T'HH:mm:ssX";
	
	public enum RequestTypeEum {
		ROUTE("Route"),
		ROUTELIST("RouteList"),
		STOP("Stop"),
		STOPLIST("StopList");

		private String value;

		private RequestTypeEum() {
		}

		private RequestTypeEum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public enum BusCompanyEum {
		KMB("kmb"),
		NWFB("nwfb"),
		CTB("ctb"),
		LWB("lwb");

		private String value;

		private BusCompanyEum() {
		}

		private BusCompanyEum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public enum DirectionShortEum {
		INBOUND("I"), OUTBOUND("O");

		private String value;

		private DirectionShortEum() {
		}

		private DirectionShortEum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	public enum DirectionFullEum {
		INBOUND("inbound"), OUTBOUND("outbound");

		private String value;

		private DirectionFullEum() {
		}

		private DirectionFullEum(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
