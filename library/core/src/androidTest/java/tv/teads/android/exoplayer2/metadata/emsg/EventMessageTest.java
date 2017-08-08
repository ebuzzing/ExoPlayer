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

import android.os.Parcel;
import junit.framework.TestCase;

/**
 * Test for {@link EventMessage}.
 */
public final class EventMessageTest extends TestCase {

  public void testEventMessageParcelable() {
    EventMessage eventMessage = new EventMessage("urn:test", "123", 3000, 1000403,
        new byte[] {0, 1, 2, 3, 4});
    // Write to parcel.
    Parcel parcel = Parcel.obtain();
    eventMessage.writeToParcel(parcel, 0);
    // Create from parcel.
    parcel.setDataPosition(0);
    EventMessage fromParcelEventMessage = EventMessage.CREATOR.createFromParcel(parcel);
    // Assert equals.
    assertEquals(eventMessage, fromParcelEventMessage);
  }

}