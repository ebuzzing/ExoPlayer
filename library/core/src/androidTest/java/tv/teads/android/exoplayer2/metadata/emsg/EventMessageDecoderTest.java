/*
 * Copyright (C) 2017 The Android Open Source Project
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
package tv.teads.android.exoplayer2.metadata.emsg;

import android.test.MoreAsserts;
import tv.teads.android.exoplayer2.metadata.Metadata;
import tv.teads.android.exoplayer2.metadata.MetadataInputBuffer;
import java.nio.ByteBuffer;
import junit.framework.TestCase;

/**
 * Test for {@link EventMessageDecoder}.
 */
public final class EventMessageDecoderTest extends TestCase {

  public void testDecodeEventMessage() {
    byte[] rawEmsgBody = new byte[] {
        117, 114, 110, 58, 116, 101, 115, 116, 0, // scheme_id_uri = "urn:test"
        49, 50, 51, 0, // value = "123"
        0, 0, -69, -128, // timescale = 48000
        0, 0, 0, 0, // presentation_time_delta (ignored) = 0
        0, 2, 50, -128, // event_duration = 144000
        0, 15, 67, -45, // id = 1000403
        0, 1, 2, 3, 4}; // message_data = {0, 1, 2, 3, 4}
    EventMessageDecoder decoder = new EventMessageDecoder();
    MetadataInputBuffer buffer = new MetadataInputBuffer();
    buffer.data = ByteBuffer.allocate(rawEmsgBody.length).put(rawEmsgBody);
    Metadata metadata = decoder.decode(buffer);
    assertEquals(1, metadata.length());
    EventMessage eventMessage = (EventMessage) metadata.get(0);
    assertEquals("urn:test", eventMessage.schemeIdUri);
    assertEquals("123", eventMessage.value);
    assertEquals(3000, eventMessage.durationMs);
    assertEquals(1000403, eventMessage.id);
    MoreAsserts.assertEquals(new byte[] {0, 1, 2, 3, 4}, eventMessage.messageData);
  }

}
