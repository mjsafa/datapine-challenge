package mojtaba.safaeian.domain.chart;

import org.springframework.stereotype.Component;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/25/2017.
 */
public interface QueryRepository {
    <T> ResultSet<T> runQuery(Query query);
}
