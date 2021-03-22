/**
 * ConsoleGui
 *
 * @author AN - DISI - Unibo
 * ===============================================================
 * The user hoits a button and a message with the same name is
 * sent to the WEnv by using WEnvConnSupportNoChannel.sendMessage
 * ===============================================================
 */
package iss2021_resumablebw.consolegui;

import iss2021_resumablebw.interaction.IssObserver;
import iss2021_resumablebw.wenv.RobotInputController;

import java.util.Observable;
import java.util.Observer;

public class ConsoleGui implements Observer {
    private String[] buttonLabels = new String[]{"STOP", "RESUME"};
    private IssObserver controller;

    public ConsoleGui(IssObserver controller) {
        GuiUtils.showSystemInfo();
        ButtonAsGui concreteButton = ButtonAsGui.createButtons("", buttonLabels);
        concreteButton.addObserver(this);
        this.controller = controller;
    }

    public void update(Observable o, Object arg) {
        String move = arg.toString();
        String robotCmd = (move == "STOP") ? "{\"robotcmd\":\"STOP\" }" : "{\"robotcmd\":\"RESUME\" }";
        controller.handleInfo(robotCmd);
    }

    public static void main(String[] args) {
        RobotInputController robotInputController = new RobotInputController(null, true, false);
        ConsoleGui consoleGui = new ConsoleGui(robotInputController);
    }
}

