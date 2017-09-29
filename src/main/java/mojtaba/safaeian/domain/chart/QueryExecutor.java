package mojtaba.safaeian.domain.chart;

import java.util.List;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/26/2017.
 */
public interface QueryExecutor {

    <T> List<ResultSet<T>> executeQueries(List<Query> queries);
}
