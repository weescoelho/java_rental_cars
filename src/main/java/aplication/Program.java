package aplication;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);


        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel:");
        System.out.print("Modelo do carro: ");

        Vehicle carModel = new Vehicle(sc.nextLine());

        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);

        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental carRental = new CarRental(start, finish, carModel);

        System.out.println("Entre com o preço por hora:");
        double pricePerHour = sc.nextDouble();

        System.out.println("Entre com o preço por dia:");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService =  new RentalService(pricePerDay,pricePerHour, new BrazilTaxService());

        rentalService.processInvoice(carRental);

        System.out.println("FATURA: ");
        System.out.println("Pagamento básico: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
        System.out.println("Imposto: " + String.format("%.2f", carRental.getInvoice().getTax()));
        System.out.println("Pagamento total: " + carRental.getInvoice().getTotalPayment());

        sc.close();

    }
}
