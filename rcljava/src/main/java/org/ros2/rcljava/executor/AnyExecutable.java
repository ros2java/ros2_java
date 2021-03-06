/* Copyright 2017-2018 Mickael Gaillard <mick.gaillard@gmail.com>
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

package org.ros2.rcljava.executor;

import org.ros2.rcljava.node.Node;
import org.ros2.rcljava.node.service.Client;
import org.ros2.rcljava.node.service.Service;
import org.ros2.rcljava.node.topic.Subscription;
import org.ros2.rcljava.time.WallTimer;

/**
 * Process to check.
 */
public class AnyExecutable {

    // Only one of the following pointers will be set.
    protected Subscription<?> subscription;
    protected WallTimer timer;
    protected Service<?> service;
    protected Client<?> client;

    // These are used to keep the scope on the containing items
    protected Node node;

}
