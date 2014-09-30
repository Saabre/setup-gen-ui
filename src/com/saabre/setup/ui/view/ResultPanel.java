/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Lifaen
 */
public class ResultPanel extends JPanel implements MainController.Listener 
{
    // -- Attributes --
    
    private MainController mainController;
    private ConfigController configController;
    private List<Module> moduleList;
    private CollapsibleListPanel moduleListPanel;
    
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
        
    }

    @Override
    public void onModuleEnd() {
        
    }

    
    
}
