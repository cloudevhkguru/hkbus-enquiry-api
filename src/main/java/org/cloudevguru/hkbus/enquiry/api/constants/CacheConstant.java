package org.cloudevguru.hkbus.enquiry.api.constants;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CacheConstant {

	// CTB & NWFB
	public static final String MANAGED_ALL_ROUTE_LIST_CACHE = "managed-all-route-list-cache";
	public static final String MANAGED_ROUTE_CACHE = "managed-route-cache";
	public static final String MANAGED_ROUTE_LIST_BY_ROUTE_CACHE = "managed-route-list-by-route-cache";
	public static final String MANAGED_STOP_CACHE = "managed-stop-cache";
	public static final String MANAGED_ROUTE_LIST_CACHE = "managed-route-list-cache";
	public static final String MANAGED_ROUTE_DETAIL_CACHE = "managed-route-detail-cache";

	public CacheConstant() {
	}

	public static final String[] getCaches() {
		return Arrays.stream(CacheConstant.class.getFields()).map(field -> {
			try {
				return field.get(field).toString();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).collect(Collectors.toList()).toArray(new String[CacheConstant.class.getDeclaredFields().length]);
	}

}
