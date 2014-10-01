/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.operation;

import com.alee.laf.text.WebTextArea;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Lifaen
 */
public abstract class ConsoleResultPanel extends OperationResultPanel
{
    // -- Attributes --
    
    protected final WebTextArea text;
    
    // -- Constructor --
    
    public ConsoleResultPanel()
    {
        Font f = new Font("Monospaced", Font.PLAIN, 12);
        
        text = new WebTextArea();
        text.setForeground(Color.white);
        text.setBackground(Color.black);
        text.setFont(f);
        add(text);
    }
}
