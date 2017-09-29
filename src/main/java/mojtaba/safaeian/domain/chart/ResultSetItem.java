package mojtaba.safaeian.domain.chart;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/26/2017.
 */
public class ResultSetItem<T>  {

    private String dimension;
    private T value;


    public ResultSetItem(String dimension , T value) {
        this.dimension = dimension ;
        this.value = value;
    }

    public String getDimension() {
        return dimension;
    }

    public T getValue() {
        return value;
    }

}
