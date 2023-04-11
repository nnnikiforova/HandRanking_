import java.util.*;
import java.util.logging.Logger;

public class PokerHand {
    private static Logger log = Logger.getLogger(PokerHand.class.getName());

    String oneHand;
    int rank;
    List<CardDef> cardList;

    String[] typeCombination = new String[]{"Flush and Straight",
                                            "Four of a Kind",
                                            "Full House",
                                            "Flush",
                                            "Straight",
                                            "Set",
                                            "Two pairs",
                                            "Pair",
                                            "High card"};

    public PokerHand(String oneHand) {
        this.oneHand = oneHand;
        getCardList();
    }

    public PokerHand() {}

    public String getOneHand() {
        return oneHand;
    }

    public void setOneHand(String oneHand) {
        this.oneHand = oneHand;
    }

    //Разбиение строки на массив карт
    public List<CardDef> getCardList() {
        ArrayList<CardDef> cardDefList = new ArrayList<>();
        this.oneHand.split(" ");
        String[] listCard = this.oneHand.split(" ");
        for ( int i = 0; i < listCard.length; i++){
            cardDefList.add(new CardDef(listCard[i]));
        }
        cardList = cardDefList;
        return cardList;
    }
    //Сортировка по мастям
    public List<CardDef> getSuitOrder(){
        List<CardDef> suitOrder = this.cardList;
        Collections.sort(suitOrder, Comparator.comparing(CardDef::getSuit));
        return suitOrder;
    }
    //Сортировка по номиналу карты в поряддке возрастания (12 = туз)
    public List<CardDef> getValueOrder(){
        List<CardDef> valueOrder = this.cardList;
        Collections.sort(valueOrder, Comparator.comparing(CardDef::getValue));
        return valueOrder;
    }


    public ArrayList<String> getSuitOnly(){
        ArrayList<String> suitOnly = new ArrayList<>();
        for (CardDef oneCard :
                getSuitOrder()) {
            suitOnly.add(oneCard.getSuit());
        }
        return  suitOnly;
    }

    public ArrayList<Integer> getValueOnly(){
        ArrayList<Integer> valueOnly = new ArrayList<>();
        for (CardDef oneCard :
                getValueOrder()) {
            valueOnly.add(oneCard.getValue());
        }
        return valueOnly;
    }



    public CardDef getKicker(){
        return getValueOrder().get(getValueOrder().size()-1);
    }

    public Integer getHighCard(){
        log.info("Find High Card...");
        Integer kikerValue = getValueOrder().get(getValueOrder().size()-1).getValue();
        System.out.println(getValueOrder().get(getValueOrder().size()-1).getValue());
        return getValueOrder().get(getValueOrder().size()-1).getValue();
    }
    public boolean isFlesh(){
        List<CardDef> suitOrder = getSuitOrder();
        return getSuitOrder().get(0).getSuit().equalsIgnoreCase(suitOrder.get(suitOrder.size() - 1).getSuit());
    }
    public boolean isStraight(){
        ArrayList<Integer> valueOnly = getValueOnly();
        return valueOnly.stream().distinct().count() == valueOnly.size() && ((Collections.max(valueOnly) - Collections.min(valueOnly)) == valueOnly.size() - 1);
    }
    public boolean isFleshStraight(){
        return (isFlesh()&&isStraight());
    }

    public boolean isFullHouse(){
        ArrayList<Integer> duplicates = getDuplicatesList();
        return ((duplicates.contains(2))&&(duplicates.contains(3)));
    }
    //Метод собирает количество дубликатов в массив
    public ArrayList<Integer> getDuplicatesList(){
        ArrayList<Integer> valueOnly = getValueOnly();
        ArrayList<Integer> duplicates = new ArrayList<>();
        for (Integer r : new HashSet<Integer>(valueOnly)) {
            duplicates.add(Collections.frequency(valueOnly, r));
        }
        return duplicates;
    }
    //Метод проверяет какого номинала карты находятся в сете
    public Integer getValueInSet(){
        ArrayList<Integer> valueOnly = getValueOnly();
        ArrayList<Integer> duplicates = new ArrayList<>();
        for (Integer r : new HashSet<Integer>(valueOnly)) {
            if (Collections.frequency(valueOnly, r) == 3) {
                return r;
            }
        }
         return 0;
    }
    //Метод проверяет какого номинала карты находятся в паре
    public Integer getValueInPair(){
        ArrayList<Integer> valueOnly = getValueOnly();
        ArrayList<Integer> duplicates = new ArrayList<>();
        for (Integer r : new HashSet<Integer>(valueOnly)) {
            if (Collections.frequency(valueOnly, r) == 2) {
                return r;
            }
        }
        return 0;
    }

    public Integer rank(){
        log.info("Define combination...");
        ArrayList<Integer>duplicates = getDuplicatesList();
        log.info("Check flush");
        boolean flesh = isFlesh();
        log.info("Check straight");
        boolean straight = isStraight();
        log.info("Check full-house");
        boolean fullHouse = isFullHouse();
        log.info("Check flush-straight");
        boolean flush_straight = isFleshStraight();
        log.info("Check pair");
        boolean pair = duplicates.contains(2);
        log.info("Check set");
        boolean set = duplicates.contains(3);
        log.info("Check kare");
        boolean kare = duplicates.contains(4);
        log.info("Check two-pair");
        boolean twoPair  = (Collections.frequency(duplicates, 2) > 1);

        if (flush_straight){
            rank = 1; //
        } else if (kare){
            rank = 2;//
        }else if (fullHouse){
            rank = 3;//
        }else if (flesh){
            rank = 4;
        }else if (straight){
            rank = 5;
        }else if (set){
            rank = 6;
        }else if (twoPair){
            rank = 7;
        }else if (pair){
            rank = 8;
        } else {
            rank = 9;
        }
        return rank;
    }

    public void setCardList(List<CardDef> cardList) {
        this.cardList = cardList;
    }

    @Override
    public String toString(){
        return "PokerCombination {" + oneHand +
                "} has type = " + typeCombination[rank-1] +
                ", rank = "  + rank ;
    }
}
