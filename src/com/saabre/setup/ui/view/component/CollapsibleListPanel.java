/*

The MIT License (MIT)

Copyright (c) 2015 Saabre

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

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
