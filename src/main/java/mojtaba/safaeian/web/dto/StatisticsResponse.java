package mojtaba.safaeian.web.dto;

import mojtaba.safaeian.domain.chart.ChartResult;

/**
 * @author Mojtaba Safaeian
 *         Created at: 9/29/2017.
 */
public class StatisticsResponse {

    private int totalRequests;
    private int totalQueries;
    private ChartResult chart;

    public StatisticsResponse(int totalRequests, int totalQueries, ChartResult chart) {
        this.totalRequests = totalRequests;
        this.totalQueries = totalQueries;
        this.chart = chart;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public int getTotalQueries() {
        return totalQueries;
    }

    public ChartResult getChart() {
        return chart;
    }
}
