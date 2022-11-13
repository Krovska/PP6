package object;

public enum ComfortRating {
    SEATING("Сидячі місця", 0), SECONDCLASS("Плацкарт", 1), FIRSTCLASS("Купе", 2), LUXE("Люкс", 3);
    private final String name;
    private final int comfortRating;
    ComfortRating(String name, int comfortRating){
        this.name=name;
        this.comfortRating=comfortRating;
    }

    public String toString() {
        return name;
    }

    public int getComfortRating() {
        return comfortRating;
    }
}
