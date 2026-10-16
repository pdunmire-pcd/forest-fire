import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FireTest {
    @Test
    public void testTimeToBurnExample() {
        char[][] forest = {
            {'t','.','.','t','t','t','t','.','t'},
            {'.','.','t','t','.','.','.','.','t'},
            {'.','.','t','t','t','t','t','t','t'},
            {'t','t','t','t','.','.','.','.','.'}
        };

        int matchR = 2;
        int matchC = 6;

        int expected = 8;
        int actual = Fire.timeToBurn(forest, matchR, matchC);

        assertEquals(expected, actual);
    }

    // Add more tests!

    //Single tree test -- a forest of just {'t'} with the match at {0,0}. 
    @Test 
    public void singleTreeTestReturnsZero(){
        char[][] forest = {
            {'t'}
        };

        int matchR = 0;
        int matchC = 0;

        int expected = 0;
        int actual = Fire.timeToBurn(forest, matchR, matchC);

        assertEquals(expected, actual);
    }

    //Straight line: a row of 5 trees with the match at one end and then antoher test with the match in the middle. Should be different times...


    // Gap in the trees -- {t, t, ., t, t} with the match on the left.. it shouldn't count the . in the count...

    //Diagonal only -- trees that touch only at corners.. the fire should not spread...

    //All trees -- a full 3 by 3 grid of the t with the match in the center, then in a corner

    //Match on empty ground or off the grid -- should throw NoTreeException
}
