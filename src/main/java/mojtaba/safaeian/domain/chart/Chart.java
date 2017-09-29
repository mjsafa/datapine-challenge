package mojtaba.safaeian.domain.chart;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/26/2017.
 */
public class Chart {


    private List<Query> queries;

    public Chart(List<Query> queries) {
        this.queries = queries;
    }

    public ChartResult execute(QueryExecutor queryExecutor) {
        List<ResultSet<Integer>> resultSets = queryExecutor.executeQueries(queries);
        List<String> categories = resultSets.stream()
                .map(resultSet -> {
                    return resultSet.getItems().stream()
                            .map(ResultSetItem::getDimension)
                            .collect(Collectors.toList());
                })
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        List<ChartSeriesItem> chartSeries = resultSets.stream()
                .map(resultSet -> {
                    return new ChartSeriesItem(resultSet.getMeasure(),
                            resultSet.getItems().stream().map(ResultSetItem<Integer>::getValue).collect(Collectors.toList()));
                }).collect(Collectors.toList());
        return new ChartResult(categories, chartSeries);
    }
}
