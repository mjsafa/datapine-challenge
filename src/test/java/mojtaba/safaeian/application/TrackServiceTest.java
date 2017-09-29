package mojtaba.safaeian.application;

import mojtaba.safaeian.IntegrationTests;
import mojtaba.safaeian.application.service.ChartService;
import mojtaba.safaeian.application.service.TrackingService;
import mojtaba.safaeian.domain.chart.ChartResult;
import mojtaba.safaeian.domain.chart.Query;
import mojtaba.safaeian.domain.tracking.TrackEventType;
import mojtaba.safaeian.domain.tracking.TrackQueryResult;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mojtaba Safaeian
 *         Created at: 9/29/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Category(IntegrationTests.class)
public class TrackServiceTest {

    @Autowired
    private ChartService chartService;

    @Autowired
    private TrackingService trackingService;


    @Test
    public void testTotalNumbers() throws InterruptedException {
        ChartResult chartResults = chartService.createChartResults(Arrays.asList(
                new Query("team", "champions"),
                new Query("team", "revenue")
        ));

        Thread.sleep(2000);

        chartService.createChartResults(Arrays.asList(
                new Query("team", "champions")
        ));

        TrackQueryResult query = trackingService.query(5, TimeUnit.SECONDS);
        assertThat(query.getTotalEvents(TrackEventType.QUERIES), is(equalTo(3)));
        assertThat(query.getTotalEvents(TrackEventType.REQUESTS), is(equalTo(2)));

        query = trackingService.query(5, TimeUnit.MINUTES);
        assertThat(query.getTotalEvents(TrackEventType.QUERIES), is(equalTo(3)));
        assertThat(query.getTotalEvents(TrackEventType.REQUESTS), is(equalTo(2)));
    }
}
