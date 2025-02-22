package org.freedesktop.dbus.test;

import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.connections.impl.DBusConnectionBuilder;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.NotConnected;
import org.freedesktop.dbus.test.helper.SampleClass;
import org.freedesktop.dbus.test.helper.interfaces.SampleRemoteInterface;
import org.junit.jupiter.api.Test;

public class TestDisconnectStuff extends AbstractDBusBaseTest {

    @Test
    public void testStuffAfterDisconnect() throws DBusException, InterruptedException {

        DBusConnection serverConnection = DBusConnectionBuilder.forSessionBus()
                .withWeakReferences(true).withShared(false).build();
        DBusConnection clientConnection = DBusConnectionBuilder.forSessionBus()
                .withWeakReferences(true).withShared(false).build();

        serverConnection.requestBusName("foo.bar.why.again.disconnect.Test");

        SampleClass tclass = new SampleClass(serverConnection);

        serverConnection.exportObject("/Test2001", tclass);

        SampleRemoteInterface tri =
                clientConnection.getRemoteObject("foo.bar.why.again.disconnect.Test", "/Test2001", SampleRemoteInterface.class);

        assertThrows(NotConnected.class, () -> {
            clientConnection.disconnect();
            Thread.sleep(1000L);
            serverConnection.disconnect();
            Thread.sleep(1000L);
            logger.warn("getName() suceeded and returned: " + tri.getName());
            fail("Should not succeed when disconnected");
        });

    }
}
