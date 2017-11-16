/*
 * Copyright 2014 http://Bither.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bither.util;



import tokenxx.com.xrandomdemo.App;

public class UIUtil {
    public static final int SwitchToAbcActionId = 18203;
    public static final int SwitchTo123ActionId = 18204;

    public static final int dip2pix(float dip) {
        final float scale = App.getApp().getResources()
                .getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    public static int getScreenWidth() {
        return  App.getApp().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return  App.getApp().getResources().getDisplayMetrics().heightPixels;
    }


}
