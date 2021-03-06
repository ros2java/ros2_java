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

package org.ros2.rcljava.node.service;

import org.ros2.rcljava.internal.message.Message;
import org.ros2.rcljava.internal.service.MessageService;
import org.ros2.rcljava.node.Node;

/**
 * Interface of Service Server.
 *
 * @param <T> Service Type.
 */
public interface Service<T extends MessageService> extends AutoCloseable {

    String SCHEME = "rosservice://";

    /**
     * Safely destroy the underlying ROS2 Service structure.
     */
    void dispose();

    ServiceCallback<?, ?> getCallback();

    /**
     * @return Return owner Node.
     */
    Node getNode();

    Class<? extends Message> getRequestType();

    Class<? extends Message> getResponseType();

    String getServiceName();

    Class<T> getServiceType();
}
