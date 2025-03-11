package merchandiseservie;

import java.util.List;

public class MerchandiseInterfaceImpl implements MerchandiseInterface {

	@Override
    public int billCalculator(int unitPrice, int qty) {
        return unitPrice * qty;
    }

    @Override
    public void summeryOfday(List<Item> items) {
        int dailyQty = 0;
        int dailySales = 0;
        for (Item item : items) {
            dailyQty += item.getQty();
            dailySales += billCalculator(item.getPrice(), item.getQty());
        }
        System.out.println("\nTotal quantity: " + dailyQty);
        System.out.println("Total sales: " + dailySales + "\n");
    }

    @Override
    public void printBill(List<Item> items, double finalTotal, double discountAmount) {
        System.out.println("*************************************************************");
        System.out.println("                      Event Merchandise Store              ");
        System.out.println("*************************************************************");
        System.out.printf("%5s %15s %10s %10s %10s \n", "Code", "Name", "Quantity", "Price", "Amount");
        for (Item item : items) {
            System.out.printf("%5s %15s %10d %10d %10d \n", item.getNumber(), item.getName(), item.getQty(), item.getPrice(), billCalculator(item.getPrice(), item.getQty()));
        }
        System.out.println("-------------------------------------------------------------");
        System.out.printf("Discount Applied: %40.2f\n", discountAmount);
        System.out.printf("Final Total: %44.2f\n", finalTotal);
        System.out.println("*************************************************************");
        System.out.println("                 Thank you and Come again!                ");
    }

    

}
