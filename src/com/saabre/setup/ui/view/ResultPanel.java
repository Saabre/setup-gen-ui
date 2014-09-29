/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view;

import com.alee.extended.panel.WebCollapsiblePane;
import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.saabre.setup.helper.NameHelper;
import com.saabre.setup.system.base.Module;
import com.saabre.setup.system.controller.ConfigController;
import com.saabre.setup.system.controller.MainController;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

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
    
    private WebPanel moduleListPanel;
    private SpringLayout moduleLayout;
    private WebPanel lastModulePanel = null;
    
    // -- Constructor --
    
    public ResultPanel()
    {
        // Set main layout --
        setLayout(new BorderLayout());
        
        // Set module panel --
        moduleListPanel = new WebPanel();
        moduleLayout = new SpringLayout();
        moduleListPanel.setLayout(moduleLayout);
        add(moduleListPanel);
    }    
    
    private void addModule(Module module) 
    {
        final WebPanel pane = new WebPanel();
        pane.add(new WebButton("Test"));
        
        final WebCollapsiblePane modulePane = new WebCollapsiblePane(
                NameHelper.upper(module.getName()), pane);
        
        modulePane.setExpanded(false);
        modulePane.setMinimumWidth(100);
        
        moduleListPanel.add(modulePane);
        moduleListPanel.updateUI();
        
        if(lastModulePanel == null) // First module --
        {
            moduleLayout.putConstraint(
                SpringLayout.NORTH, modulePane,
                5,
                SpringLayout.NORTH, moduleListPanel);
        }
        else // Other modules --
        {
            moduleLayout.putConstraint(
                SpringLayout.NORTH, modulePane,
                5,
                SpringLayout.SOUTH, lastModulePanel);
        }
        
        moduleLayout.putConstraint(
            SpringLayout.HORIZONTAL_CENTER, modulePane, 
            0, 
            SpringLayout.HORIZONTAL_CENTER, moduleListPanel);
        
        
        lastModulePanel = modulePane;
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
