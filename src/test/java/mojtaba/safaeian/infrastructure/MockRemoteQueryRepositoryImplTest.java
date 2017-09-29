package mojtaba.safaeian.infrastructure;

import mojtaba.safaeian.domain.chart.Query;
import mojtaba.safaeian.domain.chart.ResultSet;
import mojtaba.safaeian.domain.chart.InvalidQueryException;
import mojtaba.safaeian.infrastructure.remote.MockRemoteQueryRepositoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


/**
 * @author Mojtaba Safaeian
 *         Created at: 09/27/2017.
 */

public class MockRemoteQueryRepositoryImplTest {

    private static MockRemoteQueryRepositoryImpl queryRepository;

    @BeforeClass
    public static void setup() {
        queryRepository = new MockRemoteQueryRepositoryImpl();
    }

    @Test
    public void testRunQuery() {
        Query query = new Query("team", "revenue");
        ResultSet<Integer> resultSet = queryRepository.runQuery(query);
        assertThat(resultSet.getMeasure(), is(equalTo("revenue")));
        assertThat(resultSet.getItems().size(), is(equalTo(5)));
    }

    @Test(expected = InvalidQueryException.class)
    public void testInvalidDimension() {
        Query query = new Query("wrong-dimension", "revenue");
        queryRepository.runQuery(query);
    }

    @Test(expected = InvalidQueryException.class)
    public void testInvalidMeasure() {
        Query query = new Query("team", "wrong-measure");
        queryRepository.runQuery(query);
    }
}
