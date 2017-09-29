package mojtaba.safaeian.domain.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/26/2017.
 */
public class ResultSet<T>{

    private String dimension;
    private String measure;
    private List<ResultSetItem<T>> items;

    public ResultSet(String dimension, String measure, List<ResultSetItem<T>> items) {
        this.dimension = dimension;
        this.measure = measure;
        this.items = items;
    }

    public ResultSet(String dimension, String measure) {
        this.dimension = dimension;
        this.measure = measure;
        this.items = new ArrayList<>();
    }

    public ResultSet<T> addItem(ResultSetItem<T> item){
        this.items.add(item);
        return this;
    }

    public String getDimension() {
        return dimension;
    }

    public String getMeasure() {
        return measure;
    }

    public List<ResultSetItem<T>> getItems() {
        return items;
    }
}
