/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saabre.setup.ui.view.module;

import com.alee.laf.label.WebLabel;
import com.saabre.setup.helper.FileHelper;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.system.module.analysis.AnalysisModule;
import com.saabre.setup.system.module.analysis.AnalysisOperation;
import java.awt.Dimension;

/**
 *
 * @author Lifaen
 */
public class AnalysisResultPanel extends ModuleResultPanel implements AnalysisModule.Listener {
    
    // -- Attributes --
    
    private final AnalysisModule module;
    
    private final WebViewPanel webView;

    // -- Constructors --
    
    public AnalysisResultPanel(AnalysisModule module) 
    {
        this.module = module;
        module.setListener(this);
        
        webView = new WebViewPanel();
        add(webView);
        
    }

    // -- Events --
    
    @Override
    public void onProfileStart(Profile profile) {
        
    }

    @Override
    public void onProfileEnd() {
        
    }

    @Override
    public void onReportBuilt(AnalysisOperation operation) {
        String path = "file:///"+ FileHelper.getAbsoluteAnalyisOutputFolder() +"Report.html";
        System.out.println(path);
        webView.loadURL(path);
    }
    
}
