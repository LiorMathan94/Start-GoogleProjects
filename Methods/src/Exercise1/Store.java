package Exercise1;

public class Store {
    public void assignCouponToUser(User user, double value) {
        Coupon coupon = new Coupon(value);
        user.assignNewCoupon(coupon);
    }

    public void assignRandomCouponToUser(User user) {
        Coupon randomCoupon = Coupon.generateRandomCoupon();
        user.assignNewCoupon(randomCoupon);
    }

    public void redeemCouponForUser(User user, int couponId) {
        try {
            user.useCoupon(couponId);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}