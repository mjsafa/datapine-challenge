package mojtaba.safaeian.domain.tracking;

import mojtaba.safaeian.domain.chart.ChartResult;
import mojtaba.safaeian.domain.chart.ChartSeriesItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Mojtaba Safaeian
 *         Created at: 9/29/2017.
 */
public class TrackQueryResult {

    private Map<String, Map<TrackEventType, Integer>> data;

    public TrackQueryResult() {
        this.data = new ConcurrentHashMap<>();
    }

    public TrackQueryResult addData(String key, int value, TrackEventType eventType) {
        Map<TrackEventType, Integer> newData = new ConcurrentHashMap<>();
        newData.put(eventType, value);
        data.merge(key, newData, (oldData, newMap) -> {
            oldData.put(eventType, newMap.get(eventType));
            return oldData;
        });

        return this;
    }

    public Map<String, Map<TrackEventType, Integer>> getData() {
        return data;
    }

    public int getTotalEvents(TrackEventType eventType) {
        return data.values().stream()
                .map(item -> {
                    return item.get(eventType) != null ? item.get(eventType).intValue() : 0;
                })
                .mapToInt(Integer::intValue).sum();
    }

    public ChartResult getChart() {
        List<ChartSeriesItem> series = new ArrayList<>();
        List<String> categories = data.keySet().stream()
                .sorted()
                .collect(Collectors.toList());

        for (TrackEventType trackEventType : TrackEventType.values()) {
            ChartSeriesItem seriesItem = new ChartSeriesItem(trackEventType.name().toLowerCase(),
                    categories.stream()
                            .map(category -> {
                                return data.get(category).get(trackEventType) != null ? data.get(category).get(trackEventType) : 0;
                            })
                            .collect(Collectors.toList())
            );

            series.add(seriesItem);
        }

        return new ChartResult(categories, series);
    }
}
