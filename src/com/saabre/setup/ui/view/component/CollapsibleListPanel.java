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
        setLayout(layout);
        
        rule = new GridBagConstraints();
        setFinalConstraint();
        rule.weighty = 0;
        rule.weightx = 2;
        rule.fill = GridBagConstraints.BOTH;
        
        index = 0;
    }
    
    // -- Methods --
    

    private void addItem(Component newPanel)
    {
        rule.gridy = index;
        add(newPanel, rule);
        index++;
    }

    private void setFinalConstraint() {
        rule.weighty = 2;
        rule.fill = GridBagConstraints.VERTICAL;
        rule.gridy = 100;
        add(new WebPanel(), rule);
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
        
        newPanel.setMinimumWidth(options.size);
        newPanel.setExpanded(false);
        
        addItem(newPanel);        
        panelList.add(panel);
        updateUI();
        
        newPanel.setExpanded(options.expanded, true);
    }
    
    public void addSeparator(String name) {
        WebPanel panel = new WebPanel();
        WebLabel label = new WebLabel(name, WebLabel.CENTER);
        
        label.setBoldFont();
        
        panel.add(label);
        addItem(panel);  
    }
    
    public void setExpandedForAll(boolean expanded) 
    {
        for(Component component : getComponents())
        {
            if(component instanceof WebCollapsiblePane)
            {
                WebCollapsiblePane panel = (WebCollapsiblePane) component;
                panel.setExpanded(expanded, true);
            }
        }
    }

    public void setExpandedForIndex(boolean expanded, int index) {
        Component component = getComponent(index);
        if(component instanceof WebCollapsiblePane)
        {
            WebCollapsiblePane panel = (WebCollapsiblePane) component;
            panel.setExpanded(expanded, true);
        }
    }
    
    // -- Getters and setters --
    
    public WebPanel getPanel(int index)
    {
        return panelList.get(index);
    }
    
}
