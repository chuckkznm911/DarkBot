package com.github.manolo8.darkbot.core.objects.swf;

import com.github.manolo8.darkbot.core.itf.Updatable;
import com.github.manolo8.darkbot.core.utils.ByteUtils;

import static com.github.manolo8.darkbot.core.manager.Core.API;

public class SwfVectorInt extends Updatable {

    public int[] elements;
    public int   size;

    public SwfVectorInt() {
        this.elements = new int[0];
        this.size = 0;
    }

    public SwfVectorInt(long address) {
        this();
        this.address = address;
    }

    @Override
    public void update() {

        if (address == 0)
            return;

        int size = API.readMemoryInt(address + 64);

        if (size < 1 || size > 512) return;

        if (this.size < size)
            elements = new int[size];

        this.size = size;

        byte[] data = API.readMemory(API.readMemoryLong(address + 48) + 4, size * 4);

        int count = 0;

        for (int i = 0; i < data.length; i += 4)
            elements[count++] = ByteUtils.getInt(data, i);
    }

}
