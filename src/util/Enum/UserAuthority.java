package util.Enum;

/**
 * Created by hdd on 19/05/15.
 */
public enum UserAuthority {
    Customer("Customer"),Agent("Agent"),Staff("Staff"),ServiceManager("ServiceManager"),FlightManager("FlightManager"),Admin("Admin");
    private final String value;

    UserAuthority(String e) {
        this.value = e;
    }

}
