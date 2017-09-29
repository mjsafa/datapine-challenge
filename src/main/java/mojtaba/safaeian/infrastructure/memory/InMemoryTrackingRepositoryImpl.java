package mojtaba.safaeian.infrastructure.memory;

import mojtaba.safaeian.SystemException;
import mojtaba.safaeian.domain.tracking.TrackEventType;
import mojtaba.safaeian.domain.tracking.TrackQueryResult;
import mojtaba.safaeian.domain.tracking.TrackingRepository;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Mojtaba Safaeian
 *         Created at: 9/29/2017.
 */

@Component
public class InMemoryTrackingRepositoryImpl implements TrackingRepository {

    private static ConcurrentHashMap<String, Map<TrackEventType, Integer>> secondsHashMap, minutesHashMap;

    public InMemoryTrackingRepositoryImpl() {
        this.clearAll();
    }

    @Override
    public void clearAll() {
        secondsHashMap = new ConcurrentHashMap<>();
        minutesHashMap = new ConcurrentHashMap<>();
    }

    @Override
    public void trackEvent(TrackEventType eventType) {
        //index event in maps
        String lastSecond = getLastTimes(TimeUnit.SECONDS, 1).get(0);
        String lastMinute = getLastTimes(TimeUnit.MINUTES, 1).get(0);

        //indexing second
        Map<TrackEventType, Integer> secondValue = secondsHashMap.get(lastSecond);
        if (null != secondValue) {
            secondValue.merge(eventType, 1, (a, b) -> a + b);
        } else {
            secondValue = new ConcurrentHashMap<>();
            secondValue.put(eventType, 1);
        }
        secondsHashMap.put(lastSecond, secondValue);

        //indexing Minute
        Map<TrackEventType, Integer> minuteValue = minutesHashMap.get(lastMinute);
        if (null != minuteValue) {
            minuteValue.merge(eventType, 1, (a, b) -> a + b);
        } else {
            minuteValue = new ConcurrentHashMap<>();
            minuteValue.put(eventType, 1);
        }
        minutesHashMap.put(lastMinute, minuteValue);
    }

    @Override
    public TrackQueryResult query(int last, TimeUnit timeUnit) {
        List<String> lastTimes = getLastTimes(timeUnit, last);
        ConcurrentHashMap<String, Map<TrackEventType, Integer>> map = timeUnit == TimeUnit.SECONDS ? secondsHashMap : minutesHashMap;
        TrackQueryResult trackQueryResult = new TrackQueryResult();
        lastTimes.stream().forEach(timeKey -> {
            if (null != map.get(timeKey)) {
                trackQueryResult.addData(timeKey,
                        null != map.get(timeKey).get(TrackEventType.REQUESTS) ? map.get(timeKey).get(TrackEventType.REQUESTS) : 0,
                        TrackEventType.REQUESTS);

                trackQueryResult.addData(timeKey,
                        null != map.get(timeKey).get(TrackEventType.QUERIES) ? map.get(timeKey).get(TrackEventType.QUERIES) : 0,
                        TrackEventType.QUERIES);
            }else {
                trackQueryResult.addData(timeKey, 0, TrackEventType.REQUESTS);
                trackQueryResult.addData(timeKey, 0, TrackEventType.QUERIES);
            }
        });

        return trackQueryResult;
    }

    private List<String> getLastTimes(TimeUnit timeUnit, int count) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(timeUnit == TimeUnit.SECONDS ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd HH:mm");

        try {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(dateFormat.parse(dateFormat.format(new Date())));
            List<String> result = new ArrayList<>();
            result.add(dateFormat.format(calendar.getTime()));
            for (int i = 1; i < count; i++) {
                calendar.add((timeUnit == TimeUnit.SECONDS) ? Calendar.SECOND : Calendar.MINUTE, -1);
                result.add(0, dateFormat.format(calendar.getTime()));
            }
            return result;
        } catch (ParseException e) {
            throw new SystemException("some problem while running tracking query");
        }
    }
}
