import org.junit.Test;
import static org.junit.Assert.*;

public class PokerHandTest {
    @Test
    public void testCompare(){
        PokerHand firstHand = new PokerHand("9H 3H AH QC 9C");
        PokerHand secondHand = new PokerHand("2C 2C 3C 3C 5C");
        assertTrue(firstHand.rank().compareTo(secondHand.rank())<0);
        assertTrue(secondHand.rank().compareTo(firstHand.rank())>0);
    }

    @Test
    public void testIsFlesh(){
        PokerHand firstHand = new PokerHand("9H 3H AH QH 9H");//flesh
        PokerHand secondHand = new PokerHand("2H 2C 3C 3C 5C");//no-flesh
        assertTrue(firstHand.isFlesh());
        assertFalse(secondHand.isFlesh());
    }

    @Test
    public void testIsStraight(){
        PokerHand firstHand = new PokerHand("2H 3H 4H 5H 6C");//straight
        PokerHand secondHand = new PokerHand("2H 2C 3C 3C 5C");//no-straight
        assertTrue(firstHand.isStraight());
        assertFalse(secondHand.isStraight());
    }
    @Test
    public void testIsFleshStraight(){
        PokerHand firstHand = new PokerHand("2H 3H 4H 5H 6H");//flesh-straight
        PokerHand secondHand = new PokerHand("2H 2C 3C 3C 5C");//no-flesh-straight
        assertTrue(firstHand.isFleshStraight());
        assertFalse(secondHand.isFleshStraight());
    }
    @Test
    public void testDefineCombination(){
        PokerHand fleshStraight = new PokerHand("2H 3H 4H 5H 6H");
        PokerHand straight = new PokerHand("8H 9C TC JC QC");
        PokerHand kare = new PokerHand("JH JS JC JD 5C");
        PokerHand set = new PokerHand("2H QC 3C 3D 3S");
        PokerHand pair = new PokerHand("2H 2C 4C 8C KC");
        PokerHand twoPair = new PokerHand("2H 2C 3C 3D 5C");
        PokerHand fullHouse = new PokerHand("2H 2C 3C 3S 3D");
        PokerHand flesh = new PokerHand("2C 3C 7C 9C TC");
        PokerHand noCombination = new PokerHand("2H 3C 6C 9C QC");
        assertEquals(noCombination.rank(),Integer.valueOf(9));
        assertEquals(pair.rank(),Integer.valueOf(8));
        assertEquals(twoPair.rank(),Integer.valueOf(7));
        assertEquals(set.rank(),Integer.valueOf(6));
        assertEquals(straight.rank(),Integer.valueOf(5));
        assertEquals(flesh.rank(),Integer.valueOf(4));
        assertEquals(fullHouse.rank(),Integer.valueOf(3));
        assertEquals(kare.rank(),Integer.valueOf(2));
        assertEquals(fleshStraight.rank(),Integer.valueOf(1));
    }
}
