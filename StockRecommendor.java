import java.util.*;
public class StockRecommendor {
    private static class Company {
    	
        String name;
        double ce;
        double pe;
        double marketValue;

        public Company(String name, double ce, double pe, double marketValue) {
            this.name = name;
            this.ce = ce;
            this.pe = pe;
            this.marketValue = marketValue;
        }

        public String toString() {
            return name + " (CE: " + ce + ", PE: " + pe + ", Market Value: " + marketValue + ")";
        }
    }

    public static void main(String[] args) {
        LinkedList<Company> companies = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the number of companies to compare: ");
            int numCompanies = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            for (int i = 1; i <= numCompanies; i++) {
                System.out.println("Enter details for company #" + i + ":");

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("CE: ");
                double ce = scanner.nextDouble();
                if (ce <= 0) {
                    throw new IllegalArgumentException("CE must be a positive number.");
                }

                System.out.print("PE: ");
                double pe = scanner.nextDouble();
                if (pe <= 0) {
                    throw new IllegalArgumentException("PE must be a positive number.");
                }

                System.out.print("Market Value: ");
                double marketValue = scanner.nextDouble();
                if (marketValue <= 0) {
                    throw new IllegalArgumentException("Market value must be a positive number.");
                }

                companies.add(new Company(name, ce, pe, marketValue));
                scanner.nextLine(); // consume the newline character
            }

            // Sort the companies based on their market value
            companies.sort((c1, c2) -> Double.compare(c2.marketValue, c1.marketValue));

            // Print the sorted list of companies
            System.out.println("\nList of companies sorted by market value:");
            for (Company company : companies) {
                System.out.println(company);
            }

            // Find the company with the best CE-to-PE ratio
            Company bestCompany = null;
            double bestRatio = Double.NEGATIVE_INFINITY;

            for (Company company : companies) {
                double ratio = company.ce + company.pe;

                if (ratio > bestRatio) {
                    bestRatio = ratio;
                    bestCompany = company;
                }
            }

            // Print the recommended company
            System.out.println("\nBest company based on CE-to-PE ratio: " + bestCompany);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
