package Interest;

public class BasicInterest implements InterestManager {

	@Override
	public Double calculateInterest(Double balance) throws Exception {
		if (balance.equals(0.0))
			throw new Exception("Your balance is 0.0");
		return balance*0.02;
	}

}
