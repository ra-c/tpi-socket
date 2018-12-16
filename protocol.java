
import java.util.regex.*;
import java.math.*;
public class protocol {
	Pattern p = Pattern.compile("\\s*(?<intero1>\\d+)\\s*,?\\s*(?<decimale1>\\d*)\\s*(?<operatore>[-+*/])\\s*(?<intero2>\\d+)\\s*,?\\s*(?<decimale2>\\d*)\\s*");

	public String elabora(String input)
	{
		String output;
		Matcher m = p.matcher(input);
		if(m.find())
		{
			if(m.group("operatore").charAt(0) == '/' || !(m.group("decimale1").isEmpty()) || !(m.group("decimale2").isEmpty()))
			{
				BigDecimal operando1 = new BigDecimal(m.group("intero1") + '.' + m.group("decimale1"));
				BigDecimal operando2 = new BigDecimal(m.group("intero2") + '.' + m.group("decimale2"));
				BigDecimal risultato;
				switch(m.group("operatore").charAt(0)){
					case '+':
					{
						risultato = operando1.add(operando2);
						break;
					}
					case '-':
					{
						risultato = operando1.subtract(operando2);
						break;
					}
					case '*':
					{
						risultato = operando1.multiply(operando2);
						break;
					}
					default:
					{
						risultato = operando1.divide(operando2);
						break;
					}
				}
					output = m.group("intero1") + ',' + m.group("decimale1") + ' ' + m.group("operatore") + ' ' + m.group("intero2") + m.group("decimale2") + " = " + risultato.toString();
				
			} else {
				System.out.println("Intero");
				BigInteger operando1 = new BigInteger(m.group("intero1"));
				BigInteger operando2 = new BigInteger(m.group("intero2"));
				BigInteger risultato = null;
				
				switch(m.group("operatore").charAt(0)){
					case '+':
					{
						risultato = operando1.add(operando2);
						break;
					}
					case '-':
					{
						risultato = operando1.subtract(operando2);
						break;
					}
					case '*':
					{
						risultato = operando1.multiply(operando2);
						break;
					}
				}
					output = m.group("intero1") + ' ' + m.group("operatore") + ' ' + m.group("intero2") + " = " + risultato.toString();
			}
		}	else output = "error";
		return output;
	}
}
	