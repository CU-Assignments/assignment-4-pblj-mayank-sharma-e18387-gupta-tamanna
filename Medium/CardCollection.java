import java.util.*;

public class CardCollection {
    private Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    // Method to add a card to the collection
    public void addCard(String symbol, String card) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
    }

    // Method to retrieve cards of a given symbol
    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    public static void main(String[] args) {
        CardCollection collection = new CardCollection();
        
        // Adding sample cards to the collection
        collection.addCard("Hearts", "Ace of Hearts");
        collection.addCard("Hearts", "King of Hearts");
        collection.addCard("Spades", "Queen of Spades");
        collection.addCard("Diamonds", "Jack of Diamonds");
        collection.addCard("Clubs", "10 of Clubs");
        collection.addCard("Spades", "2 of Spades");
        
        // User interaction
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the symbol (e.g., Hearts, Spades, Diamonds, Clubs): ");
        String symbol = scanner.nextLine().trim();
        
        List<String> cards = collection.getCardsBySymbol(symbol);
        
        if (!cards.isEmpty()) {
            System.out.println("Cards of " + symbol + ": " + cards);
        } else {
            System.out.println("No cards found for the symbol: " + symbol);
        }
        
        scanner.close();
    }
}
