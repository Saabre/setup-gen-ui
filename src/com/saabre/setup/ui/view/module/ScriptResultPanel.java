/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.module;

import com.alee.extended.progress.WebStepProgress;
import com.alee.laf.label.WebLabel;
import com.saabre.setup.system.module.script.ScriptModule;

/**
 *
 * @author Lifaen
 */
public class ScriptResultPanel extends ModuleResultPanel{
    
    public ScriptResultPanel(ScriptModule module) 
    {
        add(new WebLabel("Test")); 
    }
    
}
