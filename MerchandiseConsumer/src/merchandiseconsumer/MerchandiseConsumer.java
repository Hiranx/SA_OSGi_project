package merchandiseconsumer;

import java.util.*;

import discountservice.DiscountInterface;
import merchandiseservie.MerchandiseInterface;
import merchandiseservie.Item;

public class MerchandiseConsumer {
	private final MerchandiseInterface merchandiseService;
    private final DiscountInterface discountService;

    private static final Map<Integer, List<Item>> merchMap = new HashMap<>();

    static {
        merchMap.put(1, Arrays.asList(
            new Item("1", "T-Shirt", 1000, 0),
            new Item("2", "Mug", 500, 0),
            new Item("3", "Cap", 300, 0)
        ));

        merchMap.put(2, Arrays.asList(
            new Item("1", "Sticker", 200, 0),
            new Item("2", "T-Shirt", 1200, 0),
            new Item("3", "Photo", 400, 0)
        ));

        merchMap.put(3, Arrays.asList(
            new Item("1", "Bookmark", 100, 0),
            new Item("2", "Pen", 50, 0),
            new Item("3", "Book", 400, 0)
        ));
    }

    public MerchandiseConsumer(MerchandiseInterface merchandiseService, DiscountInterface discountService) {
        this.merchandiseService = merchandiseService;
        this.discountService = discountService;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        List<Item> dailyItems = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        int total = 0, subtotal = 0, numInput;
        double finalTotal = 0, discountAmount = 0;

        System.out.println("\n********  Welcome to the Event Merchandise Store  ********\n");

        do {
            System.out.println("Select the number:\n" +
                               "1) Start Billing\n" +
                               "2) Summary of the day\n" +
                               "3) View last bill\n" +
                               "4) End the program");
            System.out.print("\nEnter the number: ");
            numInput = Integer.parseInt(sc.nextLine());

            if (numInput == 1) {
                if (!items.isEmpty()) {
                    items.clear();
                    total = 0;
                }

                System.out.println("Enter the event number: ");
                System.out.println("1) DevOps workshop 2025");
                System.out.println("2) Get Together SLIIT");
                System.out.println("3) Art Exhibition");

                int eventNum = Integer.parseInt(sc.nextLine());
                String eventName = switch (eventNum) {
                    case 1 -> "DevOps workshop 2025";
                    case 2 -> "Get Together SLIIT";
                    case 3 -> "Art Exhibition";
                    
                    default -> {
                        System.out.println("Invalid Event");
                        yield null;
                    }
                };

                if (eventName == null) continue;

                System.out.println("Available Merchandise for " + eventName + ":");
                List<Item> availableItems = merchMap.get(eventNum);
                for (Item item : availableItems) {
                    System.out.println(item.getNumber() + ") " + item.getName() + " - " + item.getPrice() + "Rs");
                }

                do {
                    System.out.print("Enter item number to buy (-1 to checkout): ");
                    int choiceNum = Integer.parseInt(sc.nextLine());
                    if (choiceNum == -1) break;

                    Item selectedItem = availableItems.stream()
                        .filter(item -> item.getNumber().equals(String.valueOf(choiceNum)))
                        .findFirst()
                        .orElse(null);

                    if (selectedItem == null) {
                        System.out.println("Invalid number");
                        continue;
                    }

                    System.out.print("Enter item quantity: ");
                    int qty = Integer.parseInt(sc.nextLine());

                    Item purchasedItem = new Item(selectedItem.getNumber(), selectedItem.getName(), selectedItem.getPrice(), qty);
                    subtotal = merchandiseService.billCalculator(purchasedItem.getPrice(), purchasedItem.getQty());
                    total += subtotal;

                    items.add(purchasedItem);
                    dailyItems.add(purchasedItem);
                } while (true);

                //dicount section
                if (discountService != null) {
                    double discount = discountService.getDiscount(eventName);
                    discountAmount = total * (discount / 100);
                    finalTotal = total - discountAmount;
                    System.out.println("Discount for " + eventName + ": " + discount + "% (-" + discountAmount + ")");
                    System.out.println("Final Total after Discount: " + finalTotal);
                } else {
                    finalTotal = total;
                    System.out.println("No Discount Applied.");
                }

                merchandiseService.printBill(items, finalTotal, discountAmount);
            } else if (numInput == 2) {
                merchandiseService.summeryOfday(dailyItems);
            } else if (numInput == 3) {
                if (!items.isEmpty()) {
                    System.out.print("\nLast Bill printed\n\n");
                    merchandiseService.printBill(items, finalTotal, discountAmount);
                } else {
                    System.out.print("\n Bill not found \n\n");
                }
            } else if (numInput != 4) {
                System.out.println("\nInvalid input\n");
            }

        } while (numInput != 4);

        System.out.println("\nProgram closed");
        
    }
}
