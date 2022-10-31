package autoValue;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CoffeeMachine {
    static CoffeeMachine create(int id, String brand) {
        return new AutoValue_CoffeeMachine(id, brand);
    }

    abstract int id();
    abstract String brand();
}
