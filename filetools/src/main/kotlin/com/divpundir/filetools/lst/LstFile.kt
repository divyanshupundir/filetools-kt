package com.divpundir.filetools.lst

public interface LstFile {

    public val entries: List<String>

    public fun update(values: List<String>)

    public fun write()
}
