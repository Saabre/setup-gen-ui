/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.module;

import com.alee.laf.panel.WebPanel;
import com.saabre.setup.ui.view.component.CollapsibleListPanel;

/**
 *
 * @author Lifaen
 */
public class ModuleResultPanel extends WebPanel {
    
    // -- Attributes --
    
    protected CollapsibleListPanel profileListPanel;
    
    // -- Methods --
    
    protected void loadProfileListPanel()
    {
        profileListPanel = new CollapsibleListPanel();
        add(profileListPanel);
    }
}
