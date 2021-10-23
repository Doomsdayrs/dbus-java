package org.freedesktop.dbus;

public class DBusPath implements Comparable<DBusPath> {
    private String path;

    public DBusPath(String _path) {
        this.setPath(_path);
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return getPath();
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof DBusPath) && getPath().equals(((DBusPath) other).getPath());
    }

    @Override
    public int hashCode() {
        return getPath().hashCode();
    }

    @Override
    public int compareTo(DBusPath that) {
        return getPath().compareTo(that.getPath());
    }

    public void setPath(String _path) {
        path = _path;
    }
}
