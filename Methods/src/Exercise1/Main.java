package Exercise1;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        testStore();
    }

    public static void testStore() {
        Store store = new Store();
        User user = new User("Amnon Titinsky");

        store.assignCouponToUser(user, 15);
        store.assignRandomCouponToUser(user);
        store.assignRandomCouponToUser(user);
        store.redeemCouponForUser(user, 1);

        Optional<Coupon> coupon1 = user.getClosestExpiryCoupon();
        Optional<Coupon> coupon2 = user.getRandomCoupon();
        Optional<Coupon> coupon3 = user.getHighestValueCoupon();
    }
}
