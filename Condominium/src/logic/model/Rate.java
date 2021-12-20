package logic.model;

public class Rate {

    private String userRated;
    private String userRating;
    private String massageRate;

    public Rate(String userRated, String userRating, String massageRate) {
        this.userRated = userRated;
        this.userRating = userRating;
        this.massageRate = massageRate;
    }

    public String getUserRated() {
        return userRated;
    }

    public void setUserRated(String userRated) {
        this.userRated = userRated;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getMassageRate() {
        return massageRate;
    }

    public void setMassageRate(String massageRate) {
        this.massageRate = massageRate;
    }
}
