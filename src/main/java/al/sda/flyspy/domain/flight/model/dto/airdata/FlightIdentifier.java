package al.sda.flyspy.domain.flight.model.dto.airdata;

public class FlightIdentifier {
    private String number;
    private String iata;
    private String icao;
    private CodeShared codeshared; // Could be further defined if needed

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getIcao() {
        return icao;
    }

    public void setIcao(String icao) {
        this.icao = icao;
    }

    public CodeShared isCodeshared() {
        return codeshared;
    }

    public void setCodeshared(CodeShared codeshared) {
        this.codeshared = codeshared;
    }
}