package davenkin;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 2/5/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BankService {
    public void transfer(int fromId, int toId, int amount);
}
