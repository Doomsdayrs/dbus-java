package org.freedesktop.dbus.transport.jre;

import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

import org.freedesktop.dbus.connections.BusAddress;
import org.freedesktop.dbus.connections.transports.IFileBasedBusAddress;
import org.freedesktop.dbus.utils.Util;

public class UnixBusAddress extends BusAddress implements IFileBasedBusAddress {

    public UnixBusAddress(BusAddress _obj) {
        super(_obj);
    }

    public boolean hasPath() {
        return getParameters().containsKey("path");
    }

    public String getPath() {
        return getParameters().get("path");
    }

    @Override
    public void updatePermissions(String _fileOwner, String _fileGroup, Set<PosixFilePermission> _fileUnixPermissions) {
        Util.setFilePermissions(Path.of(getPath()), _fileOwner, _fileGroup, _fileUnixPermissions);
    }

}
