package mojtaba.safaeian.domain.chart;

import java.util.List;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/26/2017.
 */
public class ChartSeriesItem {
    private String name;
    private List<Integer> data;

    public ChartSeriesItem(String name, List<Integer> data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getData() {
        return data;
    }
}
