/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.module;

import com.alee.extended.progress.WebStepProgress;
import com.alee.laf.label.WebLabel;
import com.saabre.setup.helper.NameHelper;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.system.module.script.ScriptModule;
import com.saabre.setup.system.module.script.ScriptOperation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.border.Border;

/**
 *
 * @author Lifaen
 */
public class ScriptResultPanel extends ModuleResultPanel implements ScriptModule.Listener{
    
    // -- Attributes --
   
    private ScriptModule module;
    
    private List<WebLabel> labelList;
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
        this.labelList = new ArrayList<>();
        this.currentProfileIndex = 0;
        
        for(Profile profile : module.getProfileList())
        {
            WebLabel label = new WebLabel(NameHelper.upper(profile.getName())); 
            Border paddingBorder = BorderFactory.createEmptyBorder(2,0,2,0);
            
            label.setBorder(paddingBorder);
            label.setForeground(RED);
            
            labelList.add(label);
            add(label);
        }
    }
    
    // -- Events --
    
    @Override
    public void onProfileStart(Profile profile) {
        
    }

    @Override
    public void onProfileEnd() 
    {
        labelList.get(currentProfileIndex).setForeground(GREEN);
        currentProfileIndex++;
    }

    @Override
    public void onOperationStart(ScriptOperation operation) {
        
    }

    @Override
    public void onOperationEnd() {
        
    }
    
}
