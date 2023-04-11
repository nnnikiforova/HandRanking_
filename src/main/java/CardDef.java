import java.util.logging.Logger;

public class CardDef {
    private static Logger log = Logger.getLogger(CardDef.class.getName());
    String suit;
    String value;
    String compare = "23456789TJQKA";

    public CardDef(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public CardDef(String card) {
        this.value = String.valueOf(card.charAt(0));
        this.suit = String.valueOf(card.charAt(1));

    }

    @Override
    public String toString(){
        return "Card "+this.value+" "+ this.suit;
    }
    public String getSuit() {
        if (!((suit.equals("H"))||(suit.equals("S"))||(suit.equals("C"))||(suit.equals("D")))){
            log.warning("Проверьте корректность введения мастей или номинала карт");
            System.exit(0);
        }return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        if (compare.indexOf(this.value) == -1){
            log.warning("Проверьте корректность введения мастей или номинала карт");
            System.exit(0);
        }
       return compare.indexOf(this.value);
    }

    public void setValue(String value) {
        this.value = value;
    }


}
