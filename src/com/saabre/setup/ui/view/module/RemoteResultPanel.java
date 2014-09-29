/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.module;

import com.alee.laf.label.WebLabel;
import com.saabre.setup.system.module.remote.RemoteModule;

/**
 *
 * @author Lifaen
 */
public class RemoteResultPanel extends ModuleResultPanel {

    public RemoteResultPanel(RemoteModule module) 
    {
        add(new WebLabel("Test"));
    }
    
}
