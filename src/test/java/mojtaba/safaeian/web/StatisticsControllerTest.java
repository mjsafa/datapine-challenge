package mojtaba.safaeian.web;

import mojtaba.safaeian.application.service.TrackingService;
import mojtaba.safaeian.domain.tracking.TrackQueryResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mojtaba Safaeian
 *         Created at: 9/29/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackingService trackingService;

    @Before
    public void setup(){
        given(trackingService.query(anyInt(), any()))
                .willReturn(new TrackQueryResult());
    }

    @Test
    public void testWithWrongTimeUnit() throws Exception {
        mockMvc.perform(
                get("/statistics")
                        .param("last", "5")
                        .param("timeUnit", "invalidTimeUnit")
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    public void testWithCorrectParams() throws Exception {
        mockMvc.perform(
                get("/statistics")
                        .param("last", "5")
                        .param("timeUnit", "seconds")
        ).andExpect(
                status().isOk()
        );
    }
}
