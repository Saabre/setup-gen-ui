/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.operation;

import com.saabre.setup.operation.remote.RunCommand;

/**
 *
 * @author Lifaen
 */
public class RunCommandResultPanel extends ConsoleResultPanel implements RunCommand.Listener {

    
    public RunCommandResultPanel(RunCommand runCommand) 
    {
        super();
        runCommand.setListener(this);
    }

    @Override
    public void onOutputNewLine(String line) {
        text.append(line+"\n");
    }

    @Override
    public void onExit(int exitStatus) {
        if(exitStatus < 0)
            text.append("Done, but exit status not set!");
        else if(exitStatus > 0)
            text.append("Done, but with error!");
        else
            text.append("Done!");
    }
    
}
