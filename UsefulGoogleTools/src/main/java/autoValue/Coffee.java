package autoValue;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Coffee {
    static Coffee create(int strength) {
        return new AutoValue_Coffee(strength);
    }

    abstract int strength();
}
