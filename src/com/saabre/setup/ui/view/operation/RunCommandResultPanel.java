/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.operation;

import com.alee.laf.label.WebLabel;
import com.alee.laf.text.WebTextArea;
import com.saabre.setup.operation.remote.RunCommand;
import java.awt.Color;

/**
 *
 * @author Lifaen
 */
public class RunCommandResultPanel extends OperationResultPanel implements RunCommand.Listener {

    private WebTextArea text;
    
    public RunCommandResultPanel(RunCommand runCommand) 
    {
        text = new WebTextArea();
        text.setForeground(Color.white);
        text.setBackground(Color.black);
        add(text);
        
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
