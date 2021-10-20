package utilities.parser;

import command.CommandParameters;
import inventory.Medicine;
import inventory.Stock;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manages medicines that are stock objects.
 */
public class StockManager {

    /**
     * Retrieves the total stock quantity for medicine with same name.
     *
     * @param medicines Arraylist of medicines.
     * @param name      Medicine name.
     * @return Total stock quantity for the medicine.
     */
    public static int getTotalStockQuantity(ArrayList<Medicine> medicines, String name) {
        int existingQuantity = 0;
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            boolean isSameMedicineName = medicine.getMedicineName().equalsIgnoreCase(name);
            boolean isDeleted = ((Stock) medicine).isDeleted();
            if (isSameMedicineName && !isDeleted) {
                existingQuantity += medicine.getQuantity();
            }
        }
        return existingQuantity;
    }

    /**
     * Retrieves the maximum stock quantity for medicine with same name.
     *
     * @param medicines Arraylist of medicines.
     * @param name      Medicine name.
     * @return Total maximum stock quantity for the medicine.
     */
    public static int getMaxStockQuantity(ArrayList<Medicine> medicines, String name) {
        int existingMaxQuantity = 0;
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            boolean isSameMedicineName = medicine.getMedicineName().equalsIgnoreCase(name);
            boolean isDeleted = ((Stock) medicine).isDeleted();
            if (isSameMedicineName && !isDeleted) {
                existingMaxQuantity = ((Stock) medicine).getMaxQuantity();
                break;
            }
        }
        assert (existingMaxQuantity > 0) : "Invalid max quantity";
        return existingMaxQuantity;
    }

    /**
     * Extracts the stock object for a given stock id.
     *
     * @param parameters HashMap Key-Value set for parameter and user specified parameter value.
     * @param medicines  Arraylist of all medicines.
     * @return Stock object of the provided stock id by user
     */
    public static Stock extractStockObject(HashMap<String, String> parameters, ArrayList<Medicine> medicines) {
        int stockId = Integer.parseInt(parameters.get(CommandParameters.ID));
        Stock stock = null;
        for (Medicine medicine : medicines) {
            if (medicine instanceof Stock && stockId == ((Stock) medicine).getStockID()) {
                stock = (Stock) medicine;
            }
        }
        assert (stock != null) : "Expected a stock object but none extracted";
        assert (stock.isDeleted() == false) : "Stock object should not be deleted";
        return stock;
    }

    /**
     * Extracts the filtered stock for stocks with same name.
     *
     * @param medicines Arraylist of all medicines.
     * @param stockName Stock name for a given stock.
     * @return ArrayList of filteredStocks of the same stock name.
     */
    public static ArrayList<Stock> getFilteredStocksByName(ArrayList<Medicine> medicines, String stockName) {
        ArrayList<Stock> filteredStocks = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Stock)) {
                continue;
            }
            boolean isSameName = medicine.getMedicineName().equalsIgnoreCase(stockName);
            boolean isDeleted = ((Stock) medicine).isDeleted();
            if (isSameName && !isDeleted) {
                filteredStocks.add((Stock) medicine);
            }
        }
        return filteredStocks;
    }

}