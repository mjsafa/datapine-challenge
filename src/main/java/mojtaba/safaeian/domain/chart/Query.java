package mojtaba.safaeian.domain.chart;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/26/2017.
 */
public class Query {

    private String dimension;
    private String measure;

    public Query(String dimension, String measure) {
        this.dimension = dimension;
        this.measure = measure;
    }

    public String getDimension() {
        return dimension;
    }

    public String getMeasure() {
        return measure;
    }
}
