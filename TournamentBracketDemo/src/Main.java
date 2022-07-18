import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static String currentPlayers = "[0,1,2,3,4,5,6,7]"; // REPRESENTS FIELD IN TABLE
    static String currentWinners = "[]"; // REPRESENTS FIELD IN TABLE
    static String currentMatch = "[]"; // REPRESENTS FIELD IN TABLE

    static List<Integer> currentPlayersList = new ArrayList<>();
    static List<Integer> currentWinnersList = new ArrayList<>();
    static List<Integer> currentMatchList = new ArrayList<>();


    static LinkedHashSet<Player> currentPlayersHash = new LinkedHashSet<>();// REPRESENTS FIELD IN TABLE
    static LinkedHashSet<Player> currentMatchHash = new LinkedHashSet<>();// REPRESENTS FIELD IN TABLE
    static LinkedHashSet<Player> currentWinnersHash = new LinkedHashSet<>();// REPRESENTS FIELD IN TABLE

    static LinkedHashSet<Player> tempPlayersHash = new LinkedHashSet<>();// REPRESENTS FIELD IN TABLE
    static LinkedHashSet<Player> tempMatchHash = new LinkedHashSet<>();// REPRESENTS FIELD IN TABLE
    static LinkedHashSet<Player> tempWinnersHash = new LinkedHashSet<>();// REPRESENTS FIELD IN TABLE






    public static void main(String[] args)
    {
        currentPlayersHash.add(new Player("Jack", "123", 1));
        currentPlayersHash.add(new Player("Ben", "123", 2));
        currentPlayersHash.add(new Player("Matt", "123", 3));
        currentPlayersHash.add(new Player("Matthew", "123", 4));
        currentPlayersHash.add(new Player("Rebecca", "123", 5));
        currentPlayersHash.add(new Player("John", "123", 6));
        currentPlayersHash.add(new Player("Eve", "123", 7));
        currentPlayersHash.add(new Player("Tiarnan", "123", 8));
        //Player[] tempPlayerHashArray = new Player[currentPlayersHash.size()];
        //tempPlayerHashArray = currentPlayersHash.toArray(tempPlayerHashArray);
        /*for(int i = 0; i < tempPlayerHashArray.length; i++)
        {
            System.out.println(tempPlayerHashArray[i].getName());
        }*/
        createNewMatchHash();
        System.out.println(currentPlayersHash.size());
        debugPrintAll(convertLHStoArrayList(currentPlayersHash));
        debugPrintAll(convertLHStoArrayList(currentMatchHash));






    }

    public static void debugPrintAll(ArrayList<Player> myList)
    {
        System.out.println("--------------------------");
        System.out.println("Debug Printing Arraylist");
        for(int i = 0; i < myList.size(); i++)
        {
            System.out.println(myList.get(i).getName());
        }
        System.out.println("--------------------------");
    }

    public static ArrayList<Player> convertLHStoArrayList(LinkedHashSet<Player> LHS)
    {
        Player[] tempPlayerHashArray = new Player[LHS.size()];
        tempPlayerHashArray = LHS.toArray(tempPlayerHashArray);
        ArrayList<Player> tempPlayerHashList = new ArrayList<>();
        for(int i = 0; i < tempPlayerHashArray.length; i++)
        {
            tempPlayerHashList.add(tempPlayerHashArray[i]);
        }
        return tempPlayerHashList;
    }

    public static LinkedHashSet<Player> createNewMatchHash() // most likely will be endpoint
    {

        tempPlayersHash = currentPlayersHash; // Takes data from table and stores in list
        //System.out.println(tempPlayersHash.size());
        tempWinnersHash = currentWinnersHash; // Takes data from table and stores in list
        if(tempPlayersHash.size() == 0) // Checks if current round has any players left and if not creates new round with winners.
        {
            tempPlayersHash = tempWinnersHash; // Sets current round players as last rounds winners
            tempWinnersHash.clear(); // Clears previous rounds winners.
        }
        ArrayList<Player> tempPlayerHashList = convertLHStoArrayList(tempPlayersHash);
        //System.out.println(tempPlayerHashList.size());
        //debugPrintAll(tempPlayerHashList);
        tempMatchHash.add(tempPlayerHashList.get(0)); // Adds player at position zero in list to current match
        tempMatchHash.add(tempPlayerHashList.get(1)); // Adds player at position zero in list to current match
        //debugPrintAll(convertLHStoArrayList(tempMatchHash));
        tempPlayerHashList.remove(0);
        tempPlayerHashList.remove(0);
        //debugPrintAll(tempPlayerHashList);
        //System.out.println(tempPlayerHashList.size());
        tempPlayersHash.clear();
        for(int x = 0; x < tempPlayerHashList.size(); x++)
        {
            tempPlayersHash.add(tempPlayerHashList.get(x));
        }


        //Adding temporary hash to current hash for players
        //debugPrintAll(convertLHStoArrayList(tempPlayersHash));
        ArrayList<Player> insertPlayer = convertLHStoArrayList(tempPlayersHash);
        //debugPrintAll(insertPlayer);
        for(int x = 0; x < insertPlayer.size(); x++)
        {
            currentPlayersHash.add(insertPlayer.get(x));
        }

        //Adding temporary hash to current hash for Match
       //debugPrintAll(convertLHStoArrayList(tempMatchHash));
        ArrayList<Player> insertMatch = convertLHStoArrayList(tempMatchHash);
        //debugPrintAll(insertMatch);
        for(int x = 0; x < insertMatch.size(); x++)
        {
            currentMatchHash.add(insertMatch.get(x));
        }





        //currentPlayersHash = tempPlayersHash;
        //currentMatchHash = tempMatchHash;
        //tempPlayersHash.clear();
        //tempWinnersHash.clear();
        //tempMatchHash.clear();

        return currentMatchHash;
    }


    public static void testList()
    {
        System.out.println("Current match players are: " + createNewMatchList());
        determineMatch(1);
        System.out.println("Current winners of this round so far: " + currentWinners);
        System.out.println("Current match players are: " + createNewMatchList());
        determineMatch(1);
        System.out.println("Current winners of this round so far: " + currentWinners);
        System.out.println("Current match players are: " + createNewMatchList());
        determineMatch(1);
        System.out.println("Current winners of this round so far: " + currentWinners);
        System.out.println("Current match players are: " + createNewMatchList());
        determineMatch(1);
        System.out.println("Current winners of this round so far: " + currentWinners);

    }

    public static String createNewMatchList() // most likely will be endpoint
    {

        currentPlayersList = convertStringToList(currentPlayers); // Takes data from table and stores in list
        currentWinnersList = convertStringToList(currentWinners); // Takes data from table and stores in list
        if(currentPlayersList.size() == 0) // Checks if current round has any players left and if not creates new round with winners.
        {
            currentPlayersList = currentWinnersList; // Sets current round players as last rounds winners
            currentWinnersList.clear(); // Clears previous rounds winners.
        }
        currentMatchList.add(currentPlayersList.get(0)); // Adds player at position zero in list to current match
        currentMatchList.add(currentPlayersList.get(1)); // Adds player at position one in list to current match
        currentPlayersList.remove(0); // removes first ID
        currentPlayersList.remove(0); // removes second ID
        currentMatch = convertListToString(currentMatchList); // REPLACE WITH JPA STUFF -- Sets current match field to current match list
        currentPlayers = convertListToString(currentPlayersList); // ^^^
        currentPlayersList.clear(); // Clears list
        currentMatchList.clear(); // Clears list
        return currentMatch;
    }

    public static void determineMatch(int winner)
    {
        currentMatchList = convertStringToList(currentMatch); // Takes data from table and stores in list
        currentWinnersList = convertStringToList(currentWinners); // Takes data from table and stores in list
        currentWinnersList.add(currentMatchList.get(winner));
        currentMatchList.clear(); // Clears list
        currentMatch = convertListToString(currentMatchList);
        currentWinners = convertListToString(currentWinnersList);
        currentWinnersList.clear(); // Clears list
    }


    public static String convertListToString(List<Integer> listToConvert)
    {
        String result = "[";
        for(int i = 0; i < listToConvert.size(); i++)
        {
            if(i == listToConvert.size() - 1)
            {
                result += listToConvert.get(i) + "]";
            } else {
                result += listToConvert.get(i) + ",";
            }
        }
        return result;
    }

    public static List<Integer> convertStringToList(String currentPlayers)
    {
        String[] items = currentPlayers.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        int[] currentPlayersArray = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                currentPlayersArray[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {
                //NOTE: write something here if you need to recover from formatting errors
            };
        }

        List<Integer> myList = Arrays.stream(currentPlayersArray).boxed().collect(Collectors.toList());

        return myList;

    }

}