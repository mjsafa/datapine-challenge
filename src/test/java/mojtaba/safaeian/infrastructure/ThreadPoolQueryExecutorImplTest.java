package mojtaba.safaeian.infrastructure;

import mojtaba.safaeian.application.service.TrackingService;
import mojtaba.safaeian.domain.chart.InvalidQueryException;
import mojtaba.safaeian.domain.chart.Query;
import mojtaba.safaeian.domain.chart.ResultSet;
import mojtaba.safaeian.infrastructure.concurrent.ThreadPoolQueryExecutorImpl;
import mojtaba.safaeian.infrastructure.remote.MockRemoteQueryRepositoryImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/27/2017.
 */

public class ThreadPoolQueryExecutorImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private TrackingService trackingService;

    private static ThreadPoolQueryExecutorImpl threadPoolQueryExecutor;


    @Before
    public void setup() {
        threadPoolQueryExecutor = new ThreadPoolQueryExecutorImpl(new MockRemoteQueryRepositoryImpl(), trackingService);
    }

    @Test
    public void testMultipleQueries() {
        List<Query> queries = Arrays.asList(new Query("team", "revenue"),
                new Query("team", "champions"),
                new Query("team", "leagues"));

        List<ResultSet<Object>> resultSets = threadPoolQueryExecutor.executeQueries(queries);
        assertThat(resultSets.size(), is(equalTo(3)));
        assertThat(resultSets.get(0).getItems().get(0).getDimension(), is(equalTo("Real Madrid")));
    }


    @Test
    public void testInvalidQuery() {
        expectedException.expectCause(isA(InvalidQueryException.class));

        List<Query> queries = Arrays.asList(new Query("wrong-dimension", "revenue"));
        threadPoolQueryExecutor.executeQueries(queries);
    }
}
