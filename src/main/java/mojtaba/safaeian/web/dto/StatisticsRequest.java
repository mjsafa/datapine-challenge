package mojtaba.safaeian.web.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 * @author Mojtaba Safaeian
 *         Created at: 9/29/2017.
 */
public class StatisticsRequest {

    @Min(value = 1, message = "last parameter must be greater than 1")
    private int last;

    @Pattern(regexp = "seconds|minutes", flags = Pattern.Flag.CASE_INSENSITIVE, message = "only valid values are 'seconds|minutes'")
    private String timeUnit;

    private int mAvgPoints;

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public int getmAvgPoints() {
        return mAvgPoints;
    }

    public void setmAvgPoints(int mAvgPoints) {
        this.mAvgPoints = mAvgPoints;
    }
}
