package cafe.springcafe.config;

public abstract class Config {

    private Config() {}

    public static final String DISH_NOT_FOUND_IN_THE_MENU = "Dish not found in the menu";
    public static final String ALL_COOKS_ARE_BUSY_AT_THE_MOMENT = "All cooks are busy at the moment";
    public static final String COOK_NOT_FOUND_FOR_THE_GIVEN_ID = "Cook not found for the given ID";
    public static final String SUCCESSFULLY_ADDED_ORDER = "Successfully added order";
    public static final String COOK_WAS_ADDED_SUCCESSFULLY = "cook, was added successfully";

    public static final String NO_CURRENT_COOKS_EMPLOYED_AT_THE_FACILITY = "No current cooks employed at the facility";
}
