import Exercise1.Item;
import Exercise1.ProductType;
import Exercise1.Stock;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        testStock();
    }

    public static void testStock() {
        Stock stock = new Stock();
        List<Item> expired = stock.getExpiredItems();
        Optional<Item> closestExpiryItem = stock.getClosestExpiryItem();
        List<Item> sortedAlphabetically = stock.getAlphabeticallySortedItems();
        Optional<Item> oatly = stock.getItemByName("oatly");
        List<String> heavyItems = stock.getItemsNameListHeavierThan(10.0);
        Map<ProductType, Integer> typeQuantity = stock.getProductTypeQuantityHashMap();
    }
}