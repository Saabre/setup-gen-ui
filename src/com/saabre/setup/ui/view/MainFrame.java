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
        setSize(1024, 850);
        
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
