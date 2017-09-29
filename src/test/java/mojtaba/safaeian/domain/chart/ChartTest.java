package mojtaba.safaeian.domain.chart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/26/2017.
 */

@RunWith(SpringRunner.class)
public class ChartTest {

    @MockBean
    private QueryExecutor queryExecutor;


    @Before
    public void setup() {
        given(queryExecutor.executeQueries(any()))
                .willReturn(Arrays.asList(
                        new ResultSet<Object>("team", "revenue")
                                .addItem(new ResultSetItem<>("team1", 100))
                                .addItem(new ResultSetItem<>("team2", 200))));
    }

    @Test
    public void testConvertToChartResults() {
        Chart chart = new Chart(Arrays.asList(new Query("team", "revenue")));
        ChartResult chartResult = chart.execute(queryExecutor);
        assertThat(chartResult.getCategories().size(), is(equalTo(2)));
        assertThat(chartResult.getCategories(), hasItem("team1"));
        assertThat(chartResult.getCategories(), hasItem("team2"));
        assertThat(chartResult.getSeries(), hasItem(hasProperty("name", equalTo("revenue"))));
        assertThat(chartResult.getSeries(), hasItem(hasProperty("data", hasItem(100))));
        assertThat(chartResult.getSeries(), hasItem(hasProperty("data", hasItem(200))));
    }
}
