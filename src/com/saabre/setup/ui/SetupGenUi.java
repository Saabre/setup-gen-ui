/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui;

import com.alee.laf.WebLookAndFeel;
import com.saabre.setup.helper.FileHelper;
import com.saabre.setup.ui.view.MainFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Lifaen
 */
public class SetupGenUi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // You should work with UI (including installing L&F) inside Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
                // Install WebLaF as application L&F
                WebLookAndFeel.install ();

                // -- Global configuration --
                FileHelper.setRootFolder("../setup-gen/data/");
                
                new MainFrame().setVisible(true);
            }
        } );
    }
    
}
