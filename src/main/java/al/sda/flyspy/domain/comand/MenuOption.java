package al.sda.flyspy.domain.comand;

public enum MenuOption {
    SHOW_DEPARTURES,
    SHOW_ARRIVALS,
    EXIT;

    public static MenuOption fromInput(String input){
        return  switch(input) {
            case "1" -> SHOW_DEPARTURES;
            case "2" -> SHOW_ARRIVALS;
            case "3" -> EXIT;
            default -> null;
        };
    }
}
