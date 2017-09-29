package mojtaba.safaeian.domain.tracking;

import mojtaba.safaeian.domain.chart.ChartResult;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mojtaba Safaeian
 *         Created at: 9/29/2017.
 */
public class TrackQueryResultTest {

    @Test
    public void testToChart() {
        TrackQueryResult trackQueryResult = new TrackQueryResult();
        trackQueryResult.addData("1", 1, TrackEventType.QUERIES);
        trackQueryResult.addData("2", 2, TrackEventType.QUERIES);
        trackQueryResult.addData("3", 5, TrackEventType.QUERIES);
        trackQueryResult.addData("1", 1, TrackEventType.REQUESTS);
        trackQueryResult.addData("2", 2, TrackEventType.REQUESTS);

        ChartResult chart = trackQueryResult.getChart();

        assertThat(chart.getCategories(), hasItems("1", "2"));
        assertThat(chart.getSeries(), hasItem(hasProperty("name", is(equalTo("requests")))));
        assertThat(chart.getSeries(), hasItem(hasProperty("name", is(equalTo("queries")))));
        assertThat(chart.getSeries(), hasItem(hasProperty("data", hasItems(0,1,2))));
        assertThat(chart.getSeries(), hasItem(hasProperty("data", hasItems(1,2,5))));
    }

    @Test
    public void testGetTotal() {
        TrackQueryResult trackQueryResult = new TrackQueryResult()
                .addData("1", 1, TrackEventType.QUERIES)
                .addData("2", 2, TrackEventType.QUERIES)
                .addData("3", 2, TrackEventType.QUERIES)
                .addData("1", 1, TrackEventType.REQUESTS)
                .addData("2", 2, TrackEventType.REQUESTS);

        assertThat(trackQueryResult.getTotalEvents(TrackEventType.QUERIES), is(equalTo(5)));
        assertThat(trackQueryResult.getTotalEvents(TrackEventType.REQUESTS), is(equalTo(3)));
    }
}
