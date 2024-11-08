import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    //Дан список  BankAccount {Person owner, String IBAN, double balance).
    // Класс Person состоит из {String fName, String lName String email)
    //
    //Используя stream необходимо получить List всех аккаунтов,
    // баланс которых составляет менее 100.
    //
    //Используя stream необходимо получить List всех владельцев счетов
    //
    //Используя stream,  сформировать лист строк вида
    // “Lennon J.;IBAN: DE199988643;lennon@gmail.com”
    // (т.е. ФИО; счет, email)  для всех клиентов, чей баланс более 100000
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
            Person person1 = new Person("John", "Lennon", "lennon@gmail.com");
            Person person2 = new Person("Paul", "McCartney", "paul@gmail.com");
            Person person3 = new Person("George", "Harrison", "george@gmail.com");

            BankAccount account1 = new BankAccount(person1, "DE199988643", 500000);
            BankAccount account2 = new BankAccount(person2, "DE123456789", 50);
            BankAccount account3 = new BankAccount(person3, "DE987654321", 1000);

            List<BankAccount> accounts = List.of(account1, account2, account3);

        // 1. Список аккаунтов с балансом менее 100
        List<BankAccount> lowBalanceAccounts = accounts.stream()
                .filter(account -> account.getBalance() < 100)
                .collect(Collectors.toList());

        // 2. Список владельцев счетов
        List<Person> accountOwners = accounts.stream()
                .map(BankAccount::getOwner)
                .collect(Collectors.toList());

        // 3. Формирование списка строк для клиентов с балансом более 100000
        List<String> accountDetails = accounts.stream()
                .filter(account -> account.getBalance() > 100000)
                .map(account -> {
                    Person owner = account.getOwner();
                    return owner.getfName() + " " + owner.getlName().charAt(0) + ";IBAN: " + account.getIBAN() + ";" + owner.getEmail();
                })
                .collect(Collectors.toList());




     lowBalanceAccounts.forEach(account -> System.out.println(account.getOwner().getfName() + " " + account.getBalance()));
        accountOwners.forEach(owner -> System.out.println(owner.getfName() + " " + owner.getlName()));
        accountDetails.forEach(System.out::println);
}
}
