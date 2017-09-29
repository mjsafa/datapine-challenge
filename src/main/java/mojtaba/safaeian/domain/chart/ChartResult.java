package mojtaba.safaeian.domain.chart;

import java.util.List;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/26/2017.
 */
public class ChartResult {

    private List<String> categories;
    private List<ChartSeriesItem> series;

    public ChartResult(List<String> categories, List<ChartSeriesItem> series) {
        this.categories = categories;
        this.series = series;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<ChartSeriesItem> getSeries() {
        return series;
    }

    public void setSeries(List<ChartSeriesItem> series) {
        this.series = series;
    }
}
