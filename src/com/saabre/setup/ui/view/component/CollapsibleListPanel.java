/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.component;

import com.alee.extended.panel.WebCollapsiblePane;
import com.alee.laf.panel.WebPanel;
import com.saabre.setup.helper.NameHelper;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SpringLayout;

/**
 *
 * @author Lifaen
 */
public class CollapsibleListPanel extends WebPanel {
    
    // -- Attributes --
    
    private final List<WebPanel> panelList;
    private final SpringLayout layout;
    private WebPanel lastPanel = null;
    
    // -- Constructors --
    
    public CollapsibleListPanel()
    {
        panelList = new ArrayList<>();
        layout = new SpringLayout();
        setLayout(layout);
    }
    
    // -- Methods --
    
    public static class Options
    {
        public String name = "";
        public boolean expanded = false;
        public int size = 300;
    }
    
    public void addPanel(WebPanel panel, Options options)
    {
        // Configure collapsible panel --
        WebCollapsiblePane newPanel;        
        newPanel = new WebCollapsiblePane(options.name, panel);
        
        newPanel.setExpanded(options.expanded);
        newPanel.setMinimumWidth(options.size);
        
        add(newPanel);
        panelList.add(panel);
        updateUI();
        
        // Configure panel location --
        
        if(lastPanel == null) // First module --
        {
            layout.putConstraint(
                SpringLayout.NORTH, newPanel,
                5,
                SpringLayout.NORTH, this);
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
            SpringLayout.HORIZONTAL_CENTER, this);
        
        lastPanel = newPanel;
    }
    
    public void setFinalConstraint()
    {
        if(lastPanel != null)
        {
            layout.putConstraint(
                SpringLayout.SOUTH, this, 
                0, 
                SpringLayout.SOUTH, lastPanel);
        }
    }
    
    // -- Getters and setters --
    
    public WebPanel getPanel(int index)
    {
        return panelList.get(index);
    }
    
}
