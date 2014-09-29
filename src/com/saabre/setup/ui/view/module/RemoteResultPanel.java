/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.module;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebTextField;
import com.saabre.setup.helper.NameHelper;
import com.saabre.setup.operation.remote.RunCommand;
import com.saabre.setup.operation.remote.SendFile;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.system.module.remote.RemoteModule;
import com.saabre.setup.system.module.remote.RemoteOperation;
import com.saabre.setup.ui.view.component.CollapsibleListPanel;
import com.saabre.setup.ui.view.component.CollapsibleListPanel.Options;
import com.saabre.setup.ui.view.operation.OperationResultPanel;
import com.saabre.setup.ui.view.operation.RunCommandResultPanel;
import com.saabre.setup.ui.view.operation.SendFileResultPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

/**
 *
 * @author Lifaen
 */
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
        add(profileListPanel, rule);
        
        // Load Profile List --
        for(Profile profile : module.getProfileList())
        {
            ProfilePanel panel = new ProfilePanel(profile);
            CollapsibleListPanel.Options options = new CollapsibleListPanel.Options();

            options.name = NameHelper.upper(profile.getName());
            options.expanded = true;
            options.size = 400;

            profileListPanel.addPanel(panel, options);
        }
        
        profileListPanel.setFinalConstraint();
    }
    
    // -- Profile Panel --
    
    private class ProfilePanel extends WebPanel
    {
        private Profile profile;
        public CollapsibleListPanel operationList = new CollapsibleListPanel();
        private int currentOperationIndex = 0;
        
        public ProfilePanel(Profile profile)
        {
            this.profile = profile;
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
            
            add(operationList);
            
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
                options.size = 300;

                operationList.addPanel(panel, options);
            }
            
            operationList.setFinalConstraint();
        }
        
        public void onOperationStart(RemoteOperation operation)
        {
            
        }

        private void onOperationEnd() {
            
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
        ProfilePanel panel = (ProfilePanel) profileListPanel.getPanel(currentProfileIndex);
        panel.onOperationStart(operation);
    }

    @Override
    public void onOperationEnd() {
        ProfilePanel panel = (ProfilePanel) profileListPanel.getPanel(currentProfileIndex);
        panel.onOperationEnd();
    }
    
}
