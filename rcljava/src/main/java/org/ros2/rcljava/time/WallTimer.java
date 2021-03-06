/* Copyright 2017 Esteve Fernandez <esteve@apache.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ros2.rcljava.time;

public interface WallTimer extends AutoCloseable {

    long getTimerPeriodNS();

    void setTimerPeriodNS(long period);

    boolean isReady();

    boolean isCanceled();

    void cancel();

    void reset();

    long timeSinceLastCall();

    long timeUntilNextCall();

    WallTimerCallback getCallback();

    void callTimer();

    /**
     * Safely destroy the underlying ROS2 publisher structure.
     */
    void dispose();

}
