/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.component;

import com.alee.extended.panel.WebCollapsiblePane;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
    private final GridBagLayout layout;
    
    private GridBagConstraints rule;
    private int index;
    
    private WebPanel lastPanel = null;
    
    // -- Constructors --
    
    public CollapsibleListPanel()
    {
        panelList = new ArrayList<>();
        layout = new GridBagLayout();
        
        rule = new GridBagConstraints();
        rule.weighty = 0;
        
        index = 0;
        setLayout(layout);
    }
    
    // -- Methods --
    

    private void addItem(Component newPanel)
    {
        rule.gridy = index;
        add(newPanel, rule);
        index++;
    }

    public void setFinalConstraint() {
        rule.weighty = 2;
        rule.fill = GridBagConstraints.VERTICAL;
        addItem(new WebPanel());
    }
    
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
        
        addItem(newPanel);        
        panelList.add(panel);
        updateUI();
    }
    
    public void addSeparator(String name) {
        WebPanel panel = new WebPanel();
        WebLabel label = new WebLabel(name, WebLabel.CENTER);
        
        label.setBoldFont();
        
        panel.add(label);
        addItem(panel);  
    }
    
    // -- Getters and setters --
    
    public WebPanel getPanel(int index)
    {
        return panelList.get(index);
    }
    
}
