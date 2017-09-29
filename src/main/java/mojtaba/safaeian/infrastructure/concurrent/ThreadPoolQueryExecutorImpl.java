package mojtaba.safaeian.infrastructure.concurrent;

import mojtaba.safaeian.SystemException;
import mojtaba.safaeian.application.service.TrackingService;
import mojtaba.safaeian.domain.ApplicationException;
import mojtaba.safaeian.domain.chart.Query;
import mojtaba.safaeian.domain.chart.QueryExecutor;
import mojtaba.safaeian.domain.chart.QueryRepository;
import mojtaba.safaeian.domain.chart.ResultSet;
import mojtaba.safaeian.domain.tracking.TrackEventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/27/2017.
 */

@Component
public class ThreadPoolQueryExecutorImpl implements QueryExecutor {

    private static final int QUERY_EXECUTION_TIMEOUT_MILLISECONDS = 2000;

    private ExecutorService executorService;

    private final QueryRepository queryRepository;

    private final TrackingService trackingService;

    @Autowired
    public ThreadPoolQueryExecutorImpl(QueryRepository queryRepository,
                                       TrackingService trackingService,
                                       @Value("${query.executor.threads}") Integer threadNumber) {
        this.queryRepository = queryRepository;
        this.trackingService = trackingService;
        executorService = Executors.newFixedThreadPool(threadNumber);
    }


    @Override
    public <T> List<ResultSet<T>> executeQueries(List<Query> queries) {
        List<Callable<ResultSet<T>>> taskList = queries.stream()
                .map((Query query) -> (Callable<ResultSet<T>>) (() -> queryRepository.runQuery(query)))
                .collect(Collectors.toList());
        try {
            List<Future<ResultSet<T>>> futures = executorService.invokeAll(taskList, QUERY_EXECUTION_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS);
            return futures.stream()
                    .map(future -> {
                        try {
                            trackingService.trackUsage(TrackEventType.QUERIES);
                            return future.get();
                        } catch (InterruptedException e) {
                            throw new SystemException("executing one of queries interrupted: " + e.getMessage(), e);
                        } catch (ExecutionException e) {
                            throw new ApplicationException("problem while executing one of queries: " + e.getCause().getMessage(), e.getCause());
                        }
                    })
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new SystemException("Executing queries has been timed out");
        }
    }

}
