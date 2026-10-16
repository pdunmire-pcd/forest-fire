import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Fire {

    public static void main(String[] args){
        Location p = new Location(0,6);
        System.out.println(p);
        System.out.println(p.row());
        System.out.println(p.column());
        Location q = new Location(0,6);

        Set<Location> visitedLocations = new HashSet<>();
        visitedLocations.add(p);
        System.out.println(visitedLocations.contains(q));
    }
    /**
     * Returns how long it takes for all vulnerable trees to be set on fire if a
     * match is lit at a given location.
     * 
     * The forest is represented via a rectangular 2d char array where t represents a tree
     * and . represents an empty space.
     * 
     * At time 0, the tree at location [matchR, matchC] is set on fire. At every subsequent
     * time step, any tree that is adjacent (up/down/left/right) to a burning tree is also
     * set on fire. 
     * 
     * 
     * EXAMPLE 
     * forest:
     * 
     * t..tttt.t
     * ..tt....t
     * ..ttttttt
     * tttt.....
     * 
     * matchR: 2
     * matchC: 6
     * 
     * Result: 8
     * 
     * Explanation:
     * At time 0, the tree at (2, 6) is set on fire. At time 1, its adjacent trees also catch on fire
     * At time 2, the trees adjacent to those catch as well. At time 8, the last tree to catch is at
     * (0,6). In this example, there is one tree that never burns, so it is not included in the time calculation.
     * 
     * 
     * @param forest a 2d array where t represents a tree and . represents the ground
     * @param matchR The row the match is lit at
     * @param matchC The column the match is lit at
     * @return the time at which the final tree to be incinerated starts burning
     */
    public static int timeToBurn(char[][] forest, int matchR, int matchC) {
        // HINT: when adding to your BFS queue, you can include more information than
        // just a location. What other information might be useful?
        // Implement this AND add more tests!!!

        //Breath First Search with queue...
        //visited
        //some sort of way to keep track of time aka timer
        //will need a helper

        // return will be an int... the time at which the final tree to be incinerated starts burning
        if (forest == null || forest.length == 0 ||
                matchR < 0 || matchR >= forest.length ||
                matchC < 0 || matchC >= forest[matchR].length ||
                forest[matchR][matchC] != 't') {
            throw new NoTreeException();
        }

        Burning start = new Burning(matchR, matchC, 0);

        Set<Location> visited = new HashSet<>();
        Queue<Burning> queue = new LinkedList<>();

        //start the queue at matchR and matchC
        queue.add(start);
        visited.add(new Location(matchR, matchC));

        int time = 0;

        //while
        while(!queue.isEmpty()){
            Burning current = queue.remove();
            time = current.time();
            Location currentLocation =
                new Location(current.row(), current.col());

            for (Location neighbor : possibleMoves(forest, currentLocation)) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);

                    Burning next = new Burning(
                        neighbor.row(),
                        neighbor.column(),
                        current.time() + 1
                    );

                    queue.add(next);
                }
            }
        }

        return time;
    }

    //location method
    public static List<Location> possibleMoves(
            char[][] forest, Location current){

        List<Location> neighbors = new LinkedList<>();

        int[][] moves = new int[][]{
            { -1,0}, //up
            {1, 0}, //down
            {0,-1}, //left
            {0,1} //right
        };

        for(int [] move : moves){

            int newRow = current.row() + move[0];
            int newCol = current.column() + move[1];

            if(newRow >= 0 && newRow < forest.length){
                if( newCol >= 0 && newCol < forest[newRow].length){
                    if (forest[newRow][newCol] == 't') {
                        Location neighbor = new Location(newRow, newCol);
                        neighbors.add(neighbor);
                    }
                }
            }
        }
        return neighbors;
    }

}
