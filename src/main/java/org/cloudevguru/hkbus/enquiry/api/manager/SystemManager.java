package org.cloudevguru.hkbus.enquiry.api.manager;

import java.util.Date;

import org.cloudevguru.hkbus.enquiry.api.service.ConcurrentHashMapService;
import org.cloudevguru.hkbus.enquiry.api.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemManager {
	
	@Value("${preload.route.characters}")
	private String preloadRouteCharacters;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private ManagedManager managedManager;
	
	@Autowired
	private ConcurrentHashMapService concurrentHashMapService;

	@Scheduled(cron="${evict.cache.cron}")
	public void cacheAllEvict() {
		concurrentHashMapService.cleanRawRouteChm();
		concurrentHashMapService.cleanRouteListChm();
		concurrentHashMapService.cleanRouteDetailChm();
		concurrentHashMapService.cleanRouteFareChm();
	}
	
	@Async
	@Scheduled(cron = "${preload.route.cron}")
	public void preloadRouteList() {
		if(concurrentHashMapService.isEmptyRouteListChm()) {
			System.out.println("Start preloading route list at " + new Date());
			String[] busRouteStartCharacter= preloadRouteCharacters.split(",");
			for(String character : busRouteStartCharacter) {
				System.out.println("Start preloading " + character + " at " + new Date());
				managedManager.getRouteByRoute(character);
				System.out.println("Completed preloading " + character + " at " + new Date());
			}
			for (int i = 1; i < 10; i++) {
				System.out.println("Start preloading " + String.valueOf(i) + " at " + new Date());
				managedManager.getRouteByRoute(String.valueOf(i));
				System.out.println("Completed preloading " + String.valueOf(i) + " at " + new Date());
			}
			System.out.println("Completed preloading route list at " + new Date());
		}else{
			System.out.println("Route List is not empty at " + new Date());	
		}
	}

}
