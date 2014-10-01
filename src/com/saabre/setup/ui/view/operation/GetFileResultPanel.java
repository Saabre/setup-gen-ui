/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.operation;

import com.saabre.setup.helper.FileHelper;
import com.saabre.setup.operation.remote.GetFile;
import java.io.File;

/**
 *
 * @author Lifaen
 */
public class GetFileResultPanel extends ConsoleResultPanel implements GetFile.Listener {

    private int fileCount;
    
    public GetFileResultPanel(GetFile operation) 
    {
        super();
        fileCount = 0;
        operation.setListener(this);
    }

    @Override
    public void onFileGot(File file) {
        String size = FileHelper.byteCountToReadable(file.length(), true);
        text.append((fileCount == 0 ? "" : "\n") + file.getName() +" arrived (" + size +")");
        fileCount++;
    }
}
