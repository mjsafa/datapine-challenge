package mojtaba.safaeian.application.service;

import mojtaba.safaeian.domain.chart.Chart;
import mojtaba.safaeian.domain.chart.ChartResult;
import mojtaba.safaeian.domain.chart.Query;
import mojtaba.safaeian.domain.chart.QueryExecutor;
import mojtaba.safaeian.domain.tracking.TrackEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/25/2017.
 */
@Service
public class ChartService {

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private TrackingService trackingService;

    public ChartResult createChartResults(List<Query>  queries){
        trackingService.trackUsage(TrackEventType.REQUESTS);
        Chart chart = new Chart(queries);
        return chart.execute(queryExecutor);
    }

}
