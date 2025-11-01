// AccountService.java
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    @Transactional
    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        Account fromAccount = accountDao.findById(fromAccountId);
        Account toAccount = accountDao.findById(toAccountId);

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountDao.update(fromAccount);
        accountDao.update(toAccount);

        // Optionally: record transactions
        accountDao.saveTransaction(new Transaction(amount, "DEBIT", fromAccountId));
        accountDao.saveTransaction(new Transaction(amount, "CREDIT", toAccountId));
    }
}
