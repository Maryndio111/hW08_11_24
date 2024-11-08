
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BankAccountServiceTest {

    private BankAccountService bankAccountService;
    private Person person1, person2, person3;
    private BankAccount account1, account2, account3;

    @DisplayName("")
    public void setUp() {
        bankAccountService = new BankAccountService();

        person1 = new Person("John", "Lennon", "lennon@gmail.com");
        person2 = new Person("Paul", "McCartney", "paul@gmail.com");
        person3 = new Person("George", "Harrison", "george@gmail.com");

        account1 = new BankAccount(person1, "DE199988643", 500000);
        account2 = new BankAccount(person2, "DE123456789", 50);
        account3 = new BankAccount(person3, "DE987654321", 1000);
    }

    @Test
    public void testGetLowBalanceAccounts() {
        List<BankAccount> accounts = List.of(account1, account2, account3);
        List<BankAccount> lowBalanceAccounts = bankAccountService.getLowBalanceAccounts(accounts);

        // Проверяем, что есть только один аккаунт с балансом меньше 100
        assertEquals(1, lowBalanceAccounts.size());
        assertEquals(account2, lowBalanceAccounts.get(0));
    }

    @Test
    public void testGetAccountOwners() {
        List<BankAccount> accounts = List.of(account1, account2, account3);
        List<Person> owners = bankAccountService.getAccountOwners(accounts);

        // Проверяем, что получен список владельцев
        assertEquals(3, owners.size());
        assertTrue(owners.contains(person1));
        assertTrue(owners.contains(person2));
        assertTrue(owners.contains(person3));
    }

    @Test
    public void testGetHighBalanceAccountDetails() {
        List<BankAccount> accounts = List.of(account1, account2, account3);
        List<String> highBalanceDetails = bankAccountService.getHighBalanceAccountDetails(accounts);

        // Проверяем, что есть только один аккаунт с балансом больше 100000
        assertEquals(1, highBalanceDetails.size());

        String expectedDetail = "John L.;IBAN: DE199988643;lennon@gmail.com";
        assertEquals(expectedDetail, highBalanceDetails.get(0));
    }
}

