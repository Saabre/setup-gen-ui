/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view;

import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lifaen
 */
public class MainFrame extends JFrame {
    
    // -- Attributes --
    
    private ResultPanel panel;
    
    WebMenuItem itemLoad;
    WebMenuItem itemRun;
    
    // -- Constructors --
    
    public MainFrame()
    {
        // Set main layout --
        setLayout(new BorderLayout());
        setSize(800, 600);
        
        // Load menubar --
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
        
        // Load ResultPanel --
        panel = new ResultPanel();
        add(panel, BorderLayout.CENTER);
        
        // Events --
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //* Shortcut --
        SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
                panel.load();
                panel.run();
            }
        } ); 
        //*/
    }
    
    // -- Methods --
    
    // -- Events --

    private class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.load();
            itemRun.setEnabled(true);
        }
    }

    private class RunListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            panel.run();
        }
    }
    
}
