/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.operation;

import com.alee.laf.label.WebLabel;
import com.saabre.setup.operation.remote.RunCommand;

/**
 *
 * @author Lifaen
 */
public class RunCommandResultPanel extends OperationResultPanel {

    public RunCommandResultPanel(RunCommand runCommand) {
        add(new WebLabel("Test"));
    }
    
}
