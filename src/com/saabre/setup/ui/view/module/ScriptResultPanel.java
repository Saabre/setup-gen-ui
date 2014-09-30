/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.module;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.saabre.setup.helper.NameHelper;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.system.module.script.ScriptModule;
import com.saabre.setup.system.module.script.ScriptOperation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

/**
 *
 * @author Lifaen
 */
public class ScriptResultPanel extends ModuleResultPanel implements ScriptModule.Listener{
    
    // -- Attributes --
   
    private ScriptModule module;
    
    private List<ProfilePanel> profileList;
    private int currentProfileIndex;
    
    private static Color RED = new Color(172, 0, 0);
    private static Color GREEN = new Color(0, 122, 23);
    
    // -- Constructors --
    
    public ScriptResultPanel(ScriptModule module) 
    {
        // Initialisation --
        this.module = module;
        module.setListener(this);
        
        // Progress --
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        this.profileList = new ArrayList<>();
        this.currentProfileIndex = 0;
        
        for(Profile profile : module.getProfileList())
        {
            ProfilePanel panel = new ProfilePanel(profile);
            profileList.add(panel);
            add(panel);
        }
    }
    
    // -- Profile Panel --
    
    private class ProfilePanel extends WebPanel
    {
        private Profile profile;
        private WebLabel label;
        private List<WebLabel> operationList = new ArrayList<>();
        private int currentOperationIndex = 0;
        
        public ProfilePanel(Profile profile)
        {
            this.profile = profile;
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            
            // Label --
            label = new WebLabel(NameHelper.upper(profile.getName()));
            add(label);
            
            // Operations --
            for(ScriptOperation operation : profile.getScriptOperationList())
            {
                WebLabel label = new WebLabel(" > " + operation.getType());
                label.setBorder(BorderFactory.createEmptyBorder(2,10,2,10));
                operationList.add(label);
                add(label);
            }
        }
        
        public void onEnd()
        {
            label.setForeground(GREEN);
        }
        
        public void onOperationEnd()
        {
            operationList.get(currentOperationIndex).setForeground(GREEN);
            currentOperationIndex++;
        }
    }
    
    // -- Events --
    
    @Override
    public void onProfileStart(Profile profile) {
        
    }

    @Override
    public void onProfileEnd() 
    {
        profileList.get(currentProfileIndex).onEnd();
        currentProfileIndex++;
    }

    @Override
    public void onOperationStart(ScriptOperation operation) {
    }

    @Override
    public void onOperationEnd() {
        profileList.get(currentProfileIndex).onOperationEnd();
    }
    
}
