package org.ros2.rcljava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.BasicConfigurator;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.ros2.rcljava.internal.message.Message;
import org.ros2.rcljava.node.Node;
import org.ros2.rcljava.node.service.RCLFuture;
import org.ros2.rcljava.node.topic.Publisher;
import org.ros2.rcljava.node.topic.Subscription;
import org.ros2.rcljava.node.topic.SubscriptionCallback;

import std_msgs.msg.Header;

public abstract class AbstractMessageTest {

    private final static String NODE_NAME       = "test_node";
    private final static String VALUE_FRAMEID   = "frameTest";
    private final static int VALUE_TIME_SEC     = 100;
    private final static int VALUE_TIME_NANOSEC = 200;

    protected Node node;

    @BeforeClass
    public static void beforeClass() {
        BasicConfigurator.resetConfiguration();
        BasicConfigurator.configure();
    }

    @Before
    public void setUp() {
        RCLJava.rclJavaInit();
        this.node = RCLJava.createNode(NODE_NAME);
    }

    @After
    public void tearDown() {
        this.node.dispose();
        RCLJava.shutdown();
    }


    @Test
    public final void testCreate() {
        assertNotEquals(0, this.node.getNodeHandle());
    }

    @SuppressWarnings("unchecked")
    private <T extends Message> Header headerInit(T msg) {
        Header header = null;
        Class<T> msgClass = (Class<T>) msg.getClass();

        try {
            Method methodToFind = msgClass.getMethod("getHeader", (Class<?>[]) null);
            if (methodToFind != null) {
                header = (Header) methodToFind.invoke(msg);
                headerInit(header);
            }
        } catch (Exception e) { /* Bypass just a test !*/ }

        if (header != null) {
            header.setFrameId(VALUE_FRAMEID);
            header.getStamp().setSec(VALUE_TIME_SEC);
            header.getStamp().setNanosec(VALUE_TIME_NANOSEC);
        }

        return header;
    }

    private void headerTest(Header msg) {
        assertEquals(VALUE_FRAMEID, msg.getFrameId());
        assertEquals(VALUE_TIME_SEC, msg.getStamp().getSec());
        assertEquals(VALUE_TIME_NANOSEC, msg.getStamp().getNanosec());
    }

    @SuppressWarnings("unchecked")
    protected <T extends Message> T pubSubTest(T msg) throws InterruptedException, ExecutionException {
        Class<T> msgClass = (Class<T>) msg.getClass();
        Publisher<T> publisher = this.node.createPublisher(msgClass, "test_topic");

        Header header = this.headerInit(msg);

        RCLFuture<T> future = new RCLFuture<T>(new WeakReference<Node>(this.node));
        Subscription<T> subscription = this.node.createSubscription(
                msgClass, "test_topic", new TestConsumer<T>(future));

        while (RCLJava.ok() && !future.isDone()) {
            publisher.publish(msg);
            RCLJava.spinOnce(this.node);
        }

        T value = future.get();

        publisher.dispose();
        subscription.dispose();

        assertNotNull(value);

        if (header != null) {
            this.headerTest(header);
        }

        return value;
    }

    public class TestConsumer<T extends Message> implements SubscriptionCallback<T> {
        private final RCLFuture<T> future;

        TestConsumer(final RCLFuture<T> future) {
            this.future = future;
        }

        public final void dispatch(final T msg) {
            if (!this.future.isDone()) {
                this.future.set(msg);
            }
        }
    }
}