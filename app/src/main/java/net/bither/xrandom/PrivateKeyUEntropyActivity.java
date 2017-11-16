/*
 *
 *  * Copyright 2014 http://Bither.net
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *    http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package net.bither.xrandom;
import android.util.Log;
import java.util.ArrayList;
import tokenxx.com.xrandomdemo.R;


public class PrivateKeyUEntropyActivity extends UEntropyActivity {
    private static final int MinGeneratingTime = 5000;
    private GenerateThread generateThread;
    @Override
    Thread getGeneratingThreadWithXRandom(UEntropyCollector collector,
                                          String password) {
        generateThread = new GenerateThread(collector, password);
        return generateThread;
    }
    @Override
    void cancelGenerating(Runnable cancelRunnable) {
        generateThread.cancel(cancelRunnable);
    }

    @Override
    void didSuccess(Object obj) {
        setResult(RESULT_OK);
        finish();
        overridePendingTransition(0, R.anim.slide_out_bottom);
    }
    private class GenerateThread extends Thread{
        private double startProgress = 0.01;
        private long startGeneratingTime;
        private String password;
        private Runnable cancelRunnable;
        private UEntropyCollector entropyCollector;

        public GenerateThread(UEntropyCollector collector, String password) {
            this.password = password;
            entropyCollector = collector;
        }

        @Override
        public synchronized void start() {
            if (password == null) {
                throw new IllegalStateException("GenerateThread does not have password");
            }
            startGeneratingTime = System.currentTimeMillis();
            super.start();
            onProgress(startProgress);
        }

        public void cancel(Runnable cancelRunnable) {
            this.cancelRunnable = cancelRunnable;
        }

        private void finishGenerate() {
            if (password != null) {

                password = null;
            }
            entropyCollector.stop();
        }

        @Override
        public void run() {
            runWithService();
        }

        public void runWithService() {
            boolean success = false;
            final ArrayList<String> addressStrs = new ArrayList<String>();
            try {
                entropyCollector.start();
                XRandom xRandom = new XRandom(entropyCollector);

                byte [] bytes = new byte[16];
                xRandom.nextBytes(bytes);
                for (byte b :bytes){
                    Log.e("byte = ",b+"");
                }

                entropyCollector.stop();
                password = null;

                if (cancelRunnable != null) {
                    finishGenerate();
                    runOnUiThread(cancelRunnable);
                    return;
                }
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            finishGenerate();
            if (success) {
                while (System.currentTimeMillis() - startGeneratingTime < MinGeneratingTime) {

                }
                onProgress(1);
                onSuccess(addressStrs);

            } else {
                onFailed();
            }
        }
    }

}
