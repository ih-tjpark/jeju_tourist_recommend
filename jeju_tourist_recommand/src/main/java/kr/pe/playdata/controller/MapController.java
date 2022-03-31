package kr.pe.playdata.controller;

import kr.pe.playdata.domain.VisitJejuLocation;
import kr.pe.playdata.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {
    /*
        맵 컨트롤러
     */

    @Autowired
    private LocationService locationService;

    @GetMapping("/findNear")
    public List<VisitJejuLocation> findNear(
            @RequestParam String longtitude,
            @RequestParam String latitude,
            @RequestParam double distance
    ){
        Point p = new Point(Double.valueOf(longtitude), Double.valueOf(latitude));
        Distance d = new Distance(distance, Metrics.KILOMETERS);
        List<VisitJejuLocation> li = locationService.findNear(p,d);
        return li;
    }
}