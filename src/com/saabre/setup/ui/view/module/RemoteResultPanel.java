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

package com.saabre.setup.ui.view.module;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextField;
import com.saabre.setup.helper.NameHelper;
import com.saabre.setup.operation.remote.RunCommand;
import com.saabre.setup.operation.remote.SendFile;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.module.remote.RemoteModule;
import com.saabre.setup.module.remote.RemoteOperation;
import com.saabre.setup.operation.remote.GetFile;
import com.saabre.setup.ui.view.component.CollapsibleListPanel;
import com.saabre.setup.ui.view.component.CollapsibleListPanel.Options;
import com.saabre.setup.ui.view.operation.GetFileResultPanel;
import com.saabre.setup.ui.view.operation.OperationResultPanel;
import com.saabre.setup.ui.view.operation.RunCommandResultPanel;
import com.saabre.setup.ui.view.operation.SendFileResultPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;

public class RemoteResultPanel extends ModuleResultPanel implements RemoteModule.Listener {

    // -- Attributes --
   
    private final RemoteModule module;
    private int currentProfileIndex;
    
    private final GridBagLayout         layout;
    private final WebLabel              hostLabel;
    private final WebTextField          hostValue;
    private final WebLabel              stateLabel;
    private final WebTextField          stateValue;    
    
    // -- Constructors --

    public RemoteResultPanel(RemoteModule module) 
    {
        // Initialisation --
        this.module = module;
        module.setListener(this);
        currentProfileIndex = 0;
        
        // Layout --
        layout = new GridBagLayout();
        setLayout(layout);
        setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        
        // Items --
        hostLabel = new WebLabel("Host");
        hostLabel.setMinimumWidth(50);
        
        hostValue = new WebTextField();
        hostValue.setEditable(false);
        
        stateLabel = new WebLabel("Status");
        stateLabel.setMinimumWidth(50);
        
        stateValue = new WebTextField("Off");
        stateValue.setEditable(false);
        
        GridBagConstraints rule = new GridBagConstraints();
        
        rule.gridx = 0;
        rule.gridy = 0;
        rule.weightx = 0;
        add(hostLabel, rule);
        
        rule.gridx = 1;
        rule.fill = GridBagConstraints.HORIZONTAL;
        rule.weightx = 2;
        add(hostValue, rule);
        
        rule.gridx = 0;
        rule.gridy = 1;
        rule.weightx = 0;
        add(stateLabel, rule);
        
        rule.gridx = 1;
        rule.fill = GridBagConstraints.HORIZONTAL;
        rule.weightx = 2;
        add(stateValue, rule);
        
        // Profile List --
        loadProfileListPanel();
        rule.gridx = 0;
        rule.gridy = 2;
        rule.gridwidth = 2;
        add(listPanel, rule);
        
        // Load Profile List --
        for(Profile profile : module.getProfileList())
        {
            listPanel.addSeparator(NameHelper.upper(profile.getName()));
            
            for(RemoteOperation operation : profile.getRemoteOperationList())
            {
                WebPanel panel;
            
                if(operation instanceof RunCommand)
                {
                    panel = new RunCommandResultPanel((RunCommand) operation);
                }
                else if(operation instanceof SendFile)
                {
                    panel = new SendFileResultPanel((SendFile) operation);
                }
                else if(operation instanceof GetFile)
                {
                    panel = new GetFileResultPanel((GetFile) operation);
                }
                else
                {
                    panel = new OperationResultPanel();
                }

                Options options = new CollapsibleListPanel.Options();

                try {
                    options.name = operation.getType() + ": " + operation.getTitle();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                options.expanded = true;
                options.size = 400;

                listPanel.addPanel(panel, options);
            }
        }
    }
    
    // Events --

    @Override
    public void onTryingConnection() {
        stateValue.setText("Trying to connect.");
        hostValue.setText(module.getUser() + "@" 
                + module.getHost() + ":" + module.getPort());
    }

    @Override
    public void onConnected() {
        stateValue.setText("Connected. Processing.");
    }

    @Override
    public void onDisconnected() {
        stateValue.setText("Disconnected. Processed.");
        
        listPanel.setExpandedForAll(false);
    }

    @Override
    public void onProfileStart(Profile profile) {
        
    }

    @Override
    public void onProfileEnd() {
        // profileListPanel.get(currentProfileIndex);
        currentProfileIndex++;
    }

    @Override
    public void onOperationStart(RemoteOperation operation) {
        
    }

    @Override
    public void onOperationEnd() {
        
    }
    
}
