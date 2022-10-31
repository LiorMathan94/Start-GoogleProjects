package Exercise1;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

class Coupon {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private final Date expiryDate;
    private final double value;
    private static final int MAX_COUPON_YEARS = 10;
    private static final double MIN_COUPON_VALUE = 1.0;
    private static final double MAX_COUPON_VALUE = 1000.0;


    Coupon(Date expiryDate, double value) {
        this.id = count.incrementAndGet();
        this.expiryDate = new Date(expiryDate.getTime());

        if (value > 0) { this.value = value; }
        else { throw new IllegalArgumentException(); }
    }

    Coupon(double value) {
        this.id = count.incrementAndGet();
        this.expiryDate = generateRandomDate();

        if (value > 0) { this.value = value; }
        else { throw new IllegalArgumentException(); }
    }

    Coupon(Coupon coupon) {
        this.id = coupon.getId();
        this.expiryDate = coupon.getExpiryDate();
        this.value = coupon.getValue();
    }

    static Coupon generateRandomCoupon() {
        Date randomDate = generateRandomDate();
        double randomValue = ThreadLocalRandom.current().nextDouble(MIN_COUPON_VALUE, MAX_COUPON_VALUE);

        return new Coupon(randomDate, randomValue);
    }

    public boolean isValidCoupon() {
        Date today = Calendar.getInstance().getTime();

        return expiryDate.after(today);
    }

    private static Date generateRandomDate() {
        Calendar calendar = Calendar.getInstance();
        LocalDate today = LocalDate.now();
        int thisYear = today.getYear();

        int year = ThreadLocalRandom.current().nextInt(thisYear + 1, thisYear + MAX_COUPON_YEARS);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new Date(year, month, day);
    }

    public int getId() {
        return id;
    }

    public Date getExpiryDate() {
        return new Date(expiryDate.getTime());
    }

    public double getValue() {
        return value;
    }
}
