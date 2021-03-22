/*
===============================================================
RobotControllerMapper.java
implements the business logic by handling messages received on the cmdsocket-8091

===============================================================
*/
package iss2021_resumablebw.wenv;

import iss2021_resumablebw.interaction.IssObserver;
import iss2021_resumablebw.supports.IssCommSupport;
import org.json.JSONException;
import org.json.JSONObject;

public class RobotInputController implements IssObserver {
    private RobotResumableBoundaryLogic robotBehaviorLogic;
    private IssCommSupport commSupport;

    public RobotInputController(IssCommSupport support, boolean usearil, boolean doMap) {
        commSupport = support;
        robotBehaviorLogic = new RobotResumableBoundaryLogic(support, usearil, doMap);
    }

    public String doBoundary() {
        return robotBehaviorLogic.doBoundaryInit();
    }

    @Override
    public void handleInfo(String infoJson) {
        try {
            handleInfo(new JSONObject(infoJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void handleInfo(JSONObject infoJson) {
        if (infoJson.has("endmove")) handleEndMove(infoJson);
        else if (infoJson.has("sonarName")) handleSonar(infoJson);
        else if (infoJson.has("collision")) handleCollision(infoJson);
        else if (infoJson.has("robotcmd")) handleGuiComand(infoJson);
    }

    protected void handleSonar(JSONObject sonarinfo) {
        try {
            String sonarname = (String) sonarinfo.get("sonarName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            int distance = (Integer) sonarinfo.get("distance");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //System.out.println("RobotInputController | handleSonar:" + sonarname + " distance=" + distance);
    }

    protected void handleCollision(JSONObject collisioninfo) {
        //we should handle a collision  when there are moving obstacles
        //in this case we could have a collision even if the robot does not move
        //String move   = (String) collisioninfo.get("move");
        //System.out.println("RobotInputController | handleCollision move=" + move  );
    }

    protected void handleEndMove(JSONObject endmove) {
        String answer = null;
        try {
            answer = (String) endmove.get("endmove");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String move = null;   //moveForward, ...
        try {
            move = (String) endmove.get("move");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //System.out.println("RobotInputController | handleEndMove:" + move + " answer=" + answer);
        switch (answer) {
            case "true":
                robotBehaviorLogic.boundaryStep(move, false);
                break;
            case "false":
                robotBehaviorLogic.boundaryStep(move, true);
                break;
            case "halted":
                //System.out.println("RobotInputController | handleEndMove to do halt");
                break;
            case "notallowed":
                //System.out.println("RobotInputController | handleEndMove to do notallowed");
                break;
            default:
                //System.out.println("RobotInputController | handleEndMove IMPOSSIBLE answer for move=" + move);
        }
    }

    protected void handleGuiComand(JSONObject robotcmd) {
        String comand = (String) robotcmd.get("robotcmd");
        if (comand.equals("STOP")) {
            robotBehaviorLogic.stopWalk();
        } else {
            robotBehaviorLogic.resumeWalk();
        }
    }
}
