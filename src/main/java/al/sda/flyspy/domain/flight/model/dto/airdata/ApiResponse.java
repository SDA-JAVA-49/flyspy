package al.sda.flyspy.domain.flight.model.dto.airdata;

import java.util.List;

public class ApiResponse {
    private List<AirData> data;
    private Pagination pagination;

    public List<AirData> getData() {
        return data;
    }

    public void setData(List<AirData> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
