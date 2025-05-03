package al.sda.flyspy.domain.flight.model.dto.airdata;


public class LiveData {
    private String updated;
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Double direction;
    private Double speedHorizontal;
    private Double speedVertical;
    private Boolean isGround;

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Double getDirection() {
        return direction;
    }

    public void setDirection(Double direction) {
        this.direction = direction;
    }

    public Double getSpeedHorizontal() {
        return speedHorizontal;
    }

    public void setSpeedHorizontal(Double speedHorizontal) {
        this.speedHorizontal = speedHorizontal;
    }

    public Double getSpeedVertical() {
        return speedVertical;
    }

    public void setSpeedVertical(Double speedVertical) {
        this.speedVertical = speedVertical;
    }

    public Boolean getIsGround() {
        return isGround;
    }

    public void setIsGround(Boolean isGround) {
        this.isGround = isGround;
    }
}
