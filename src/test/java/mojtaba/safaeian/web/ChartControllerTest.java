package mojtaba.safaeian.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mojtaba Safaeian
 *         Created at: 9/28/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetChartWithNoDimension() throws Exception {
        mockMvc.perform(
                get("/chart")
                        .param("dimensions", "")
                        .param("measures", "revenue")
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    public void testGetChartWithTwoDimensions() throws Exception {
        mockMvc.perform(
                get("/chart")
                        .param("dimensions", "team", "team1")
                        .param("measures", "revenue")
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    public void testGetChartWithNoMeasure() throws Exception {
        mockMvc.perform(
                get("/chart")
                        .param("dimensions", "team")
                        .param("measures", "")
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    public void testGetChart() throws Exception {
        mockMvc.perform(
                get("/chart")
                        .param("dimensions", "team")
                        .param("measures", "revenue")
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void testInvalidDimensionReturnBadRequest() throws Exception {
        mockMvc.perform(
                get("/chart")
                        .param("dimensions", "someInvalidDimension")
                        .param("measures", "revenue")
        ).andExpect(
                status().isBadRequest()
        );
    }

    @Test
    public void testWithNoParam() throws Exception {
        mockMvc.perform(
                get("/chart")
        ).andExpect(
                status().isBadRequest()
        );
    }
}
