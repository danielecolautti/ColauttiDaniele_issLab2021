package iss2021_resumablebw.wenv;

public class RobotMovesInfo {
    private boolean  doMap = false;
    private String journey = "";

    public RobotMovesInfo(boolean doMap){
        this.doMap = doMap;

    }
    public void showRobotMovesRepresentation(  ){
        System.out.println( "journey=" + journey );
    }

    public void showRobotMovesRepresentationIfAtHome(  ) {
        //System.out.println(journey + " - " + journey.split("l").length);
        if (!journey.equals("") &&
                //journey.length() % 2 != 0 ||
                journey.split("l").length > 0 &&
                journey.split("l").length % 4 == 0 &&
                journey.charAt(journey.length() - 1) == 'l')
            //return;
        //String firstHalf = journey.substring(0, journey.length()/2);
        //String secondHalf = journey.substring(journey.length()/2);
        //System.out.println(journey + " - " +firstHalf + " : " + secondHalf);
        //if (firstHalf.equals(secondHalf))
            System.out.println( "I'm come back to home! My moves history: " + journey);
    }

    public String getMovesRepresentationAndClean(  ){
        String answer = journey;
        journey       = "";
        return answer;
    }

    public String getMovesRepresentation(  ){
        return journey;
    }

    public void updateRobotMovesRepresentation(String move ){
        journey = journey + move;

        showRobotMovesRepresentationIfAtHome();
    }


}
