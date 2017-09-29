package mojtaba.safaeian.domain.tracking;

import java.util.concurrent.TimeUnit;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/25/2017.
 */
public interface TrackingRepository {

    void clearAll();

    void trackEvent(TrackEventType eventType);

    TrackQueryResult query(int last, TimeUnit timeUnit);
}
