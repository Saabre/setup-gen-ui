/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.component;

import com.alee.extended.panel.WebCollapsiblePane;
import com.alee.laf.panel.WebPanel;
import com.saabre.setup.helper.NameHelper;
import javax.swing.SpringLayout;

/**
 *
 * @author Lifaen
 */
public class CollapsibleListPanel extends WebPanel {
    
    // -- Attributes --
    
    private final WebPanel listPanel;
    private final SpringLayout layout;
    private WebPanel lastPanel = null;
    
    // -- Constructors --
    
    public CollapsibleListPanel()
    {
        listPanel = new WebPanel();
        layout = new SpringLayout();
        listPanel.setLayout(layout);
        add(listPanel);
    }
    
    // -- Methods --
    
    public void addPanel(String name, WebPanel panel)
    {
        // Configure collapsible panel --
        WebCollapsiblePane newPanel;        
        newPanel = new WebCollapsiblePane(name, panel);
        
        newPanel.setExpanded(false);
        newPanel.setMinimumWidth(300);
        
        listPanel.add(newPanel);
        listPanel.updateUI();
        
        // Configure panel location --
        
        if(lastPanel == null) // First module --
        {
            layout.putConstraint(
                SpringLayout.NORTH, newPanel,
                5,
                SpringLayout.NORTH, listPanel);
        }
        else // Other modules --
        {
            layout.putConstraint(
                SpringLayout.NORTH, newPanel,
                5,
                SpringLayout.SOUTH, lastPanel);
        }
        
        layout.putConstraint(
            SpringLayout.HORIZONTAL_CENTER, newPanel, 
            0, 
            SpringLayout.HORIZONTAL_CENTER, listPanel);

        lastPanel = newPanel;
    }
    
    
    
}
