package mojtaba.safaeian.infrastructure.remote;

import mojtaba.safaeian.domain.chart.*;
import org.springframework.stereotype.Component;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/27/2017.
 */

@Component
public class MockRemoteQueryRepositoryImpl implements QueryRepository {

    private static final String DIMENSION = "team";

    private static final ResultSet<Integer> firstResultSet;
    private static final ResultSet<Integer> secondResultSet;
    private static final ResultSet<Integer> thirdResultSet;

    static {
        firstResultSet = new ResultSet<Integer>(DIMENSION, "revenue")
                .addItem(new ResultSetItem<>("Real Madrid", 625))
                .addItem(new ResultSetItem<>("Barcelona", 620))
                .addItem(new ResultSetItem<>("Bayern Munich", 600))
                .addItem(new ResultSetItem<>("Liverpool", 400))
                .addItem(new ResultSetItem<>("Milan", 250));

        secondResultSet = new ResultSet<Integer>(DIMENSION, "champions")
                .addItem(new ResultSetItem<>("Real Madrid", 12))
                .addItem(new ResultSetItem<>("Barcelona", 5))
                .addItem(new ResultSetItem<>("Bayern Munich", 5))
                .addItem(new ResultSetItem<>("Liverpool", 5))
                .addItem(new ResultSetItem<>("Milan", 7));

        thirdResultSet = new ResultSet<Integer>(DIMENSION, "leagues")
                .addItem(new ResultSetItem<>("Real Madrid", 33))
                .addItem(new ResultSetItem<>("Barcelona", 24))
                .addItem(new ResultSetItem<>("Bayern Munich", 26))
                .addItem(new ResultSetItem<>("Liverpool", 18))
                .addItem(new ResultSetItem<>("Milan", 18));
    }


    @Override
    public <T> ResultSet<T> runQuery(Query query) {
        if(query.getDimension().equalsIgnoreCase("team")) {
            if (query.getMeasure().equalsIgnoreCase("revenue")) {
                return (ResultSet<T>) firstResultSet;
            } else if (query.getMeasure().equalsIgnoreCase("champions")) {
                return (ResultSet<T>) secondResultSet;
            }
            if (query.getMeasure().equalsIgnoreCase("leagues")) {
                return (ResultSet<T>) thirdResultSet;
            }
            throw new InvalidQueryException("No measure '" + query.getMeasure() + "' defined for dimension " + query.getDimension());
        }
        throw new InvalidQueryException("Wrong dimension '" + query.getDimension() + "'");
    }
}
