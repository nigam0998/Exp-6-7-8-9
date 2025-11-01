import javax.persistence.*;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private String type; // "DEBIT" or "CREDIT"
    private int accountId;

    // Getters, setters, constructors
}