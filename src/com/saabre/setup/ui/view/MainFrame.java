/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view;

import com.alee.extended.progress.WebProgressOverlay;
import com.alee.laf.button.WebButton;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.toolbar.ToolbarStyle;
import com.alee.laf.toolbar.WebToolBar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Lifaen
 */
public class MainFrame extends JFrame {
    
    // -- Attributes --
    
    private ResultPanel panel;
    
    WebButton itemLoad;
    WebButton itemRun;
    
    WebProgressOverlay progressOverlay;
    
    // -- Constructors --
    
    public MainFrame()
    {
        // Set main layout --
        setLayout(new BorderLayout());
        setSize(800, 600);
        
        /* Load menubar --
        WebMenuBar menubar = new WebMenuBar();
        
            WebMenu menuFile = new WebMenu("Fichier");
            
                itemLoad = new WebMenuItem("Charger");
                itemLoad.addActionListener(new LoadListener());
                
                itemRun = new WebMenuItem("Lancer");
                itemRun.addActionListener(new RunListener());
                itemRun.setEnabled(false);
                
                menuFile.add(itemLoad);
                menuFile.add(itemRun);
            
            menubar.add(menuFile);
            
        add(menubar, BorderLayout.NORTH);
        //*/
        
        //* Load actionbar --
        WebToolBar toolbar = new WebToolBar();
        
        toolbar.setToolbarStyle(ToolbarStyle.attached);
        toolbar.setFloatable(false);
            
            itemLoad = new WebButton("Charger");
            itemLoad.addActionListener(new LoadListener());

            itemRun = new WebButton("Lancer");
            itemRun.addActionListener(new RunListener());
            itemRun.setEnabled(false);
                
            toolbar.add(itemLoad);
            toolbar.add(itemRun);
            
        add(toolbar, BorderLayout.NORTH);
        //*/
        
        // Load ResultPanel --
        panel = new ResultPanel();
        
        progressOverlay = new WebProgressOverlay();
        progressOverlay.setProgressWidth(35);
        
        WebScrollPane scrollPanel = new WebScrollPane(panel, false, true);
        scrollPanel.setPreferredSize(new Dimension(0, 0));
        scrollPanel.getVerticalScrollBar().setUnitIncrement(50);
        
        add(scrollPanel, BorderLayout.CENTER);
        progressOverlay.setComponent(scrollPanel);
        add(progressOverlay);
        
        // Events --
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //* Shortcut --
        onLoad();
        //*/
    }
    
    // -- Events --

    private class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {            
            onLoad();
        }
    }

    private class RunListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            onRun();
        }
    }
    
    // -- Methods --
    
    private void onLoad()
    {
        new Thread() {

            @Override
            public void run() 
            {
                progressOverlay.setShowLoad(true);
                itemLoad.setEnabled(false);

                panel.load();
                itemRun.setEnabled(true);

                progressOverlay.setShowLoad(false);
            }

        }.start();
    }
    
    private void onRun()
    {
        new Thread() {

            @Override
            public void run() 
            {
                panel.run();
                itemRun.setEnabled(false);
            }

        }.start();
    }
    
}
