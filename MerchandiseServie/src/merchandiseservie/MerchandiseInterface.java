package merchandiseservie;

import java.util.List;

public interface MerchandiseInterface {
	int billCalculator(int unitPrice, int qty);
    void summeryOfday(List<Item> items);
    void printBill(List<Item> items, double finalTotal, double discountAmount);
}
