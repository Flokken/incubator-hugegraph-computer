/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.computer.core.io;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.IOException;

public interface RandomAccessOutput extends DataOutput, Closeable {

    long position();

    void seek(long position) throws IOException;

    /**
     * Skip {@code n} bytes.
     * @return the position before skip.
     */
    long skip(long n) throws IOException;

    void writeInt(long position, int v) throws IOException;

    void write(RandomAccessInput input, long offset, long length)
               throws IOException;

    default long writeIntLength(int v) throws IOException {
        long position = this.position();
        this.writeInt(v);
        return position;
    }

    default void writeIntLength(long position, int v) throws IOException {
        this.writeInt(position, v);
    }

    default long writeLongLength(long v) throws IOException {
        long position = this.position();
        this.writeLong(v);
        return position;
    }
}