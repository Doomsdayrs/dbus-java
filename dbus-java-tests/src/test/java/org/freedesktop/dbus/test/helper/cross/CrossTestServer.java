package org.freedesktop.dbus.test.helper.cross;

import org.freedesktop.dbus.annotations.IntrospectionDescription;
import org.freedesktop.dbus.connections.impl.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;
import org.freedesktop.dbus.exceptions.DBusExecutionException;
import org.freedesktop.dbus.interfaces.DBusSigHandler;
import org.freedesktop.dbus.test.helper.interfaces.Binding;
import org.freedesktop.dbus.test.helper.interfaces.Binding.CrossSampleStruct;
import org.freedesktop.dbus.test.helper.interfaces.Binding.SingleSample;
import org.freedesktop.dbus.test.helper.interfaces.Binding.Triplet;
import org.freedesktop.dbus.test.helper.interfaces.SamplesInterface;
import org.freedesktop.dbus.types.UInt16;
import org.freedesktop.dbus.types.UInt32;
import org.freedesktop.dbus.types.UInt64;
import org.freedesktop.dbus.types.Variant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CrossTestServer implements SamplesInterface, SingleSample, DBusSigHandler<Binding.SampleClient.Trigger> {
    private final DBusConnection conn;

    private volatile boolean     run     = true;
    private Set<String>          done    = new TreeSet<>();
    private Set<String>          notdone = new TreeSet<>();
    {
        notdone.add("org.freedesktop.DBus.Binding.Tests.Identity");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityByte");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityBool");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityInt16");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt16");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityInt32");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt32");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityInt64");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt64");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityDouble");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityString");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityArray");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityByteArray");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityBoolArray");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityInt16Array");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt16Array");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityInt32Array");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt32Array");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityInt64Array");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt64Array");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityDoubleArray");
        notdone.add("org.freedesktop.DBus.Binding.Tests.IdentityStringArray");
        notdone.add("org.freedesktop.DBus.Binding.Tests.Sum");
        notdone.add("org.freedesktop.DBus.Binding.SingleTests.Sum");
        notdone.add("org.freedesktop.DBus.Binding.Tests.InvertMapping");
        notdone.add("org.freedesktop.DBus.Binding.Tests.DeStruct");
        notdone.add("org.freedesktop.DBus.Binding.Tests.Primitize");
        notdone.add("org.freedesktop.DBus.Binding.Tests.Invert");
        notdone.add("org.freedesktop.DBus.Binding.Tests.Trigger");
        notdone.add("org.freedesktop.DBus.Binding.Tests.Exit");
        notdone.add("org.freedesktop.DBus.Binding.TestClient.Trigger");
    }

    public CrossTestServer(DBusConnection _conn) {
        this.conn = _conn;
    }

    @Override
    public boolean isRemote() {
        return false;
    }

    @Override
    public String getObjectPath() {
        return null;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean _run) {
        run = _run;
    }

    public Set<String> getDone() {
        return done;
    }

    public void setDone(Set<String> _done) {
        done = _done;
    }

    public Set<String> getNotdone() {
        return notdone;
    }

    public void setNotdone(Set<String> _notdone) {
        notdone = _notdone;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public <T> Variant<T> Identity(Variant<T> _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.Identity");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.Identity");
        return new Variant<>(_input.getValue());
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public byte IdentityByte(byte _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityByte");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityByte");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public boolean IdentityBool(boolean _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityBool");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityBool");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public short IdentityInt16(short _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityInt16");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityInt16");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public UInt16 IdentityUInt16(UInt16 _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt16");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityUInt16");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public int IdentityInt32(int _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityInt32");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityInt32");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public UInt32 IdentityUInt32(UInt32 _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt32");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityUInt32");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public long IdentityInt64(long _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityInt64");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityInt64");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public UInt64 IdentityUInt64(UInt64 _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt64");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityUInt64");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public double IdentityDouble(double _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityDouble");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityDouble");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public String IdentityString(String _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityString");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityString");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public <T> Variant<T>[] IdentityArray(Variant<T>[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityArray");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityArray");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public byte[] IdentityByteArray(byte[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityByteArray");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityByteArray");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public boolean[] IdentityBoolArray(boolean[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityBoolArray");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityBoolArray");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public short[] IdentityInt16Array(short[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityInt16Array");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityInt16Array");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public UInt16[] IdentityUInt16Array(UInt16[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt16Array");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityUInt16Array");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public int[] IdentityInt32Array(int[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityInt32Array");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityInt32Array");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public UInt32[] IdentityUInt32Array(UInt32[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt32Array");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityUInt32Array");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public long[] IdentityInt64Array(long[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityInt64Array");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityInt64Array");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public UInt64[] IdentityUInt64Array(UInt64[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityUInt64Array");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityUInt64Array");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public double[] IdentityDoubleArray(double[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityDoubleArray");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityDoubleArray");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns whatever it is passed")
    public String[] IdentityStringArray(String[] _input) {
        done.add("org.freedesktop.DBus.Binding.Tests.IdentityStringArray");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.IdentityStringArray");
        return _input;
    }

    @Override
    @IntrospectionDescription("Returns the sum of the values in the input list")
    public long Sum(int[] _a) {
        done.add("org.freedesktop.DBus.Binding.Tests.Sum");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.Sum");
        long sum = 0;
        for (int b : _a) {
            sum += b;
        }
        return sum;
    }

    @Override
    @IntrospectionDescription("Returns the sum of the values in the input list")
    public UInt32 Sum(byte[] _a) {
        done.add("org.freedesktop.DBus.Binding.SingleTests.Sum");
        notdone.remove("org.freedesktop.DBus.Binding.SingleTests.Sum");
        int sum = 0;
        for (byte b : _a) {
            sum += b < 0 ? b + 256 : b;
        }
        return new UInt32(sum % (UInt32.MAX_VALUE + 1));
    }

    @Override
    @IntrospectionDescription("Given a map of A => B, should return a map of B => a list of all the As which mapped to B")
    public Map<String, List<String>> InvertMapping(Map<String, String> _a) {
        done.add("org.freedesktop.DBus.Binding.Tests.InvertMapping");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.InvertMapping");
        Map<String, List<String>> m = new HashMap<>();
        for (String s : _a.keySet()) {
            String b = _a.get(s);
            List<String> l = m.get(b);
            if (null == l) {
                l = new ArrayList<>();
                m.put(b, l);
            }
            l.add(s);
        }
        return m;
    }

    @Override
    @IntrospectionDescription("This method returns the contents of a struct as separate values")
    public Triplet<String, UInt32, Short> DeStruct(CrossSampleStruct _a) {
        done.add("org.freedesktop.DBus.Binding.Tests.DeStruct");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.DeStruct");
        return new Triplet<>(_a.getSampleString(), _a.getSampleUint32(), _a.getSampleShort());
    }

    @Override
    @IntrospectionDescription("Given any compound type as a variant, return all the primitive types recursively contained within as an array of variants")
    public List<Variant<Object>> Primitize(Variant<Object> _a) {
        done.add("org.freedesktop.DBus.Binding.Tests.Primitize");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.Primitize");
        return CrossTestClient.primitizeRecurse(_a.getValue(), _a.getType());
    }

    @Override
    @IntrospectionDescription("inverts it's input")
    public boolean Invert(boolean _a) {
        done.add("org.freedesktop.DBus.Binding.Tests.Invert");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.Invert");
        return !_a;
    }

    @Override
    @IntrospectionDescription("triggers sending of a signal from the supplied object with the given parameter")
    public void Trigger(String _a, UInt64 _b) {
        done.add("org.freedesktop.DBus.Binding.Tests.Trigger");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.Trigger");
        try {
            conn.sendMessage(new Binding.SampleSignals.Triggered(_a, _b));
        } catch (DBusException _exD) {
            throw new DBusExecutionException(_exD.getMessage());
        }
    }

    @Override
    public void Exit() {
        done.add("org.freedesktop.DBus.Binding.Tests.Exit");
        notdone.remove("org.freedesktop.DBus.Binding.Tests.Exit");
        run = false;
        synchronized (this) {
            notifyAll();
        }
    }

    @Override
    public void handle(Binding.SampleClient.Trigger _t) {
        done.add("org.freedesktop.DBus.Binding.TestClient.Trigger");
        notdone.remove("org.freedesktop.DBus.Binding.TestClient.Trigger");
        try {
            Binding.SampleClient cb = conn.getRemoteObject(_t.getSource(), "/TestClient", Binding.SampleClient.class);
            cb.Response(_t.getSampleUint16(), _t.getSampleDouble());
        } catch (DBusException _exD) {
            throw new DBusExecutionException(_exD.getMessage());
        }
    }

}
