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

package com.saabre.setup.ui.view;

import com.alee.laf.panel.WebPanel;
import com.saabre.setup.helper.NameHelper;
import com.saabre.setup.system.base.Module;
import com.saabre.setup.system.controller.ConfigController;
import com.saabre.setup.system.controller.MainController;
import com.saabre.setup.module.analysis.AnalysisModule;
import com.saabre.setup.module.remote.RemoteModule;
import com.saabre.setup.module.script.ScriptModule;
import com.saabre.setup.ui.view.component.CollapsibleListPanel;
import com.saabre.setup.ui.view.component.CollapsibleListPanel.Options;
import com.saabre.setup.ui.view.module.AnalysisResultPanel;
import com.saabre.setup.ui.view.module.RemoteResultPanel;
import com.saabre.setup.ui.view.module.ScriptResultPanel;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ResultPanel extends JPanel implements MainController.Listener 
{
    // -- Attributes --
    
    private MainController mainController;
    private ConfigController configController;
    private List<Module> moduleList;
    private CollapsibleListPanel moduleListPanel;
    private Module currentModule;
    
    // -- Constructor --
    
    public ResultPanel()
    {
        // Set main layout --
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
        
        // Set module panel --
        moduleListPanel = new CollapsibleListPanel();
        add(moduleListPanel);
        
        // Set background --
    }    
    
    private void addModule(Module module) 
    {
        // Initialisation --
        WebPanel internalPane = null;
        Options options = new CollapsibleListPanel.Options();
        
        options.name = NameHelper.upper(module.getName());
        options.size = 500;
        options.expanded = true;
        
        // Test module type --
        if(module instanceof ScriptModule)
        {
            internalPane = new ScriptResultPanel((ScriptModule) module);
            options.expanded = false;
        }
        else if(module instanceof RemoteModule)
        {
            internalPane = new RemoteResultPanel((RemoteModule) module);
        }
        else if(module instanceof AnalysisModule)
        {
            internalPane = new AnalysisResultPanel((AnalysisModule) module);
            options.expanded = false;
        }
        
        // Security check --
        if(internalPane == null)
            return;
        
        moduleListPanel.addPanel(internalPane, options);
    }
    
    // -- Core methods --
    
    public void load()
    {
        try {
            mainController = new MainController();
            mainController.setListener(this);
            mainController.load();
            
            moduleList = mainController.getModuleList();
            
            for(Module module : moduleList)
            {
                addModule(module);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Loaded !");
    }
    
    public void run()
    {
        try 
        {
            mainController.run();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        
        System.out.println("End !");
    }

    // -- Listener -- 
    
    @Override
    public void onConfigLoading(ConfigController controller) {
        configController = controller;
    }

    @Override
    public void onModuleStart(Module module) {
        currentModule = module;
    }

    @Override
    public void onModuleEnd() {
        if(currentModule instanceof AnalysisModule)
        {
            moduleListPanel.setExpandedForIndex(true, 3);
        }
    }

    
    
}
