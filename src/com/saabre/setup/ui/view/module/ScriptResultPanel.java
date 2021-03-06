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
import com.saabre.setup.helper.NameHelper;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.module.script.ScriptModule;
import com.saabre.setup.module.script.ScriptOperation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

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
