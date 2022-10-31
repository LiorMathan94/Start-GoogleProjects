package Exercise1;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Stock {
    private final List<Item> items;

    public Stock() {
        this.items = new ArrayList<>();
        generateInitialStock();
    }

    public void addItem(Item item) {
        items.add(item);
        items.sort(null);
    }

    private void generateInitialStock() {
        int stockSize = ThreadLocalRandom.current().nextInt(10, 20);

        for (int i = 0; i < stockSize; i++) {
            generateItemToStock();
        }
    }

    private void generateItemToStock() {
        Item randomItem = Item.getRandomItem();
        addItem(randomItem);
    }

    public List<Item> getExpiredItems() {
        LocalDate today = LocalDate.now();
        Stream<Item> stream = items.stream().filter(item -> item.getExpirationDate().isBefore(today));

        return stream.collect(Collectors.toList());
    }

    public Optional<Item> getClosestExpiryItem() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Stream<Item> stream = items.stream().filter(item -> item.getExpirationDate().isAfter(yesterday));

        List<Item> nonExpiredItems = stream.collect(Collectors.toList());
        Optional<Item> result = nonExpiredItems.size() > 0 ? Optional.of(nonExpiredItems.get(0)) : Optional.empty();

        return result;
    }

    public List<Item> getAlphabeticallySortedItems() {
        return items.stream().sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());
    }

    public Optional<Item> getItemByName(String name) {
        return items.stream().filter(item -> item.getName() == name).findFirst();
    }

    public List<String> getItemsNameListHeavierThan(double weight) {
        return items.stream().filter(item -> item.getWeight() > weight)
                .map(Item::getName).collect(Collectors.toList());
    }

    public Map<ProductType, Integer> getProductTypeQuantityHashMap() {
        return items.stream().collect(groupingBy(Item::getType, summingInt(e -> 1)));
    }
}
