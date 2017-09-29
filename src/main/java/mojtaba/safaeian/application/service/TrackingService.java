package mojtaba.safaeian.application.service;

import mojtaba.safaeian.domain.tracking.TrackEventType;
import mojtaba.safaeian.domain.tracking.TrackQueryResult;
import mojtaba.safaeian.domain.tracking.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/25/2017.
 */
@Service
public class TrackingService {

    @Autowired
    private TrackingRepository trackingRepository;

    public TrackQueryResult query(int last, TimeUnit timeUnit){
        return trackingRepository.query(last, timeUnit);
    }

    public void trackUsage(TrackEventType trackEventType){
        trackingRepository.trackEvent(trackEventType);
    }
}
