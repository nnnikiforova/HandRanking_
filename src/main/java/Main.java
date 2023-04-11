import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        ArrayList<PokerHand> hands = new ArrayList<>();
        hands.add(new PokerHand("KS 2H 5C JD TD"));
        hands.add(new PokerHand("2C 3C AC 4C 5C"));

//         hands.add(new PokerHand("5H 6D 7C 8S 9H"));//straight
//         hands.add(new PokerHand("2H 2D 2C 3S 3C"));//fullHouse
//         hands.add(new PokerHand("8H 8C AS KC JH"));//pair with 8
//         hands.add(new PokerHand("AC AD 5C 6H 7S"));//pair with Ace
//         hands.add(new PokerHand("AC AD 6C 6H 7S"));//twoPair
//         hands.add(new PokerHand("4H 4D 4C 4S 6H"));//Four of a Kind
//         hands.add(new PokerHand("2H 3H 4H 5H 6H"));//Flush and Straight
//         hands.add(new PokerHand("2H 9H 4H 7H 6H"));//Flush
//         hands.add(new PokerHand("KH QH JH TH 9H"));//Flush and Straight
        log.info("Starting range " + hands.size() + " poker combinations");
        //сортируем сначала по рангу, затем, если он одинаковый сортируем по номиналу сета, далее по номиналу пары и в конце по номиналу старшей карты
        hands.sort(Comparator.comparingInt(PokerHand::rank)
                .thenComparing(PokerHand::getValueInSet, Comparator.reverseOrder())
                .thenComparing(PokerHand::getValueInPair, Comparator.reverseOrder())
                .thenComparing(PokerHand::getHighCard, Comparator.reverseOrder()));
        for (PokerHand oneHand: hands) {
            System.out.println(oneHand);
        }


    }


}
