package mojtaba.safaeian.infrastructure;

import mojtaba.safaeian.domain.tracking.TrackEventType;
import mojtaba.safaeian.domain.tracking.TrackQueryResult;
import mojtaba.safaeian.domain.tracking.TrackingRepository;
import mojtaba.safaeian.infrastructure.memory.InMemoryTrackingRepositoryImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mojtaba Safaeian
 *         Created at: 9/29/2017.
 */
public class InMemoryTrackingRepositoryTest {

    private static TrackingRepository trackingRepository;

    @BeforeClass
    public static void setup(){
        trackingRepository = new InMemoryTrackingRepositoryImpl();
    }

    @Before
    public void setupRepository(){
        trackingRepository.clearAll();
        trackingRepository.trackEvent(TrackEventType.REQUESTS);
        trackingRepository.trackEvent(TrackEventType.QUERIES);
        trackingRepository.trackEvent(TrackEventType.REQUESTS);
    }

    @Test
    public void testQueryBySeconds(){
        TrackQueryResult queryResult = trackingRepository.query(5, TimeUnit.SECONDS);
        assertThat(queryResult.getTotalEvents(TrackEventType.QUERIES), is(equalTo(1)));
        assertThat(queryResult.getTotalEvents(TrackEventType.REQUESTS), is(equalTo(2)));
    }


    @Test
    public void testQueryByMinutes(){
        TrackQueryResult queryResult = trackingRepository.query(5, TimeUnit.MINUTES);
        assertThat(queryResult.getTotalEvents(TrackEventType.QUERIES), is(equalTo(1)));
        assertThat(queryResult.getTotalEvents(TrackEventType.REQUESTS), is(equalTo(2)));
    }



}
