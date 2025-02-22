package org.freedesktop.dbus.test;

import org.freedesktop.dbus.DBusPath;
import org.freedesktop.dbus.Marshalling;
import org.freedesktop.dbus.ObjectPath;
import org.freedesktop.dbus.Struct;
import org.freedesktop.dbus.annotations.DBusInterfaceName;
import org.freedesktop.dbus.annotations.Position;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.interfaces.DBusInterface;
import org.freedesktop.dbus.messages.DBusSignal;
import org.freedesktop.dbus.messages.Message;
import org.freedesktop.dbus.messages.MessageFactory;
import org.freedesktop.dbus.test.helper.structs.MarkTuple;
import org.freedesktop.dbus.types.DBusListType;
import org.freedesktop.dbus.types.Variant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarshallingTest extends AbstractBaseTest {

    @Test
    public void parseComplexMessageReturnsCorrectTypes() throws DBusException {
        List<Type> temp = new ArrayList<>();
        Marshalling.getJavaType("a(oa{sv})ao", temp, -1);

        Assertions.assertEquals(2, temp.size(), "result must contain two types");
        Assertions.assertTrue(temp.get(0) instanceof DBusListType);
        Assertions.assertTrue(temp.get(1) instanceof DBusListType);
    }

    @Test
    public void parseStructReturnsCorrectParsedCharsCount() throws Exception {
        List<Type> temp = new ArrayList<>();
        int parsedCharsCount = Marshalling.getJavaType("(oa{sv})ao", temp, 1);

        Assertions.assertEquals(8, parsedCharsCount);
    }

    private static byte[] streamReader(String _file) throws IOException {
       return Files.readAllBytes(new File(_file).toPath());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMarshalling() throws Exception {

        // create an array with the required parameters of ServicesChanged signal (required to create a proper list of objects by reflection later on)
        Type[] types = null;
        if (null == types) {
            Constructor<?> con = IServicesChanged.ServicesChanged.class.getDeclaredConstructors()[0];
            Type[] ts = con.getGenericParameterTypes();
            types = new Type[ts.length - 1];
            for (int i = 1; i < ts.length; i++) {
                if (ts[i] instanceof TypeVariable) {
                    for (Type b : ((TypeVariable<GenericDeclaration>) ts[i]).getBounds()) {
                        types[i - 1] = b;
                    }
                } else {
                    types[i - 1] = ts[i];
                }
            }
        }

        // create a message from dumped data (including header and body)
        Message msg = MessageFactory.createMessage(Message.MessageType.SIGNAL,
                streamReader("src/test/resources/" + getClass().getSimpleName() + "/connman_sample_buf.bin"),
                streamReader("src/test/resources/" + getClass().getSimpleName() + "/connman_sample_header.bin"),
                streamReader("src/test/resources/" + getClass().getSimpleName() + "/connman_sample_body.bin"), null);

        // use the Marshalling tools to get the parameters for the ServicesChanged signal
        Object[] params = Marshalling.deSerializeParameters(msg.getParameters(), types, null);

        // first parameter should be a list (of our custom type)
        assertTrue(params[0] instanceof List, "First param is not a List");
        // second parameter should be a list of object path
        assertTrue(params[1] instanceof List, "Second param is not a List");

    }

    @Test
    public void testDeserializeParametersWithTuple() throws Exception {
        Object[] ob = {
                "rootfs.1", "marked slot rootfs.1 as good"
        };
        Method m = Installer.class.getDeclaredMethod("Mark", String.class, String.class);
        Type[] ts = new Type[] {m.getGenericReturnType()};

        Object[] params = Marshalling.deSerializeParameters(ob, ts, null);

        assertTrue(params[0] instanceof MarkTuple, "First param is not a MarkTuple");
        MarkTuple mt = (MarkTuple) params[0];
        assertEquals(mt.getSlotName(), "rootfs.1", "Slot name does not match after deSerialization");
        assertEquals(mt.getMessage(), "marked slot rootfs.1 as good", "Message does not match after deSerialization");
    }

    /*
     ******************************************
     *
     *     DUMMY TEST CLASSES
     *
     ******************************************
     */

    @DBusInterfaceName("net.connman.Manager")
    @SuppressWarnings({"checkstyle:methodname", "checkstyle:visibilitymodifier"})
    interface IServicesChanged extends DBusInterface {

         class ServicesChanged extends DBusSignal {

            public final String  objectPath;

            public final List<SomeData> changed;
            public final List<ObjectPath> removed;

            ServicesChanged(String _objectPath, List<SomeData> _k, List<ObjectPath> _removedItems) throws DBusException {
                super(_objectPath, _k, _removedItems);
                objectPath = _objectPath;

                changed = _k;
                removed = _removedItems;
            }

            public String getObjectPath() {
                return objectPath;
            }

            public List<SomeData> getChanged() {
                return changed;
            }

            public List<ObjectPath> getRemoved() {
                return removed;
            }

         }
    }

    @SuppressWarnings({"checkstyle:methodname", "checkstyle:visibilitymodifier"})
    public static class SomeData extends Struct {
        @Position(0)
        public DBusPath objectPath;
        @Position(1)
        public Map<String, Variant<?>> properties;

        public SomeData(DBusPath _objectPath, Map<String, Variant<?>> _properties) {
            this.objectPath = _objectPath;
            this.properties = _properties;
        }

        DBusPath getObjectPath() {
            return objectPath;
        }
        void setObjectPath(DBusPath _objectPath) {
            objectPath = _objectPath;
        }
        Map<String, Variant<?>> getProperties() {
            return properties;
        }
        void setProperties(Map<String, Variant<?>> _properties) {
            properties = _properties;
        }

    }

    @SuppressWarnings("checkstyle:methodname")
    public interface Installer extends DBusInterface {
        MarkTuple Mark(String _state, String _slotIdentifier);
    }
}
