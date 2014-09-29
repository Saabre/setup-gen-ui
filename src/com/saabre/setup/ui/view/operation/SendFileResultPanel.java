/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.operation;

import com.alee.laf.text.WebTextArea;
import com.saabre.setup.helper.FileHelper;
import com.saabre.setup.operation.remote.SendFile;
import java.awt.Color;
import java.io.File;

/**
 *
 * @author Lifaen
 */
public class SendFileResultPanel extends OperationResultPanel implements SendFile.Listener {

    private final WebTextArea text;
    private int fileCount;
    
    public SendFileResultPanel(SendFile operation) 
    {
        text = new WebTextArea();
        text.setForeground(Color.white);
        text.setBackground(Color.black);
        add(text);
        
        fileCount = 0;
        
        operation.setListener(this);
    }

    @Override
    public void onFileSent(File file) {
        String size = FileHelper.byteCountToReadable(file.length(), true);
        text.append((fileCount == 0 ? "" : "\n") + file.getName() +" sent (" + size +")");
        fileCount++;
    }
}
