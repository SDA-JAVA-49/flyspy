package al.sda.flyspy.domain.flight.model.dto.airdata;

/**
 * "pagination": {
 *         "limit": 100,
 *         "offset": 0,
 *         "count": 100,
 *         "total": 414560
 *     },
 */
public class Pagination {
    private int limit;
    private int offset;
    private int count;
    private long total;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
