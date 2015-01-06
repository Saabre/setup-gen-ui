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

package com.saabre.setup.ui.view.module;

import com.saabre.setup.helper.FileHelper;
import com.saabre.setup.system.base.Profile;
import com.saabre.setup.module.analysis.AnalysisModule;
import com.saabre.setup.module.analysis.AnalysisOperation;

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
