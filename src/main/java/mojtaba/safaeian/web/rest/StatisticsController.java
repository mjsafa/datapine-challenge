package mojtaba.safaeian.web.rest;

import mojtaba.safaeian.application.service.TrackingService;
import mojtaba.safaeian.domain.tracking.TrackEventType;
import mojtaba.safaeian.domain.tracking.TrackQueryResult;
import mojtaba.safaeian.web.dto.StatisticsRequest;
import mojtaba.safaeian.web.dto.StatisticsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/25/2017.
 */

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping
    public StatisticsResponse getResults(@Valid StatisticsRequest statisticsRequest) {
        TrackQueryResult trackQueryResult = trackingService.query(statisticsRequest.getLast(), TimeUnit.valueOf(statisticsRequest.getTimeUnit().toUpperCase()));
        return new StatisticsResponse(trackQueryResult.getTotalEvents(TrackEventType.REQUESTS),
                trackQueryResult.getTotalEvents(TrackEventType.QUERIES),
                trackQueryResult.getChart());
    }
}
