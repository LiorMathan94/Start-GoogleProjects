package Exercise1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class User {
    private final String name;
    private final List<Coupon> coupons;

    public User(String name) {
        this.name = name;
        this.coupons = new ArrayList<>();
    }

    public void assignNewCoupon(Coupon coupon) {
        this.coupons.add(coupon);
    }

    public void useCoupon(int couponId) throws IllegalArgumentException{
        Coupon couponToUse = null;

        for (Coupon coupon : coupons) {
            if (coupon.getId() == couponId) {
                couponToUse = coupon;
                break;
            }
        }

        if (couponToUse == null) {
            throw new IllegalArgumentException("Coupon ID does not exists for this user!");
        }
        if (!couponToUse.isValidCoupon()) {
            throw new IllegalArgumentException("This coupon has expired!");
        }

        coupons.remove(couponToUse);
    }

    public Optional<Coupon> getCouponByID(int id) {
        for (Coupon coupon : coupons) {
            if (coupon.getId() == id) {
                return Optional.of(new Coupon(coupon));
            }
        }

        return Optional.empty();
    }

    public Optional<Coupon> getHighestValueCoupon() {
        if (coupons.isEmpty()) {
            return Optional.empty();
        }

        double maxValue = coupons.get(0).getValue();
        int maxValueId = coupons.get(0).getId();

        for (Coupon coupon : coupons) {
            if (coupon.getValue() > maxValue) {
                maxValue = coupon.getValue();
                maxValueId = coupon.getId();
            }
        }

        return getCouponByID(maxValueId);
    }

    public Optional<Coupon> getClosestExpiryCoupon() {
        if (coupons.isEmpty()) {
            return Optional.empty();
        }

        Date closestDate = coupons.get(0).getExpiryDate();
        int closestId = coupons.get(0).getId();

        for (Coupon coupon : coupons) {
            if (closestDate.after(coupon.getExpiryDate())) {
                closestDate = coupon.getExpiryDate();
                closestId = coupon.getId();
            }
        }

        return getCouponByID(closestId);
    }

    public Optional<Coupon> getRandomCoupon() {
        if (coupons.isEmpty()) {
            return Optional.empty();
        }

        Coupon randomCoupon = coupons.get(ThreadLocalRandom.current().nextInt(coupons.size()));
        return Optional.of(new Coupon(randomCoupon));
    }

}
