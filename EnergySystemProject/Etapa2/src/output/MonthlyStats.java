package output;

import java.util.ArrayList;
import java.util.List;

public final class MonthlyStats {
    private int month;
    private List<Integer> distributorsIds = new ArrayList<>(0);

    public MonthlyStats(int month, List<Integer> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(final int month) {
        this.month = month;
    }

    public List<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(final List<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
