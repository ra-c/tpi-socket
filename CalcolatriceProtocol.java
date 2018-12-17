import java.util.regex.*;
import java.math.*;
public class CalcolatriceProtocol {
	//Niente domande.
	Pattern p = Pattern.compile("\\s*(?<intero1>\\d+)\\s*\\.?\\s*(?<decimale1>\\d*)\\s*(?<operatore>[-+*/])\\s*(?<intero2>\\d+)\\s*\\.?\\s*(?<decimale2>\\d*)\\s*");
	//							^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	//							Pattern per espressioni regolari. Serve a verificare che in input sia inserita un'operazione valida (come ad esempio 2+2) e ignora spazi messi a caso
	public String elabora(String input)
	{
		if (input.equalsIgnoreCase("esci")){		//	Se input è uguale a "esci"
			return "Chiusura...";					//	Allora restituisci questa stringa
		}											//	ed esci dalla funzione

		String output;
		Matcher m = p.matcher(input);	//Oggetto creato per verificare se la stringa inserita rispetta il pattern (cioè quella cosa lunghissima sopra)
		if(m.find()) {
			if (m.group("decimale1").isEmpty() && m.group("decimale2").isEmpty()) {		//if: controlla se entrambi i numeri inseriti sono interi (cioè senza parte decimale)
				if (m.group("operatore").charAt(0) == '/') {								//if: controlla se l'operazione richiesta è una divisione
					BigDecimal operando1 = new BigDecimal(m.group("intero1"));				//in caso affermativo istanzia 2 oggetti BigDecimal col valore dei numeri in input
					BigDecimal operando2 = new BigDecimal(m.group("intero2"));				// (Cosa è BigDecimal? Semplicemente un float che non va mai in overflow, con valore illimitato)
					if(operando2.compareTo(BigDecimal.ZERO) == 0) {			//Controlla se il divisore è uguale a 0
						output = "Errore: impossibile dividere per zero.";	//In caso affermativo dà errore
						return output;
					} else {
						//Se il divisore non è uguale a 0. Il parametro "15" indica il numero di cifre decimali da mostrare
						//Il parametro successivo indica semplicemente che il risultato decimale viene arrotondato per eccesso
						BigDecimal risultato = operando1.divide(operando2, 15, RoundingMode.HALF_UP);
						//Il risultato viene messo in una stringa
						output = operando1.toString() + ' ' + m.group("operatore") + ' ' + operando2.toString() + " = " + risultato.toString();
					}
				} else {
					BigInteger operando1 = new BigInteger(m.group("intero1"));  //Se in input sono inseriti 2 numeri interi e non viene richiesta una divisione
					BigInteger operando2 = new BigInteger(m.group("intero2"));	//allora vengono istanziati oggetti BigInteger
					BigInteger risultato = null;								//(BigInteger è un int illimitato che non va mai in overflow)
					switch (m.group("operatore").charAt(0)) {		//
						case '+': {									//
							risultato = operando1.add(operando2);	//In base all'operatore inserito viene svolta l'operazione apposita
							break;									//(se inserito + va fatta la somma, se - la sottrazione ecc...)
						}
						case '-': {
							risultato = operando1.subtract(operando2);
							break;
						}
						case '*': {
							risultato = operando1.multiply(operando2);
							break;
						}
					}
					output = operando1.toString() + ' ' + m.group("operatore") + ' ' + operando2.toString() + " = " + risultato.toString();
				}
				return output;
			}

			BigDecimal operando1 = new BigDecimal(m.group("intero1") + '.' + m.group("decimale1"));	//Se i numeri sono decimali allora istanzia BigDecimal
			BigDecimal operando2 = new BigDecimal(m.group("intero2") + '.' + m.group("decimale2"));
			BigDecimal risultato = null;
			switch (m.group("operatore").charAt(0)) {
				case '+': {
					risultato = operando1.add(operando2);
					break;
				}
				case '-': {
					risultato = operando1.subtract(operando2);
					break;
				}
				case '*': {
					risultato = operando1.multiply(operando2);
					break;
				}
				case '/': {
					if(operando2.compareTo(BigDecimal.ZERO) == 0)
					{
						output = "Errore: impossibile dividere per zero.";
						return output;
					} else {
						risultato = operando1.divide(operando2, 15, RoundingMode.HALF_UP);
					}
					break;
				}
			}
			output = operando1.toString() + ' ' + m.group("operatore") + ' ' + operando2.toString() + " = " + risultato.toString();
			return output;
		}	else output = "Errore: espressione non valida."; //vuoto
		return output;
	}
}