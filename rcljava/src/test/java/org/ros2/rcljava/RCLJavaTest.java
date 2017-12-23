/* Copyright 2016 Esteve Fernandez <esteve@apache.org>
 * Copyright 2016-2017 Mickael Gaillard <mick.gaillard@gmail.com>
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

package org.ros2.rcljava;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.ros2.rcljava.exception.NotInitializedException;
import org.ros2.rcljava.node.NativeNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@FixMethodOrder(MethodSorters.JVM)
public class RCLJavaTest extends AbstractRosTest {
    private static final Logger logger = LoggerFactory.getLogger(RCLJavaTest.class);

    @Before
    public void setUp() {
        // Disable default setUp.
    }

    @After
    public void tearDown() {
        // Disable default tearDown.
    }

    @Test
    public void testInit() {
        logger.debug(new Object(){}.getClass().getEnclosingMethod().getName());

        boolean test = true;

        try {
            Assert.assertEquals(false, RCLJava.isInitialized());
            this.initRCLjava();
            Assert.assertEquals(true, RCLJava.isInitialized());
        } catch (Exception e) {
            test = false;
        } finally {
            this.releaseRCLjava();
        }

        Assert.assertTrue("failed to initialize rclJava", test);
    }

    @Test
    public void testInitShutdown() {
        logger.debug(new Object(){}.getClass().getEnclosingMethod().getName());

        boolean test = true;

        try {
            this.initRCLjava();
            this.releaseRCLjava();
        } catch (Exception e) {
            test = false;
        }

        Assert.assertTrue("failed to shutdown rclJava", test);
    }

    @Test
    public void testInitShutdownSequence() {
        logger.debug(new Object(){}.getClass().getEnclosingMethod().getName());

        boolean test = true;

        this.initRCLjava();
        this.releaseRCLjava();

        try {
            RCLJava.rclJavaInit();
            this.releaseRCLjava();
        } catch (Exception e) {
            test = false;
        }

        Assert.assertTrue("failed to initialize rclJava after shutdown", test);
    }

    @Test
    public void testShutdownDouble() {
        logger.debug(new Object(){}.getClass().getEnclosingMethod().getName());

        boolean test = false;

        this.initRCLjava();
        RCLJava.shutdown();

        try {
            this.releaseRCLjava();
        } catch (Exception e) {
            test = true;
        }

        Assert.assertTrue("Expected Runtime error when shutting down rclJava twice", test);
    }

    @Test
    public void testCreateNode() {
        logger.debug(new Object(){}.getClass().getEnclosingMethod().getName());

        boolean test = true;
        NativeNode node = null;

        this.initRCLjava();
        try {
            node = (NativeNode)RCLJava.createNode("testNode");
            node.getName();
            node.close();
        } catch (Exception e) {
            test = false;
        } finally {
            this.releaseRCLjava();
        }

        Assert.assertTrue("Expected Runtime error.", test);
    }

    @Test
    public void testOk() {
        logger.debug(new Object(){}.getClass().getEnclosingMethod().getName());

        boolean test = true;

        this.initRCLjava();

        try {
            test = RCLJava.ok();
            Assert.assertEquals(true, RCLJava.ok());
        } catch (Exception e) {
            test = false;
        }
        Assert.assertTrue("Expected Runtime error.", test);

        try {
            this.releaseRCLjava();
            test = RCLJava.ok();
            // not really call...
            assertEquals(false, test);
            test = false;
        } catch (NotInitializedException e) {
            test = true;
        } catch (Exception e) {
            test = false;
        }
        Assert.assertTrue("Expected Runtime error.", test);
    }

    @Test
    public void testNotInitializedException() {
        logger.debug(new Object(){}.getClass().getEnclosingMethod().getName());

        boolean test = false;
        NativeNode node = null;

        try {
            node = (NativeNode)RCLJava.createNode("testNode");
            node.close();
        } catch (NotInitializedException e) {
            test = true;
        } catch (ExceptionInInitializerError e) {
            if (e.getCause() != null && e.getCause().getClass() == NotInitializedException.class) {
                test = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue("failed not initialized exception !", test);

        try {
            node = new NativeNode("testNode");
            node.close();
//        } catch (NoClassDefFoundError e) {
//            test = true;
        } catch (NotInitializedException e) {
            test = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue("failed not initialized exception !", test);
    }

}
