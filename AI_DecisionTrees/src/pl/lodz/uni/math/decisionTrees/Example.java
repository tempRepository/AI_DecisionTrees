package pl.lodz.uni.math.decisionTrees;

public class Example {
    private Boolean alternate;
    private Boolean bar;
    private Boolean friOrSat;
    private Boolean hungry;
    private Guests guests;
    private Price price;
    private Boolean raining;
    private Boolean reservation;
    private WaitEstimate waitEstimate;
    private Type type;
    private Boolean finalDecision;

    public Boolean getAlternate() {
        return alternate;
    }

    public void setAlternate(Boolean alternate) {
        this.alternate = alternate;
    }

    public Boolean getBar() {
        return bar;
    }

    public void setBar(Boolean bar) {
        this.bar = bar;
    }

    public Boolean getFriOrSat() {
        return friOrSat;
    }

    public void setFriOrSat(Boolean friOrSat) {
        this.friOrSat = friOrSat;
    }

    public Boolean getHungry() {
        return hungry;
    }

    public void setHungry(Boolean hungry) {
        this.hungry = hungry;
    }

    public Guests getGuests() {
        return guests;
    }

    public void setGuests(Guests guests) {
        this.guests = guests;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Boolean getReservation() {
        return reservation;
    }

    public void setReservation(Boolean reservation) {
        this.reservation = reservation;
    }

    public WaitEstimate getWaitEstimate() {
        return waitEstimate;
    }

    public void setWaitEstimate(WaitEstimate waitEstimate) {
        this.waitEstimate = waitEstimate;
    }

    public Boolean getFinalDecision() {
        return finalDecision;
    }

    public void setFinalDecision(Boolean finalDecision) {
        this.finalDecision = finalDecision;
    }

    public Example() {
        alternate = false;
        bar = false;
        friOrSat = false;
        guests = Guests.NONE;
        hungry = false;
        price = Price.CHEAP;
        raining = false;
        reservation = false;
        waitEstimate = WaitEstimate.TO10;
    }

    public Example(Boolean alternate, Boolean bar, Boolean friOrSat,
            Boolean hungry, Guests guests, Price price, Boolean raining,
            Boolean reservation, Type type, WaitEstimate waitEstimate,
            Boolean finalDecision) {
        this.alternate = alternate;
        this.bar = bar;
        this.friOrSat = friOrSat;
        this.guests = guests;
        this.hungry = hungry;
        this.price = price;
        this.raining = raining;
        this.reservation = reservation;
        this.waitEstimate = waitEstimate;
        this.finalDecision = finalDecision;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
